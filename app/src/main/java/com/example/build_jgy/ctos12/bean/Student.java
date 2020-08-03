package com.example.build_jgy.ctos12.bean;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String student_name;
    private String pwd;
    private String class1;
    private int final_grades;
    private int comprehensive_assessment;
    private String competition_add;
    private String activity_add;
    private String cadres_add;
    private String picture_prove;
    private int teacher_flag;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStudent_name() {
        return student_name;
    }
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getClass1() {
        return class1;
    }
    public void setClass1(String class1) {
        this.class1 = class1;
    }
    public int getFinal_grades() {
        return final_grades;
    }
    public void setFinal_grades(int final_grades) {
        this.final_grades = final_grades;
    }
    public int getComprehensive_assessment() {
        return comprehensive_assessment;
    }
    public void setComprehensive_assessment(int comprehensive_assessment) {
        this.comprehensive_assessment = comprehensive_assessment;
    }
    public String getCompetition_add() {
        return competition_add;
    }
    public void setCompetition_add(String competition_add) {
        this.competition_add = competition_add;
    }
    public String getActivity_add() {
        return activity_add;
    }
    public void setActivity_add(String activity_add) {
        this.activity_add = activity_add;
    }
    public String getCadres_add() {
        return cadres_add;
    }
    public void setCadres_add(String cadres_add) {
        this.cadres_add = cadres_add;
    }
    public String getPicture_prove() {
        return picture_prove;
    }
    public void setPicture_prove(String picture_prove) {
        this.picture_prove = picture_prove;
    }
    public int getTeacher_flag() {
        return teacher_flag;
    }
    public void setTeacher_flag(int teacher_flag) {
        this.teacher_flag = teacher_flag;
    }
    public int getAdmin_flag() {
        return admin_flag;
    }
    public void setAdmin_flag(int admin_flag) {
        this.admin_flag = admin_flag;
    }
    private int admin_flag;
}
