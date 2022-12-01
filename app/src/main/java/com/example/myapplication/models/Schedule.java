package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schedule {

    private List<Day> Days;

    private class Day {
        String name;
        List<Course> courses;

        public Day(String name) {
            this.name = name;
            this.courses = new ArrayList<>();
        }
    }

    public Schedule() {
        this.Days = Arrays.asList(new Day("شنبه"), new Day("یک‌شنبه"), new Day("دو‌شنبه"), new Day("سه‌شنبه"), new Day("چهارشنبه"));
    }

}

