package com.example.xzhuang1_countbook;

import android.database.Cursor;
import android.renderscript.Type;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzhuang1 on 2017-10-01.
 */

public class viewCounter extends AppCompatActivity{

    private static final String FILENAME = "file.sav";

    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;
    protected ListView oldCounterList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_counters);

        oldCounterList = (ListView) findViewById(R.id.counterList);

    }


    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

    }


    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            java.lang.reflect.Type lisType = new TypeToken<ArrayAdapter<Counter>>() {}.getType();
            counters = gson.fromJson(in, lisType);

        } catch (FileNotFoundException e) {
            counters = new ArrayList<Counter>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
