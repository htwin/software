package com.software;

import com.software.common.util.CommonUtils;
import com.software.statistics.StatisticsApplication;
import com.software.statistics.mapper.StatisticsMapper;
import com.software.statistics.pojo.CollegeVo;
import com.software.statistics.pojo.SoftVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsApplication.class)
public class MyTest {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Test
    public void collegeTest() throws Exception {
        List<SoftVo> colleges = statisticsMapper.softNameAndDownload();

        System.out.println(colleges);
    }

}
