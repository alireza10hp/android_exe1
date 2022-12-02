package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dialogs.SelectCourseDialog;
import com.example.myapplication.models.Course;
import com.example.myapplication.viewmodels.CourseViewModel;

import java.util.List;

public class CourseDaysRCAdapter extends ListAdapter<Course, CourseDaysRCAdapter.CourseViewHolder> {
    private static final int TYPE = 1;
    private final Context context;
//    private final List<Course> listRecyclerItem;

    private final CourseViewModel viewModel;

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<Course> DIFF_CALLBACK = new DiffUtil.ItemCallback<Course>() {
        @Override
        public boolean areItemsTheSame(Course oldItem, Course newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Course oldItem, Course newItem) {
            return oldItem.getCourseId().equals(newItem.getCourseId());
        }
    };

    public CourseDaysRCAdapter(Context context, CourseViewModel viewModel) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.viewModel = viewModel;
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
//        Course course = listRecyclerItem.get(position);
        Course course = getItem(position);

        holder.name.setText(course.getName());
        holder.course_id.setText(course.getCourseId());
        holder.info.setText(course.getInfo());
    }

//    @Override
//    public int getItemCount() {
//        return listRecyclerItem.size();
//    }

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
//            Course course = listRecyclerItem.get(position);

//            SelectCourseDialog d = new SelectCourseDialog(context, viewModel, course);
//            d.show();
        }
    }
}
