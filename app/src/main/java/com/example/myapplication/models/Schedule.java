package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schedule {
    private List<Day> Days;

    public Schedule() {
        this.Days = Arrays.asList(new Day(0, "شنبه"), new Day(1, "یک‌شنبه"), new Day(2, "دو‌شنبه"), new Day(3, "سه‌شنبه"), new Day(4, "چهارشنبه"));
    }

//    public void addTempCourse() {
//        this.Days.get(0).addTempCourse();
//        this.Days.get(1).addTempCourse();
//        this.Days.get(2).addTempCourse();
//        this.Days.get(3).addTempCourse();
//        this.Days.get(4).addTempCourse();
//    }

    public List<Day> getDays() {
        return Days;
    }
}

