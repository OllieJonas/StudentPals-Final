package com.igpgroup17.studentpals.services;
import com.igpgroup17.studentpals.models.Organiser;

public interface OrganiserService {

    Organiser createOrganiser(Organiser organiser);

    Organiser readOrganiser(String id);

    Organiser updateOrganiser(Organiser organiser);

    String deleteOrganiser(String id);
}
