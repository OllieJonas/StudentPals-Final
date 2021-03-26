package com.igpgroup17.studentpals.dao.impl.chat;

import com.igpgroup17.studentpals.dao.ChatDao;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.resource.ClientResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatDaoImpl implements ChatDao {

    private final ClientResources clientResources;

    private final RedisURI redis;

    @Autowired
    public ChatDaoImpl(ClientResources clientResources, RedisURI redis) {
        this.clientResources = clientResources;
        this.redis = redis;
    }

    public String redisHelloWorld() {
        RedisClient redisClient = RedisClient.create(redis);
        StatefulRedisPubSubConnection<String, String> pubSubConnection = redisClient.connectPubSub();
        return "";
    }
}
