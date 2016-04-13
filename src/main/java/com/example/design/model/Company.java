package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by lxh on 4/13/16.
 */
public class Company implements Serializable {
    @Id
    private Long id;
    private String name;
    private String logo;
    private String link;
    private String major;
    private String minor;
    private int is_500;
    private int is_cn500;
    private int is_crawled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public int getIs_500() {
        return is_500;
    }

    public void setIs_500(int is_500) {
        this.is_500 = is_500;
    }

    public int getIs_cn500() {
        return is_cn500;
    }

    public void setIs_cn500(int is_cn500) {
        this.is_cn500 = is_cn500;
    }

    public int getIs_crawled() {
        return is_crawled;
    }

    public void setIs_crawled(int is_crawled) {
        this.is_crawled = is_crawled;
    }
}
