package com.pxy.tutor.domain.entities;

import javax.persistence.*;

@Entity
public class MStudent {

    @Column(name = "StudentNO")
    private String studentNO;

    @Column(name = "Name")
    private String name;

    @Column(name = "Pwd")
    private String pwd;

    @Column(name = "SubjectTeach")
    private String subjectTeach;

    @Column(name = "RequireTeach")
    private String requireTeach;

    @Column(name = "TeachingTime")
    private String  teachingTime;

    @Column(name = "AreaTeach")
    private String areaTeach;

    @Column(name = "HeadUrl")
    private String headUrl;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentNO() {
        return studentNO;
    }

    public void setStudentNO(String studentNO) {
        this.studentNO = studentNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSubjectTeach() {
        return subjectTeach;
    }

    public void setSubjectTeach(String subjectTeach) {
        this.subjectTeach = subjectTeach;
    }

    public String getRequireTeach() {
        return requireTeach;
    }

    public void setRequireTeach(String requireTeach) {
        this.requireTeach = requireTeach;
    }

    public String getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(String teachingTime) {
        this.teachingTime = teachingTime;
    }

    public String getAreaTeach() {
        return areaTeach;
    }

    public void setAreaTeach(String areaTeach) {
        this.areaTeach = areaTeach;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
