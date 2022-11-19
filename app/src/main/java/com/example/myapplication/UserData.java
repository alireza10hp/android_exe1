package com.example.myapplication;

import java.util.List;

public class UserData {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int info;
        private int course_id;
        private int course_number;
        private String name;
        private int units;
        private int capacity;
        private String instructor;
        private String class_time;
        private int id;
        private String exam_time;


        public int getInfo() {
            return info;
        }

        public void setInfo(int info) {
            this.info = info;
        }

        public int getCourse_id() {
            return course_id;
        }

        public void setCourse_id(int course_id) {
            this.course_id = course_id;
        }

        public int getCourse_number() {
            return course_number;
        }

        public void setCourse_number(int course_number) {
            this.course_number = course_number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}

