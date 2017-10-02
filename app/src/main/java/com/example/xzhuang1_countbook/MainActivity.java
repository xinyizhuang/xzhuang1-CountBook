package com.example.xzhuang1_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xzhuang1 on 2017-10-02.
 */

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";

    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewButton = (Button) findViewById(R.id.view_counter_list);
        Button editButton = (Button) findViewById(R.id.edit);
        Button addButton = (Button) findViewById(R.id.add);
        Button deleteButton = (Button) findViewById(R.id.delete);


        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewCounter = new Intent(MainActivity.this, viewCounter.class);
                startActivity(viewCounter);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCounter = new Intent(MainActivity.this, addCounter.class);
                startActivity(addCounter);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editCounter = new Intent(MainActivity.this, editCounter.class);
                startActivity(editCounter);

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteCounter = new Intent(MainActivity.this, deleteCounter.class);
                startActivity(deleteCounter);

            }
        });
    }



    protected void onStart() {
        super.onStart();
        loadFromFile();

        adapter = new ArrayAdapter<Counter>(this, R.layout.activity_view_counters, counters);

    }

    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type lisType = new TypeToken<ArrayList<Counter>>() {}.getType();
            counters = gson.fromJson(in, lisType);

        } catch (FileNotFoundException e) {
            counters = new ArrayList<Counter>();

        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters, writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }




}
