package com.example.myapplication;

public class Course {

    private String info;
    private String course_id;
    private String course_number;
    private String name;
    private int units;
    private int capacity;
    private String instructor;
    private String class_time;
    private int id;
    private String exam_time;

    public Course(String info, String course_id, String course_number, String name,
                  int units, int capacity, String instructor, String class_time, int id, String exam_time) {
        this.info = info;
        this.course_id = course_id;
        this.course_number = course_number;
        this.name = name;
        this.units = units;
        this.capacity = capacity;
        this.instructor = instructor;
        this.class_time = class_time;
        this.id = id;
        this.exam_time = exam_time;

    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_number() {
        return course_number;
    }

    public void setCourse_number(String course_number) {
        this.course_number = course_number;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getClass_time() {
        return class_time;
    }

    public void setClass_time(String class_time) {
        this.class_time = class_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExam_time() {
        return exam_time;
    }

    public void setExam_time(String exam_time) {
        this.exam_time = exam_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
