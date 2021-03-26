import json
import boto3
import requests
from requests_aws4auth import AWS4Auth
import base64

# Authentication stuff
region = 'eu-west-1'
service = 'es'
credentials = boto3.Session().get_credentials()
auth = AWS4Auth(credentials.access_key, credentials.secret_key, region, service, session_token=credentials.token)

# Building the URL
host = 'https://search-event-service-fyrtpugn2ktogtbm47a7vugeu4.eu-west-1.es.amazonaws.com'  # Link to ElasticSearch instance for events
kine_index = 'event-index'
kine_type = 'lambda-kine-type'

insert_url = host + '/' + kine_index + '/' + kine_type + '/'
delete_url = host + '/' + kine_index + '/' + '_delete_by_query' + '/'
modify_url = host + '/' + kine_index + '/' + '_update_by_query' + '?conflicts=proceed'

headers = {"content": "application/json"}


def lambda_handler(event, context):
    """
    This Lambda function is responsible for taking a Kinesis stream of data, and putting it in the ElasticSearch Service.

    This function is internal, and shouldn't be accessed on the outside!

    This function triggers automatically upon a change to the "events" document in DynamoDB.
    """

    print(event)

    count = 0
    for record in event['Records']:
        id = record['eventID']
        method = record['eventName']
        if method == "INSERT":
            handle_insert(record, id)
        elif method == "REMOVE":
            handle_remove(record, id)
        elif method == "MODIFY":
            handle_modify(record, id)
        count += 1
    return 'Processed ' + str(count) + ' items.'


def handle_modify(record, id):
    print("modifying")
    image = record['dynamodb']['NewImage']
    handle_remove(record, id)
    handle_insert(record, id)


def handle_remove(record, id):
    query = {
        "query": {
            "match": {
                "message.eventId": record['dynamodb']['Keys']['eventId']['S']
            }
        }
    }

    r = requests.post(delete_url, auth=auth, json=query, headers=headers)


def handle_insert(record, id):
    timestamp = record['dynamodb']['ApproximateCreationDateTime']

    # Kinesis data is base64-encoded, so decode here
    message = flatten(record['dynamodb']['NewImage'])

    # Create the JSON document
    document = {"id": id, "timestamp": timestamp, "message": message}
    print(document)
    # Index the document
    r = requests.put(insert_url + id, auth=auth, json=document, headers=headers)


def flatten(message: dict) -> dict:
    new_message = dict()
    nested_keys = ['S', 'N', 'B']

    for key, value in message.items():
        if type(value) is dict:
            for nested_key, nested_val in value.items():
                if nested_key == 'L':
                    new_message[key] = []

                if nested_key in nested_keys:
                    new_message[key] = nested_val

    return new_message
