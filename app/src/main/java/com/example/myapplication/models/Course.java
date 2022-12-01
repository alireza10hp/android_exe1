package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class Course {

    private String info;
    private String courseId;
    private String courseNumber;
    private String name;
    private int units;
    private int capacity;
    private String instructor;
    private String classTime;
    private int id;
    private String examTime;
    private ArrayList<Float> startTimes = new ArrayList<>();
    private ArrayList<Float> endTimes = new ArrayList<>();
    private ArrayList<Integer> days = new ArrayList<>();

    public Course(String info, String courseId, String courseNumber, String name,
                  int units, int capacity, String instructor, String classTime, int id, String examTime) {
        this.info = info;
        this.courseId = courseId;
        this.courseNumber = courseNumber;
        this.name = name;
        this.units = units;
        this.capacity = capacity;
        this.instructor = instructor;
        this.classTime = classTime;
        this.id = id;
        this.examTime = examTime;

    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
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

    public String getClassTime() {
        StringBuilder classTimes = new StringBuilder();
        String day = "";
        Pattern p = Pattern.compile("\\d+\\.*\\d*");
        Matcher m = p.matcher(classTime);
        int check = 0;
        while (m.find()) {
            if (m.group().equals("0")) {
                day = "sat";
                classTimes.deleteCharAt(classTimes.length() - 1);
                classTimes.append(" ").append(day).append("\n");
                days.add(0);

            } else if (m.group().equals("1")) {
                day = "sun";
                classTimes.deleteCharAt(classTimes.length() - 1);
                classTimes.append(" ").append(day).append("\n");
                days.add(1);

            } else if (m.group().equals("2")) {
                day = "mon";
                classTimes.deleteCharAt(classTimes.length() - 1);
                classTimes.append(" ").append(day).append("\n");
                days.add(2);

            } else if (m.group().equals("3")) {
                day = "tue";
                classTimes.deleteCharAt(classTimes.length() - 1);
                classTimes.append(" ").append(day).append("\n");
                days.add(3);

            } else if (m.group().equals("4")) {
                day = "wed";
                classTimes.deleteCharAt(classTimes.length() - 1);
                classTimes.append(" ").append(day).append("\n");
                days.add(4);

            } else if (m.group().equals("5")) {
                day = "thu";
                classTimes.deleteCharAt(classTimes.length() - 1);
                classTimes.append(" ").append(day).append("\n");
                days.add(5);

            } else if (m.group().equals("6")) {
                day = "fri";
                classTimes.deleteCharAt(classTimes.length() - 1);
                classTimes.append(" ").append(day).append("\n");
                days.add(6);

            } else {
                classTimes.append(m.group()).append("-");
                if (check == 0) {
                    check = 1;
                    startTimes.add(Float.parseFloat(m.group()));
                } else {
                    check = 0;
                    endTimes.add(Float.parseFloat(m.group()));
                }
            }
        }
        classTimes.deleteCharAt(classTimes.length() - 1);
        this.classTime = classTimes.toString().replaceAll("\\.", ":");
        if (this.classTime.contains(".5")) {
            this.classTime = this.classTime.replaceAll(".5", ":30");
        }
        return classTime;

    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Float> getStartTimes() {
        return startTimes;
    }

    public ArrayList<Float> getEndTimes() {
        return endTimes;
    }

    public ArrayList<Integer> getDays() {
        return days;
    }
}
