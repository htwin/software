package com.software.classify.service;

import com.software.classify.dao.ClassifyDao;
import com.software.classify.pojo.Classify;
import com.software.common.entity.Result;
import com.software.common.util.IdWorker;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class ClassifyService {

    @Autowired
    private ClassifyDao classifyDao;

    @Autowired
    private IdWorker idWorker;

    public List<Classify> list(){
        return classifyDao.findAll();
    }

    public void add(Classify classify){
        //封装classify
        classify.setId(idWorker.nextId()+"");
        classify.setCreatetime(new Date());
        classify.setUpdatetime(new Date());
        classifyDao.save(classify);
    }

    public void delete(String id){
        classifyDao.deleteById(id);
    }

    public void update(Classify classify){
        classifyDao.save(classify);
    }

}
