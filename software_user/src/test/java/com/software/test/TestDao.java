package com.software.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.software.user.UserApplication;
import com.software.user.dao.UserDao;
import com.software.user.pojo.UserVo;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class TestDao {


    @Autowired
    private UserDao userDao;

    @Test
    public void test() throws Exception {
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
