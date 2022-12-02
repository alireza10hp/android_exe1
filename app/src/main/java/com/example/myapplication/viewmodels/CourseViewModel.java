package com.example.myapplication.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.models.Course;
import com.example.myapplication.repositories.CourseRepository;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    // creating a new variable for course repository.
    private CourseRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<Course>> allCourses;

    // constructor for our view modal.
    public CourseViewModel(@NonNull Application application) {
        super(application);
        repository = new CourseRepository(application);
        allCourses = repository.getAllCourses();
    }

    // below method is use to insert the data to our repository.
    public void insert(Course model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(Course model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(Course model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllCourses() {
        repository.deleteAllCourses();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

//    public LiveData<List<Course>> getDayCourses(int day) {
//        return allCourses.getValue();
//    }
}