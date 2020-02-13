package com.software.statistics.pojo;

import io.swagger.models.auth.In;

import java.math.BigInteger;

public class SoftVo {

    private String name;//软件名称

    private Integer download;//下载数

    public SoftVo() {
    }

    public SoftVo(String name, BigInteger download) {
        this.name = name;
        this.download = download.intValue();
    }

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
