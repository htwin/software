package com.software.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.software.user.UserApplication;
import com.software.user.dao.UserDao;
import com.software.user.pojo.User;
import com.software.user.pojo.UserVo;
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
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class TestDao {


    @Autowired
    private UserDao userDao;
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
    public void test() throws Exception {
        User user = new User();
        user.setCollegeId("1");
        Specification specification = createSpecification(user);
        List<Object[]> userVos = userDao.userList(0, 2);
        List<UserVo> userVos1 = castEntity(userVos, UserVo.class);
        System.out.println(userVos1);
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
