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
import com.example.myapplication.dao.CourseDao;
import com.example.myapplication.models.Course;
import com.example.myapplication.viewmodels.CourseViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectCourseDialog extends Dialog implements View.OnClickListener {

    private final Context context;
    private final Course course;

    private Button cancel, select;
    private TextView name, instructor, department, courseId, unit, capacity, classTimes, info;

    ArrayList<Course> selectedCourses = new ArrayList<>();

    private final CourseViewModel viewModel;

    public SelectCourseDialog(@NonNull Context context, CourseViewModel viewModel, Course course) {
        super(context);
        this.context = context;
        this.course = course;
        this.viewModel = viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.select_course_dig);

        cancel = (Button) findViewById(R.id.btn_cancel);
        select = (Button) findViewById(R.id.btn_select);
        cancel.setOnClickListener(this);
        select.setOnClickListener(this);

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
            case R.id.btn_select:
                if (viewModel.getAllCourses().getValue().size() == 0 || !isOverlapped(course)) {
//                    selectedCourses.add(course);
                    saveCourse(course);
                } else {
                    Toast.makeText(context, "برنامه درس انتخابی با دیگر دروس انتخاب شده تداخل دارد", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        dismiss();
    }

    public boolean isOverlapped(Course course) {
        List<Course> courses = viewModel.getAllCourses().getValue();
        for (int i = 0; i < courses.size(); i++) {
            int dayLength = courses.get(i).getDays().size();
            for (int j = 0; j < dayLength; j++) {
                for (int k = 0; k < course.getDays().size(); k++) {
                    if (Objects.equals(courses.get(i).getDays().get(j), course.getDays().get(k))) {
                        if (courses.get(i).getEndTimes().get(j) > course.getStartTimes().get(k) ||
                                courses.get(i).getStartTimes().get(j) < course.getEndTimes().get(k))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private void saveCourse(Course course) {
        viewModel.insert(course);
    }
}
