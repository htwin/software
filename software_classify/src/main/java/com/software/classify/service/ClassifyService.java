package com.software.classify.service;

import com.software.classify.dao.ClassifyDao;
import com.software.classify.pojo.Classify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifyService {

    @Autowired
    private ClassifyDao classifyDao;

    public List<Classify> list(){
        return classifyDao.findAll();
    }

}
