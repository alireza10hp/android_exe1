package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dialogs.SelectCourseDialog;
import com.example.myapplication.models.Course;

import java.util.List;

public class CourseDaysRCAdapter extends RecyclerView.Adapter<CourseDaysRCAdapter.CourseViewHolder> {
    private static final int TYPE = 1;
    private final Context context;
    private final List<Course> listRecyclerItem;

    public CourseDaysRCAdapter(Context context, List<Course> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.day_course_item, parent, false);

        return new CourseViewHolder((layoutView));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = listRecyclerItem.get(position);

        holder.name.setText(course.getName());
        holder.course_id.setText(course.getCourseId());
        holder.info.setText(course.getInfo());
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView info;
        public TextView course_id;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.name);
            info = itemView.findViewById(R.id.info);
            course_id = itemView.findViewById(R.id.course_id);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            Course course = listRecyclerItem.get(position);

            SelectCourseDialog d = new SelectCourseDialog(context, course);
            d.show();
        }
    }
}
