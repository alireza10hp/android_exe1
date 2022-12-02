package com.example.myapplication.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CourseRCAdapter;
import com.example.myapplication.adapters.MainTabAdapter;
import com.example.myapplication.fragments.CourseListFragment;
import com.example.myapplication.fragments.ScheduleFragment;
import com.example.myapplication.models.Course;
import com.example.myapplication.R;
import com.example.myapplication.viewmodels.CourseViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CourseViewModel courseViewModel;

    private MainTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        adapter = new MainTabAdapter(this);
        adapter.addFragment(new CourseListFragment(courseViewModel), getString(R.string.tab_course_list));
        adapter.addFragment(new ScheduleFragment(courseViewModel), getString(R.string.tab_schedule));
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, true, (tab, position) -> tab.setText(adapter.getTabTitle(position))).attach();
    }
}


