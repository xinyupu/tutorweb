package com.pxy.tutor.domain.entities;

import javax.persistence.*;

@Entity
public class MTutor {

    @Column(name = "TutorNO")
    private String tutorNO;

    @Column(name = "Name")
    private String name;

    @Column(name = "Pwd")
    private String pwd;

    @Column(name = "Birthday")
    private String birthday;

    @Column(name = "Education")
    private String education;

    @Column(name = "CanTeach")
    private String canTeach;

    @Column(name = "MajorTeach")
    private String majorTeach;

    @Column(name = "DescTeach", columnDefinition = "varchar(1000)")
    private String descTeach;

    @Column(name = "HeadUrl")
    private String headUrl;

    @Column(name = "Address")
    private String address;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTutorNO() {
        return tutorNO;
    }

    public String getCanTeach() {
        return canTeach;
    }

    public void setCanTeach(String canTeach) {
        this.canTeach = canTeach;
    }

    public void setTutorNO(String tutorNO) {
        this.tutorNO = tutorNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajorTeach() {
        return majorTeach;
    }

    public void setMajorTeach(String majorTeach) {
        this.majorTeach = majorTeach;
    }

    public String getDescTeach() {
        return descTeach;
    }

    public void setDescTeach(String descTeach) {
        this.descTeach = descTeach;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
