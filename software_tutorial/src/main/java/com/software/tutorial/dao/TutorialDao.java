package com.software.tutorial.dao;

import com.software.tutorial.pojo.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialDao extends JpaRepository<Tutorial,String> {

    Tutorial findBySoftwareId(String softwareId);

    void deleteBySoftwareId(String softwareId);

}
