package com.igpgroup17.studentpals.dao;
import com.igpgroup17.studentpals.models.Organiser;

public interface OrganiserCrudDao {
     Organiser createOrganiser(Organiser organiser);
     Organiser readOrganiser(String organiserId);
     Organiser updateOrganiser(Organiser organiser);
     void deleteOrganiser(String organiserId);
}
