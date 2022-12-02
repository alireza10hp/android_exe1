package com.example.myapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.adapters.CourseRCAdapter;
import com.example.myapplication.models.Course;
import com.example.myapplication.viewmodels.CourseViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseListFragment extends Fragment {

    private Map<String, Integer> departmentRaw = new HashMap<String, Integer>() {
        {
            put("ریاضی", R.raw.c3);
            put("فیزیک", R.raw.c5);
            put("کامپیوتر", R.raw.c38);
        }
    };

    private Map<String, List<Course>> departmentCourses = new HashMap<>();

    private List<Course> viewItems = new ArrayList<>();

    private RecyclerView mRecyclerView;
//    private List<Course> viewItems = new ArrayList<>();

    private CourseRCAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String TAG = "MainActivity";

    private final CourseViewModel courseViewModel;

    ListView listView;
    SearchView searchView;

    ArrayList<String> list;
    ArrayAdapter<Course> searchAdapter;

    public CourseListFragment(CourseViewModel courseViewModel) {
        this.courseViewModel = courseViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        readDepCourses();

        searchView = (SearchView) getView().findViewById(R.id.search);
//        listView = (ListView) getView().findViewById(R.id.list_view);

//        searchAdapter = new ArrayAdapter<Course>(getContext(), android.R.layout.simple_list_item_1, viewItems);
//        listView.setAdapter(searchAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                if (list.contains(query)) {
//                    searchAdapter.getFilter().filter(query);
//                } else {
//                    Toast.makeText(getContext(), "No Match found", Toast.LENGTH_LONG).show();
//                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                searchAdapter.getFilter().filter(newText);
                filter(newText);
                return false;
            }
        });

        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i(TAG, "---------------------onFocusChange: " + hasFocus);
                if (hasFocus) {
                    listView.setVisibility(View.VISIBLE);
                } else {
                    listView.setVisibility(View.GONE);
                }
            }
        });

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CourseRCAdapter(getContext(), viewItems, courseViewModel);
        mRecyclerView.setAdapter(mAdapter);

        // room
        courseViewModel.getAllCourses().observe(getViewLifecycleOwner(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                mAdapter.submitList(courses);
            }
        });

//        Log.i(TAG, "onViewCreated: " + viewItems.get(0));
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Course> filteredList = new ArrayList<Course>();

        // running a for loop to compare elements.
        for (Course item : viewItems) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.toString().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
//            mAdapter.filterList(viewItems);
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            mAdapter.filterList(filteredList);
        }
    }

    private void readDepCourses() {

        for (Map.Entry<String, Integer> entry : departmentRaw.entrySet()) {
            addItemsFromJSON(entry.getValue(), entry.getKey());
        }

    }

    private void addItemsFromJSON(int rawId, String dep) {
        try {


            String jsonDataString = readJSONDataFromFile(rawId);
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String info = itemObj.getString("info");
                String course_id = itemObj.getString("course_id");
                String course_number = itemObj.getString("course_number");
                String name = itemObj.getString("name");
                int units = itemObj.getInt("units");
                int capacity = itemObj.getInt("capacity");
                String instructor = itemObj.getString("instructor");
                String class_time = itemObj.getString("class_times");
                int id = itemObj.getInt("id");
                String exam_time = itemObj.getString("exam_time");

                ArrayList<Float> startTimes = new ArrayList<>();
                ArrayList<Float> endTimes = new ArrayList<>();
                ArrayList<Integer> days = new ArrayList<>();

                JSONArray times = new JSONArray(class_time);
                for (int j = 0; j < times.length(); j++) {
                    JSONObject obj = times.getJSONObject(j);
                    startTimes.add(Float.parseFloat(obj.getString("start")));
                    endTimes.add(Float.parseFloat(obj.getString("end")));
                    days.add(Integer.parseInt(obj.getString("day")));
                }

                Course course = new Course(info, course_id, course_number, name, units, capacity, instructor, class_time, id, exam_time, startTimes, endTimes, days, dep);
                viewItems.add(course);
            }

            departmentCourses.put(dep, viewItems);

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile(int rawId) throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();


        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(rawId);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}

