package com.example.myapplication.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.models.Course;
import com.example.myapplication.viewmodels.CourseViewModel;

import java.util.ArrayList;

public class DeleteCourseDialog extends Dialog implements View.OnClickListener {

    private final Context context;
    private final Course course;

    private Button cancel, delete;
    private TextView name, instructor, department, courseId, unit, capacity, classTimes, info;

    ArrayList<Course> selectedCourses = new ArrayList<>();

    private final CourseViewModel viewModel;

    public DeleteCourseDialog(@NonNull Context context, CourseViewModel viewModel, Course course) {
        super(context);
        this.context = context;
        this.course = course;
        this.viewModel = viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.delete_course_dig);

        cancel = (Button) findViewById(R.id.btn_cancel);
        delete = (Button) findViewById(R.id.btn_delete);
        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);

        name = (TextView) findViewById(R.id.name);
        name.setText(course.getName());
        instructor = (TextView) findViewById(R.id.instructor);
        instructor.setText(course.getInstructor());
        department = (TextView) findViewById(R.id.department);
        department.setText(course.getDepartment());
        courseId = (TextView) findViewById(R.id.course_id);
        courseId.setText(course.getCourseId());
        unit = (TextView) findViewById(R.id.unit);
        unit.setText(String.valueOf(course.getUnits()));
        capacity = (TextView) findViewById(R.id.capacity);
        capacity.setText(String.valueOf(course.getCapacity()));
        classTimes = (TextView) findViewById(R.id.class_times);
        classTimes.setText(course.getClassTime());
        info = (TextView) findViewById(R.id.info);
        info.setText(course.getInfo());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_delete:
                deleteCourse(course);
                break;
            default:
                break;
        }
        dismiss();
    }

    private void deleteCourse(Course course) {
        viewModel.delete(course);
    }
}