package com.software.test;

import com.software.soft.SoftApplication;
import com.software.soft.mapper.SoftMapper;
import com.software.soft.pojo.ClassifySoft;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = SoftApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SoftTest {


    @Autowired
    private SoftMapper softMapper;

    @Test
    public void softTest(){
        List<ClassifySoft> classifySofts = softMapper.classifySoft();
        System.out.println(classifySofts);


    }

}
