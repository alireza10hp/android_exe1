package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.DaysRecyclerAdapter;
import com.example.myapplication.adapters.RecyclerAdapter;
import com.example.myapplication.models.Day;
import com.example.myapplication.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {

    private RecyclerView days_rc;
    private List<Schedule> schedules = new ArrayList<>();

    private RecyclerView.Adapter rcAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set default schedule
        // todo check schedules count
        schedules.add(new Schedule());

        days_rc = (RecyclerView) getView().findViewById(R.id.schedule_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        days_rc.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        days_rc.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        rcAdapter = new DaysRecyclerAdapter(getContext(), schedules.get(0).getDays());
        days_rc.setAdapter(rcAdapter);

//        addItemsFromJSON();

        schedules.get(0).addTempCourse();
    }
}
