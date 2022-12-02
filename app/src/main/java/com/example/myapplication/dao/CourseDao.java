package com.example.myapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.models.Course;

import java.util.List;

@androidx.room.Dao
public interface CourseDao {

    // below method is use to
    // add data to database.
    @Insert
    void insert(Course model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(Course model);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(Course model);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM course_table")
    void deleteAllCourses();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM course_table ORDER BY courseNumber ASC")
    LiveData<List<Course>> getAllCourses();
}