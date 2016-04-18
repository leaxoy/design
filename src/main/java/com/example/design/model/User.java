package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;


/**
 * Created by lxh on 4/14/16.
 */
public class User {
    @Id
    private Long id;
    private String accountName;
    private String password;
    private String email;
    private String nickname;
    private String userPicture;
    private String name;
    private String sex;
    private Date birthday;
    private String job;
    private String city;
    private String userIntro;
    private VISITROLE visitrole;
    private ROLE role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public VISITROLE getVisitrole() {
        return visitrole;
    }

    public void setVisitrole(VISITROLE visitrole) {
        this.visitrole = visitrole;
    }

    private enum ROLE {
        admin, user, guest;
    }

    private enum VISITROLE {
        SELF, FRIEND, ALL
    }
}
