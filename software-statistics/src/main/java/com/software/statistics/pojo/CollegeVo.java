package com.software.statistics.pojo;

import java.math.BigInteger;


public class CollegeVo {


    private String name;//学院名称

    private Integer download;//下载数


    /*public CollegeVo() {
    }

    public CollegeVo(String name, BigInteger download) {
        this.name = name;
        this.download = download.intValue();
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }
}
