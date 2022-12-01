package com.example.myapplication.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.adapters.MainTabAdapter;
import com.example.myapplication.fragments.CourseListFragment;
import com.example.myapplication.fragments.ScheduleFragment;
import com.example.myapplication.models.Course;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Course> viewItems = new ArrayList<>();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String TAG = "MainActivity";

    private MainTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        adapter = new MainTabAdapter(this);
        adapter.addFragment(new CourseListFragment(), getString(R.string.tab_course_list));
        adapter.addFragment(new ScheduleFragment(), getString(R.string.tab_schedule));
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, true, (tab, position) -> tab.setText(adapter.getTabTitle(position))).attach();
    }


}