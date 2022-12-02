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

    private RecyclerView mRecyclerView;
    private List<Course> viewItems = new ArrayList<>();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String TAG = "MainActivity";

    private CourseViewModel courseViewModel;

    private MainTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;


    List<String> arrayList= new ArrayList<>();

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > arrayAdapter;


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

//        listView = (ListView) findViewById(R.id.list_view);
//
//        list = new ArrayList<>();
//        list.add("\u0631\u06cc\u0627\u0636\u06cc \u0639\u0645\u0648\u0645\u06cc \u06f1");
//        list.add("\u0631\u06cc\u0627\u0636\u06cc \u0639\u0645\u0648\u0645\u06cc \u06f2");
//        list.add("\u0645\u0639\u0627\u062f\u0644\u0627\u062a \u062f\u06cc\u0641\u0631\u0627\u0646\u0633\u06cc\u0644");
//        list.add("\u062d\u0645\u06cc\u062f\u0631\u0636\u0627 \u0641\u0646\u0627\u0626\u06cc");
//        list.add("\u0622\u0645\u0627\u0631 \u0648 \u06a9\u0627\u0631\u0628\u0631\u062f \u0622\u0646");
//        list.add("\u0627\u062d\u062a\u0645\u0627\u0644 \u0648 \u06a9\u0627\u0631\u0628\u0631\u062f \u0622\u0646");
//        list.add("\u062c\u0628\u0631 \u06f1");
//        list.add("\u062c\u0628\u0631 \u062e\u0637\u06cc \u06f1");
//        list.add("\u0622\u0634\u0646\u0627\u06cc\u06cc \u0628\u0627 \u062c\u0628\u0631 \u062e\u0637\u06cc");
//        list.add("\u0645\u062d\u0645\u062f\u0631\u0636\u0627 \u067e\u0648\u0631\u0646\u06a9\u06cc");
//        list.add("\u0645\u062d\u0645\u062f\u0631\u0636\u0627 \u067e\u0648\u0631\u0646\u06a9\u06cc");
//        list.add("\u0645\u0628\u0627\u0646\u06cc\u200c \u06a9\u0627\u0645\u067e\u06cc\u0648\u062a\u0631 \u0648 \u0628\u0631\u0646\u0627\u0645\u0647\u200c\u0646\u0648\u06cc\u0633\u06cc");
//
//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
//        listView.setAdapter(arrayAdapter);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchView.clearFocus();
//             /*   if(list.contains(query)){
//                    adapter.getFilter().filter(query);
//                }else{
//                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
//                }*/
//                return false;
//
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                arrayAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
}


