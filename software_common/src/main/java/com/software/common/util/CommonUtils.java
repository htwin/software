package com.software.common.util;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

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
