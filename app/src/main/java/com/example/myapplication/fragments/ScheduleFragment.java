package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.DaysRCAdapter;
import com.example.myapplication.adapters.DottedDividerItemDecoration;
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

        days_rc.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        days_rc.setLayoutManager(layoutManager);

        rcAdapter = new DaysRCAdapter(getContext(), schedules.get(0).getDays());
        days_rc.setAdapter(rcAdapter);

        // add decoration
//        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_divider));
//        days_rc.addItemDecoration(itemDecorator);

        DottedDividerItemDecoration decorator = new DottedDividerItemDecoration(ContextCompat.getDrawable(getContext(), R.drawable.item_divider));
        days_rc.addItemDecoration(decorator);

//        addItemsFromJSON();

        schedules.get(0).addTempCourse();
    }
}
