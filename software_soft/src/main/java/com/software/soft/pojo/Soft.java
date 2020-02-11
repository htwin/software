package com.software.soft.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "soft")
public class Soft {

    @Id
    private String id;

    private String name;

    private String url;

    private float score;

    private Integer download;

    private Integer thumb;

    private Integer hasTutorial;//1有教程 0没有

    private String introduction;

    private Integer comment;

    private String classifyId;

    private Integer ishot;//1 热门 0非热门

    private String pic;

    private Date createtime;

    private Date updatetime;

    public Integer getHasTutorial() {
        return hasTutorial;
    }

    public void setHasTutorial(Integer hasTutorial) {
        this.hasTutorial = hasTutorial;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public Integer getThumb() {
        return thumb;
    }

    public void setThumb(Integer thumb) {
        this.thumb = thumb;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getIshot() {
        return ishot;
    }

    public void setIshot(Integer ishot) {
        this.ishot = ishot;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
