package com.example.myapplication.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

@Entity(tableName = "course_table")
public class Course {

    @PrimaryKey()
    private int id;

    private String info;
    private String courseId;
    private String courseNumber;
    private String name;
    private int units;
    private int capacity;
    private String instructor;
    private String classTime;
    private String examTime;
    private ArrayList<Float> startTimes = new ArrayList<>();
    private ArrayList<Float> endTimes = new ArrayList<>();
    private ArrayList<Integer> days = new ArrayList<>();

    public class Day {
        String start;
        String end;
        String day;
    }

//    private int[] days;

    @Ignore
    public Course(String name, String info, String courseId, String courseNumber) {
        this.name = name;
        this.info = info;
        this.courseId = courseId;
        this.courseNumber = courseNumber;
    }

    public Course(String info, String courseId, String courseNumber, String name,
                  int units, int capacity, String instructor, String classTime, int id, String examTime, ArrayList<Float> startTimes, ArrayList<Float> endTimes, ArrayList<Integer> days) {
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
        this.startTimes = startTimes;
        this.endTimes = endTimes;
        this.days = days;

//        this.initTimes();
    }

    private void initTimes() {
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
//        classTimes.deleteCharAt(classTimes.length() - 1);
        this.classTime = classTimes.toString().replaceAll("\\.", ":");
        if (this.classTime.contains(".5")) {
            this.classTime = this.classTime.replaceAll(".5", ":30");
        }
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

    public void setDays(ArrayList<Integer> days) {
        this.days = days;
    }

    public void setStartTimes(ArrayList<Float> startTimes) {
        this.startTimes = startTimes;
    }

    public void setEndTimes(ArrayList<Float> endTimes) {
        this.endTimes = endTimes;
    }

    @NonNull
    @Override
    public String toString() {
        return id + "," + name + "," + courseNumber + "," + days + "," + startTimes + "," + endTimes + "," + classTime;
    }

    public static class Converters {
        @TypeConverter
        public ArrayList<Integer> fromString(String value) {
            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            return new Gson().fromJson(value, listType);
        }

        @TypeConverter
        public String fromArrayList(ArrayList<Integer> list) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return json;
        }
    }

    public static class FloatConverters {
        @TypeConverter
        public ArrayList<Float> fromString(String value) {
            Type listType = new TypeToken<ArrayList<Float>>() {
            }.getType();
            return new Gson().fromJson(value, listType);
        }

        @TypeConverter
        public String fromArrayList(ArrayList<Float> list) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return json;
        }
    }
}
