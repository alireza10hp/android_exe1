package com.example.myapplication.adapters;

import android.app.Dialog;
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

public class CourseRCAdapter extends ListAdapter<Course, RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Course> listRecyclerItem;

    private CourseViewModel viewModel;

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

    public CourseRCAdapter(Context context, List<Course> listRecyclerItem, CourseViewModel viewModel) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
        this.viewModel = viewModel;
    }

//    public static class ItemViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView name;
//        private TextView course_id;
//        private TextView info;
//
//        public ItemViewHolder(@NonNull View itemView) {
//            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.name);
//            course_id = (TextView) itemView.findViewById(R.id.course_id);
//            info = (TextView) itemView.findViewById(R.id.info);
//        }
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_item, viewGroup, false);

        return new ViewHolder((layoutView));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        ViewHolder itemViewHolder = (ViewHolder) viewHolder;
        Course course = listRecyclerItem.get(i);

        itemViewHolder.name.setText(course.getName());
        itemViewHolder.course_id.setText(course.getCourseId());
        itemViewHolder.info.setText(course.getInfo());

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView info;
        public TextView course_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.name);
            info = itemView.findViewById(R.id.info);
            course_id = itemView.findViewById(R.id.course_id);

            name.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            Course course = listRecyclerItem.get(position);

            SelectCourseDialog d = new SelectCourseDialog(context, viewModel, course);
            d.show();
        }
    }
}