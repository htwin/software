package com.software.soft.service;

import com.software.common.util.FastDFSUtil;
import com.software.common.util.IdWorker;
import com.software.soft.dao.SoftDao;
import com.software.soft.pojo.Soft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;


@Service
public class SoftService {

    @Autowired
    private SoftDao softDao;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Autowired
    private IdWorker idWorker;

    public Page search(int page, int size,Soft soft) {

        //忽略条件查询

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Soft> pages = softDao.findAll(pageable);

        return pages;

    }

    public Page<Soft> findByClassifyId(String classifyId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return softDao.findByClassifyId(classifyId, pageRequest);

    }

    public Page<Soft> findHotList(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return softDao.findHotList(pageRequest);
    }

    public Page<Soft> findNewList(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return softDao.findNewList(pageRequest);
    }

    public Soft findById(String id) {
        return softDao.findById(id).get();
    }

    @Transactional
    public void thumb(String id) {
        softDao.updateThumb(id);
    }

    public void download(@RequestBody Soft soft) throws IOException {
        //group1-M00/00/00/wKhihF4hZwmAJoXHAAAXA0yMGTo637.png
        String[] fileId = soft.getUrl().split("-");

        byte[] file = fastDFSUtil.download(fileId[0], fileId[1]);

        //扩展名
        String extName = soft.getUrl().substring(soft.getUrl().lastIndexOf("."));
        String filename = soft.getName() + extName;


        String realFilename = java.net.URLEncoder.encode(filename, "UTF-8");
        //解决中文编码
        // filename = URLEncoder.encode(filename,"utf-8");
        //response.setContentType("application/multipart/form-data");
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + realFilename);

        ServletOutputStream out = response.getOutputStream();

        out.write(file);
        out.flush();
        out.close();

    }

    public void add(Soft soft){
        //封装
        soft.setId(idWorker.nextId()+"");
        //设置默认值
        soft.setComment(0);//评论数
        soft.setIshot(0);//是否热门
        soft.setDownload(0);//下载数
        soft.setScore(0);//评分 0
        soft.setThumb(0);//点赞数 0
        soft.setCreatetime(new Date());
        soft.setUpdatetime(new Date());
        softDao.save(soft);
    }

    //根据id删除
    public void deleteById(String id){
        softDao.deleteById(id);
    }

    //更新软件
    public void update(Soft soft) {
        softDao.save(soft);
    }
}
