package com.software.statistics.service;

import com.software.common.util.CommonUtils;
import com.software.statistics.mapper.StatisticsMapper;
import com.software.statistics.pojo.CollegeVo;

import com.software.statistics.pojo.SoftVo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Autowired
    private HttpServletResponse response;

    public List<CollegeVo> college() throws Exception {
        List<CollegeVo> collegeVos = statisticsMapper.collegeNameAndDownload();
        return collegeVos;
    }

    public List<SoftVo> software() throws Exception {
        List<SoftVo> softVos = statisticsMapper.softNameAndDownload();
        return softVos;
    }

    public void exportCollege() throws IOException {

        List<CollegeVo> colleges = statisticsMapper.collegeNameAndDownload();

        //在内存中创建一个excel
        HSSFWorkbook workbook = new HSSFWorkbook();

        //新建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet();

        //设置标题
        HSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellValue("学院");
        title.createCell(1).setCellValue("下载数");

        //设置内容
        for(CollegeVo collegeVo:colleges){
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(collegeVo.getName());
            dataRow.createCell(1).setCellValue(collegeVo.getDownload());
        }

        String fileName = URLEncoder.encode("各学院下载数据统计.xls","utf-8");


        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
    }

    public void exportSoftware() throws IOException {


        List<SoftVo> softs = statisticsMapper.softNameAndDownload();
        //在内存中创建一个excel
        HSSFWorkbook workbook = new HSSFWorkbook();

        //新建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet();

        //设置标题
        HSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellValue("软件名称");
        title.createCell(1).setCellValue("下载数");

        //设置内容
        for(SoftVo softVo:softs){
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(softVo.getName());
            dataRow.createCell(1).setCellValue(softVo.getDownload());
        }

        String fileName = URLEncoder.encode("软件下载排名.xls","utf-8");


        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
    }
}
