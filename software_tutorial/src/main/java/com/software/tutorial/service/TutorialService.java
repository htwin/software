package com.software.tutorial.service;

import com.software.common.util.IdWorker;
import com.software.tutorial.dao.TutorialDao;
import com.software.tutorial.pojo.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class TutorialService {

    @Autowired
    private TutorialDao tutorialDao;

    @Autowired
    private IdWorker idWorker;

    public Tutorial findBySoftwareId(String softwareId){
        return tutorialDao.findBySoftwareId(softwareId);
    }

    //添加教程
    public boolean add(Tutorial tutorial){

        //一个软件只有一个教程
        //查询该软件是否有教程
        Tutorial t = tutorialDao.findBySoftwareId(tutorial.getSoftwareId());

        if(t!=null){
            return false;
        }

        tutorial.setId(idWorker.nextId()+"");
        tutorial.setCreatetime(new Date());
        tutorial.setUpdatetime(new Date());

        tutorialDao.save(tutorial);
        return true;
    }

    //根据教程id删除教程
    public void deleteById(String id){
        tutorialDao.deleteById(id);
    }

    //根据软件id删除教程
    @Transactional
    public void deleteBySoftwareId(String softwareId){
        tutorialDao.deleteBySoftwareId(softwareId);
    }

    //修改教程
    public void update(Tutorial tutorial){
        tutorial.setUpdatetime(new Date());
        tutorialDao.save(tutorial);
    }

    //查询教程列表
    public Page search(int page,int size,Tutorial tutorial){

        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return tutorialDao.findAll(pageRequest);
    }

    public Tutorial findById(String id) {
        return tutorialDao.findById(id).get();
    }
}
