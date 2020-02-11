package com.software.college.service;

import com.software.college.dao.CollegeDao;
import com.software.college.pojo.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    @Autowired
    private CollegeDao collegeDao;

    public List<College> list(){
        return collegeDao.findAll();
    }

}
