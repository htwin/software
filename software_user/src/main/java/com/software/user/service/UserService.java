package com.software.user.service;

import com.alibaba.fastjson.JSONArray;
import com.software.common.entity.PageResult;
import com.software.common.util.AccountUtil;
import com.software.common.util.CommonUtils;
import com.software.common.util.IdWorker;
import com.software.user.dao.UserDao;
import com.software.user.dao.UserSoftDownloadDao;
import com.software.user.dao.UserSoftThumbDao;
import com.software.user.feign.SoftClient;
import com.software.user.mapper.UserMapper;
import com.software.user.pojo.User;
import com.software.user.pojo.UserSoftDownload;
import com.software.user.pojo.UserSoftThumb;
import com.software.user.pojo.UserVo;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserService {



    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SoftClient softClient;

    @Autowired
    private UserSoftDownloadDao userSoftDownloadDao;

    @Autowired
    private UserSoftThumbDao userSoftThumbDao;

    @Autowired
    private BCryptPasswordEncoder encode;

    public User findByAccountAndPassword(String account,String password){

        //根据账号查找用户
        User user = userDao.findByAccount(account);

        if(user!=null){

            //判断密码   省略密码加密
            if(encode.matches(password,user.getPassword())){
                return user;
            }

        }
        return null;

    }

    //新增普通用户
    public void add(User user){//1618130214


        String collegeId = user.getCollegeId();
        //组成学号
        String account = AccountUtil.genAccount(collegeId);
       //密码默认为学号
        user.setAccount(account);
        //密码需要加密
        String password = encode.encode(account);
        user.setPassword(password);
        user.setId(idWorker.nextId()+"");
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());

        userDao.save(user);
    }

    //删除用户
    public void deleteById(String id){
        userDao.deleteById(id);
    }

    //修改普通用户
    public void update(User user){
        user.setUpdatetime(new Date());
        userMapper.update(user);
    }

    private Specification createSpecification(User user){
        return new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Predicate predicate = null;
                if(user.getCollegeId()!=null&&!"".equals(user.getCollegeId())){
                    predicate = cb.equal(root.get("collegeId").as(String.class),user.getCollegeId());
                    return cb.and(predicate);
                }
                return null;
            }
        };
    }

    //查询用户列表，可附带条件  (姓名)    user:条件
    public PageResult search(int page, int size, User user) throws Exception {


        int start = (page -1)*size;//开始索引位置，数据库中
        List<UserVo> userVos = userMapper.search(start, size, user);

        long total = userMapper.total(user);
        PageResult pageResult = new PageResult(total,userVos);
        return pageResult;


    }

    public User findById(String id) {
        return userDao.findById(id).get();
    }

    //点赞软件
    @Transactional
    public void thumb(String userId,String softId){

        userSoftThumbDao.add( userId, softId);

        //点赞成功，该软件的点赞数+1，通知soft微服务，，暂时不写
        softClient.thumb(softId);

    }

    //更新用户下载软件列表
    @Transactional
    public void downloads(String userId,String softId){

        UserSoftDownload download = userSoftDownloadDao.findByUserIdAndAndSoftId(userId, softId);
        if(download == null){
            userSoftDownloadDao.add(userId,softId,new Date());
        }
        //通知软件微服务 下载数加一  feign 调用
        softClient.updateDownload(softId);
    }

    public UserSoftThumb getUserSoftThu(String userId, String softId) {
        return userSoftThumbDao.findByUserIdAndAndSoftId(userId,softId);
    }
    //批量导入用户

    public void importUsers(MultipartFile file) throws IOException {
        List<User> list = new ArrayList<>();

        InputStream in = file.getInputStream();

        HSSFWorkbook workbook = new HSSFWorkbook(in);

        HSSFSheet sheet = workbook.getSheetAt(0);

        for(Row row : sheet){
            if(row.getRowNum()==0)continue;
            short lastCellNum = row.getLastCellNum();

            User user = new User();

            user.setId(idWorker.nextId()+"");
            user.setCreatetime(new Date());
            user.setUpdatetime(new Date());

            for(int i = 0;i<lastCellNum;i++){
                row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                String value = row.getCell(i).getStringCellValue();
                if(i==0){
                    if("信工学院".equals(value)||"信息工程学院".equals(value)){
                        user.setCollegeId("1");
                    }
                    if("外语学院".equals(value)||"外国语学院".equals(value)){
                        user.setCollegeId("2");
                    }
                    if("环境学院".equals(value)){
                        user.setCollegeId("3");
                    }
                    if("文学院".equals(value)){
                        user.setCollegeId("4");
                    }
                    if("数理学院".equals(value)){
                        user.setCollegeId("5");
                    }
                    if("传媒学院".equals(value)){
                        user.setCollegeId("6");
                    }
                    if("生科学院".equals(value)){
                        user.setCollegeId("7");
                    }
                    if("机电学院".equals(value)){
                        user.setCollegeId("8");
                    }
                    if("马克思学院".equals(value)){
                        user.setCollegeId("9");
                    }
                    if("音乐学院".equals(value)){
                        user.setCollegeId("10");
                    }
                }
                if(i==1){
                    user.setAccount(value);
                    user.setPassword(encode.encode(value));
                }
                if(i==2){
                    user.setName(value);
                }
                if(i==3){
                    user.setAge(Integer.parseInt(value));
                }
                if(i==4){
                    user.setSex("男".equals(value)?1:0);
                }
            }
            list.add(user);
        }

        userDao.saveAll(list);
    }
}
