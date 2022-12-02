package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class Day {

    private int id;
    private String name;
//    private List<Course> courses;

    public Day(int id, String name) {
        this.id = id;
        this.name = name;

//        this.courses = new ArrayList<>();
    }

//    public void addTempCourse() {
//        this.courses.add(new Course(this.name + " course", "temp info", "12345-1", "12345"));
//        this.courses.add(new Course(this.name + " course", "temp info", "12345-1", "12345"));
//        this.courses.add(new Course(this.name + " course", "temp info", "12345-1", "12345"));
//    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    //    public List<Course> getCourses() {
//        return courses;
//    }

//    public int getCourseSize() {
//        return courses.size();
//    }
}
