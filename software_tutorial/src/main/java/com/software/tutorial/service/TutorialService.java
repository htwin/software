package com.software.tutorial.service;

import com.software.tutorial.dao.TutorialDao;
import com.software.tutorial.pojo.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TutorialService {

    @Autowired
    private TutorialDao tutorialDao;

    public Tutorial findBySoftwareId(String softwareId){
        return tutorialDao.findBySoftwareId(softwareId);
    }

    //添加教程
    public void add(Tutorial tutorial){
        tutorialDao.save(tutorial);
    }

    //根据教程id删除教程
    public void deleteById(String id){
        tutorialDao.deleteById(id);
    }

    //根据软件id删除教程
    public void deleteBySoftwareId(String softwareId){
        tutorialDao.deleteBySoftwareId(softwareId);
    }

    //修改教程
    public void update(Tutorial tutorial){
        tutorialDao.save(tutorial);
    }

    //查询教程列表
    public Page search(int page,int size,Tutorial tutorial){

        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return tutorialDao.findAll(pageRequest);
    }

}
