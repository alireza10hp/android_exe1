package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<Integer> info;
    ArrayList<Integer> course_id;
    ArrayList<Integer> course_number;
    ArrayList<String> name;
    ArrayList<Integer> units;
    ArrayList<Integer> capacity;
    ArrayList<String> instructor;
    ArrayList<String> class_time;
    ArrayList<Integer> id;
    ArrayList<String> exam_time;


    public CustomAdapter(ArrayList<Integer> info,
                         ArrayList<Integer> course_id,
                         ArrayList<Integer> course_number,
                         ArrayList<String> name,
                         ArrayList<Integer> units,
                         ArrayList<Integer> capacity,
                         ArrayList<String> instructor,
                         ArrayList<String> class_time,
                         ArrayList<Integer> id,
                         ArrayList<String> exam_time) {
        this.info = info;
        this.course_id = course_id;
        this.course_number = course_number;
        this.name = name;
        this.units = units;
        this.capacity = capacity;
        this.instructor = instructor;
        this.class_time = class_time;
        this.id = id;
        this.exam_time = exam_time;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    //TODO edit
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.info.setText(info.get(position));
        holder.course_id.setText(course_id.get(position));
        holder.course_number.setText(course_number.get(position));
        holder.name.setText(name.get(position));
        holder.units.setText(units.get(position));
        holder.capacity.setText(capacity.get(position));
        holder.instructor.setText(instructor.get(position));
        holder.class_time.setText(class_time.get(position));
        holder.id.setText(id.get(position));
        holder.exam_time.setText(exam_time.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
//                Toast.makeText(this, name.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return info.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView info ,course_id,course_number, name,units,capacity,instructor,class_time,id,exam_time;

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            info = (TextView) itemView.findViewById(R.id.info);
            course_id = (TextView) itemView.findViewById(R.id.course_id);
            course_number = (TextView) itemView.findViewById(R.id.course_number);
            name = (TextView) itemView.findViewById(R.id.name);
            units = (TextView) itemView.findViewById(R.id.units);
            capacity = (TextView) itemView.findViewById(R.id.capacity);
            instructor = (TextView) itemView.findViewById(R.id.instructor);
            class_time = (TextView) itemView.findViewById(R.id.class_time);
            id = (TextView) itemView.findViewById(R.id.id);
            exam_time = (TextView) itemView.findViewById(R.id.exam_time);

        }
    }
}
