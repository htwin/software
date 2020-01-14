package com.software.tutorial.service;

import com.software.tutorial.dao.TutorialDao;
import com.software.tutorial.pojo.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorialService {

    @Autowired
    private TutorialDao tutorialDao;

    public Tutorial findBySoftwareId(String softwareId){
        return tutorialDao.findBySoftwareId(softwareId);
    }

}
