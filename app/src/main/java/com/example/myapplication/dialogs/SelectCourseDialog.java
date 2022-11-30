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

public class SelectCourseDialog extends Dialog implements View.OnClickListener {

    private final Context context;
    private final Course course;

    private Button cancel, select;
    private TextView name, info, courseNumber;

    public SelectCourseDialog(@NonNull Context context, Course course) {
        super(context);
        this.context = context;
        this.course = course;
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
                Toast.makeText(context, "selected "+course.getCourseId(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        dismiss();
    }
}
