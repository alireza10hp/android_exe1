package com.example.myapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dialogs.SelectCourseDialog;
import com.example.myapplication.models.Course;
import com.example.myapplication.models.Day;

import java.util.List;

public class DaysRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Day> listRecyclerItem;

    public DaysRecyclerAdapter(Context context, List<Day> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.day_item, parent, false);

        return new ViewHolder((layoutView));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        ViewHolder itemViewHolder = (ViewHolder) holder;
        Day day = (Day) listRecyclerItem.get(position);

        itemViewHolder.name.setText(day.getName());
//        itemViewHolder.course_id.setText(course.getCourseId());
//        itemViewHolder.info.setText(course.getInfo());
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
//            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.day_tv);
            info = itemView.findViewById(R.id.info);
            course_id = itemView.findViewById(R.id.course_id);

//            name.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            String TAG = "oo";
            Log.i(TAG, "onClick: "+position);
//            Course course = listRecyclerItem.get(position);

//            SelectCourseDialog d = new SelectCourseDialog(context, course);
//            d.show();
        }
    }
}
