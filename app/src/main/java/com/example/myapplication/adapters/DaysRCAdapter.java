package com.example.myapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Course;
import com.example.myapplication.models.Day;
import com.example.myapplication.viewmodels.CourseViewModel;

import java.util.ArrayList;
import java.util.List;

public class DaysRCAdapter extends RecyclerView.Adapter<DaysRCAdapter.DayViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    LifecycleOwner lifecycleOwner;
    private final List<Day> listRecyclerItem;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    private final CourseViewModel viewModel;

    public DaysRCAdapter(Context context, LifecycleOwner viewLifecycleOwner, List<Day> listRecyclerItem, CourseViewModel viewModel) {
        this.context = context;
        this.lifecycleOwner = viewLifecycleOwner;
        this.listRecyclerItem = listRecyclerItem;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.day_item, parent, false);

        return new DayViewHolder((layoutView));
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        Day day = listRecyclerItem.get(position);

        String TAG="+++++++++";
        Log.i(TAG, "onBindViewHolder: "+day.getId());
        holder.name.setText(day.getName());

        // Here we have assigned the layout
        // as LinearLayout with vertical orientation
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.dayCourseRc.getContext(), LinearLayoutManager.VERTICAL, false);

        // Since this is a nested layout, so
        // to define how many child items
        // should be prefetched when the
        // child RecyclerView is nested
        // inside the parent RecyclerView,
        // we use the following method
//        layoutManager.setInitialPrefetchItemCount(day.getCourseSize());
//        layoutManager.setInitialPrefetchItemCount(getItemCount());

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool
        CourseDaysRCAdapter courseDaysRCAdapter = new CourseDaysRCAdapter(holder.dayCourseRc.getContext(), viewModel);
//        CourseDaysRCAdapter courseDaysRCAdapter = new CourseDaysRCAdapter(holder.dayCourseRc.getContext(), getCurrentList(), viewModel);
        holder.dayCourseRc.setLayoutManager(layoutManager);
        holder.dayCourseRc.setAdapter(courseDaysRCAdapter);
        holder.dayCourseRc.setRecycledViewPool(viewPool);

        // room
        viewModel.getAllCourses().observe(lifecycleOwner, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                List<Course> dayCourses = getDayCourses(courses, day.getId());
                String TAG="----------";
                Log.i(TAG, "onChanged: "+day.getId()+", "+dayCourses.size());
                layoutManager.setInitialPrefetchItemCount(dayCourses.size());
                courseDaysRCAdapter.submitList(dayCourses);
//                courseDaysRCAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView info;
        public TextView course_id;
        private RecyclerView dayCourseRc;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.day_tv);
            info = itemView.findViewById(R.id.info);
            course_id = itemView.findViewById(R.id.course_id);
            dayCourseRc = itemView.findViewById(R.id.day_course_rc);
        }
    }

    public List<Course> getDayCourses(List<Course> courses, int day) {
        ArrayList<Course> courseArrayList = new ArrayList<>();
        for (Course course :
                courses) {
            if (course.getDays().contains(day)) {
                courseArrayList.add(course);
            }
        }
        return courseArrayList;
    }
}
