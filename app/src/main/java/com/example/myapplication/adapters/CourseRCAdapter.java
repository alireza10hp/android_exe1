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

public class CourseRCAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Course> listRecyclerItem;

    public CourseRCAdapter(Context context, List<Course> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
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

            SelectCourseDialog d = new SelectCourseDialog(context, course);
            d.show();
        }
    }
}