package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    //    // ArrayList for person names, email Id's and mobile numbers
//    ArrayList<String> personNames = new ArrayList<>();
//    ArrayList<String> emailIds = new ArrayList<>();
//    ArrayList<String> mobileNumbers = new ArrayList<>();
    ArrayList<Integer> info = new ArrayList<>();
    ArrayList<Integer> course_id = new ArrayList<>();
    ArrayList<Integer> course_number = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> units = new ArrayList<>();
    ArrayList<Integer> capacity = new ArrayList<>();
    ArrayList<String> instructor = new ArrayList<>();
    ArrayList<String> class_time = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<String> exam_time = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            // fetch JSONArray named users
            JSONArray unitArray = obj.getJSONArray("units");
            // implement for loop for getting users list data
            for (int i = 0; i < unitArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject userDetail = unitArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                info.add(userDetail.getInt("info"));
                course_id.add(userDetail.getInt("course_id"));
                course_number.add(userDetail.getInt("course_number"));
                name.add(userDetail.getString("name"));
                units.add(userDetail.getInt("units"));
                capacity.add(userDetail.getInt("capacity"));
                instructor.add(userDetail.getString("instructor"));
                class_time.add(userDetail.getString("class_time"));
                id.add(userDetail.getInt("id"));
                exam_time.add(userDetail.getString("exams_time"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter( info, course_id, course_number, name, units, capacity, instructor, class_time, id, exam_time);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("3.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "utf-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}


