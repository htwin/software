package com.software.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.software.user.UserApplication;
import com.software.user.dao.UserDao;
import com.software.user.dao.UserSoftDownloadDao;
import com.software.user.dao.UserSoftThumbDao;
import com.software.user.mapper.UserMapper;
import com.software.user.pojo.User;
import com.software.user.pojo.UserSoftDownload;
import com.software.user.pojo.UserSoftThumb;
import com.software.user.pojo.UserVo;
import com.software.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class TestDao {


    @Autowired
    private UserDao userDao;

    @Autowired
    private UserSoftDownloadDao userSoftDownloadDao;

    @Autowired
    private UserSoftThumbDao userSoftThumbDao;


    @Autowired
    private UserMapper userMapper;

    @Test
    public void add(){
        UserSoftThumb softThumb = new UserSoftThumb();
        softThumb.setSoftId("1228611297025855488");
        softThumb.setUserId("23");
        softThumb.setCreatetime(new Date());
        userSoftThumbDao.save(softThumb);
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

    @Test
    public void testMybatis(){
        User user = new User();
        user.setCollegeId("2");
        long total = userMapper.total(user);
        List<UserVo> search = userMapper.search(0, 10,user);
        System.out.println(search);
    }

    @Test
    @Transactional
    public void testAdd(){
        //userSoftDownloadDao.add("1230","123");
    }

    @Test
    public void testAc(){
        int studentNum = 1;
        String newStudentNum = String.format("%04d",studentNum);
        System.out.println(newStudentNum);
    }

    @Autowired
    private UserService userService;

    @Test
    public void update(){
        User user = new User();
        user.setId("1227112736304009216");
        user.setAccount("123123");
        user.setName("修改");
        User save = userDao.save(user);
        System.out.println(save);
    }

    //转换实体类
    public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {
        List<T> returnList = new ArrayList<T>();
        if(CollectionUtils.isEmpty(list)){
            return returnList;
        }
        Object[] co = list.get(0);
        Class[] c2 = new Class[co.length];
        //确定构造方法
        for (int i = 0; i < co.length; i++) {
            if(co[i]!=null){
                c2[i] = co[i].getClass();
            }else {
                c2[i]=String.class;
            }
        }
        for (Object[] o : list) {
            Constructor<T> constructor = clazz.getConstructor(c2);
            returnList.add(constructor.newInstance(o));
        }
        return returnList;
    }

}
