package com.igpgroup17.studentpals.services.impl;

import com.igpgroup17.studentpals.dao.OrganiserCrudDao;
import com.igpgroup17.studentpals.models.Organiser;
import com.igpgroup17.studentpals.services.OrganiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganiserServiceImpl implements OrganiserService {

    private final OrganiserCrudDao organiserCrudDao;

    @Autowired
    public OrganiserServiceImpl(OrganiserCrudDao organiserCrudDao) {
        this.organiserCrudDao = organiserCrudDao;
    }

    @Override
    public Organiser createOrganiser(Organiser organiser) {
        return organiserCrudDao.createOrganiser(organiser);
    }

    @Override
    public Organiser readOrganiser(String id) {
        return organiserCrudDao.readOrganiser(id);
    }

    @Override
    public Organiser updateOrganiser(Organiser organiser) {
        return organiserCrudDao.updateOrganiser(organiser);
    }

    @Override
    public String deleteOrganiser(String id) {
        organiserCrudDao.deleteOrganiser(id);
        return "Deleted organiser";
    }
}
