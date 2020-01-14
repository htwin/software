package com.software.soft.service;

import com.software.common.entity.PageResult;
import com.software.soft.dao.SoftDao;
import com.software.soft.pojo.Soft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;


@Service
public class SoftService {

    @Autowired
    private SoftDao softDao;

    public Page getSoftList(int page,int size){

        Pageable pageable = PageRequest.of(page-1, size);

        Page<Soft> pages = softDao.findAll(pageable);

        return pages;

    }

    public Page<Soft> findByClassifyId(String classifyId,int page,int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return softDao.findByClassifyId(classifyId,pageRequest);

    }

    public Page<Soft> findHotList(int page,int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return softDao.findHotList(pageRequest);
    }

    public Page<Soft> findNewList(int page,int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return softDao.findNewList(pageRequest);
    }

    public Soft findById( String id){
        return softDao.findById(id).get();
    }

    @Transactional
    public void thumb(String id){
        softDao.updateThumb(id);
    }

}
