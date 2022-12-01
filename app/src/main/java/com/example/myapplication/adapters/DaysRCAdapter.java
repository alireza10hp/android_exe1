package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Day;

import java.util.List;

public class DaysRCAdapter extends RecyclerView.Adapter<DaysRCAdapter.DayViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Day> listRecyclerItem;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public DaysRCAdapter(Context context, List<Day> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
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
        layoutManager.setInitialPrefetchItemCount(day.getCourseSize());

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool
        CourseDaysRCAdapter courseDaysRCAdapter = new CourseDaysRCAdapter(holder.dayCourseRc.getContext(), day.getCourses());
        holder.dayCourseRc.setLayoutManager(layoutManager);
        holder.dayCourseRc.setAdapter(courseDaysRCAdapter);
        holder.dayCourseRc.setRecycledViewPool(viewPool);
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
}
