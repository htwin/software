package com.software.soft.pojo;

import java.util.List;

public class ClassifySoft {

    private String classifyId;

    private String classifyName;

    private List<Soft> softs;

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public List<Soft> getSofts() {
        return softs;
    }

    public void setSofts(List<Soft> softs) {
        this.softs = softs;
    }
}
