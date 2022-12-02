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
import java.util.Objects;

public class SelectCourseDialog extends Dialog implements View.OnClickListener {

    private final Context context;
    private final Course course;

    private Button cancel, select;
    private TextView name, info, courseNumber;

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

        name = (TextView) findViewById(R.id.course_name);
        name.setText(course.getName());
        info = (TextView) findViewById(R.id.course_info);
        info.setText(course.getInfo());
        courseNumber = (TextView) findViewById(R.id.course_number);
        courseNumber.setText(course.getCourseNumber());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_select:
                if (selectedCourses.size() == 0 || !isOverlapped()) {
                    selectedCourses.add(course);
                    saveCourse(course);
                    Toast.makeText(context, "selected " + course.getCourseId(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "overlap ! ", Toast.LENGTH_SHORT).show();

                }
                break;
            default:
                break;
        }
        dismiss();
    }

    public boolean isOverlapped() {
//        for (int i = 0; i < selectedCourses.size(); i++) {
//            int dayLength = selectedCourses.get(i).getDays().size();
//            for (int j = 0; j < dayLength; j++) {
//                for (int k = 0; k < course.getDays().size(); k++) {
//                    if (Objects.equals(selectedCourses.get(i).getDays().get(j), course.getDays().get(k))) {
//                        if (selectedCourses.get(i).getEndTimes().get(j) > course.getStartTimes().get(k) ||
//                                selectedCourses.get(i).getStartTimes().get(j) < course.getEndTimes().get(k))
//                            return true;
//                    }
//                }
//            }
//        }
        return false;
    }

    private void saveCourse(Course course) {
        viewModel.insert(course);

        // displaying a toast message after adding the data
        Toast.makeText(context, "Course has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }
}
