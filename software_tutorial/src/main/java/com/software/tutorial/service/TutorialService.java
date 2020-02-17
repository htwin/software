package com.software.tutorial.service;

import com.software.common.entity.PageResult;
import com.software.common.util.IdWorker;
import com.software.tutorial.dao.TutorialDao;
import com.software.tutorial.feign.SoftClient;
import com.software.tutorial.mapper.TutorialMapper;
import com.software.tutorial.pojo.Tutorial;
import com.software.tutorial.pojo.TutorialVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class TutorialService {

    @Autowired
    private TutorialMapper tutorialMapper;

    @Autowired
    private TutorialDao tutorialDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SoftClient softClient;

    public TutorialVo findBySoftwareId(String softwareId){
        return tutorialMapper.findBySoftwareId(softwareId);
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

        Tutorial tutorial1 = tutorialDao.save(tutorial);

        softClient.updateTutorial(1,tutorial.getSoftwareId());

        return true;
    }

    //根据教程id删除教程
    public void deleteById(String id){
        Tutorial tutorial = tutorialDao.findById(id).get();



        tutorialDao.deleteById(id);

        //删除教程后设置该软件无教程
        softClient.updateTutorial(0,tutorial.getSoftwareId());
    }

    //根据软件id删除教程
    @Transactional
    public void deleteBySoftwareId(String softwareId){

        tutorialDao.deleteBySoftwareId(softwareId);

        //修改软件改为无教程
        softClient.updateTutorial(0,softwareId);

    }

    //修改教程
    public void update(Tutorial tutorial){
        tutorial.setUpdatetime(new Date());
        tutorialDao.save(tutorial);
    }

    //查询教程列表
    public PageResult search(int page,int size,Tutorial tutorial){

        int start = (page-1)*size;
        long total = tutorialDao.count();

        List<TutorialVo> tutorialVos = tutorialMapper.findAll(start, size);

        return new PageResult<>(total, tutorialVos);


    }

    public Tutorial findById(String id) {
        return tutorialDao.findById(id).get();
    }
}
