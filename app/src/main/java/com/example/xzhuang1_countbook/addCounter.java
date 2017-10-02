package com.example.xzhuang1_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by xzhuang1 on 2017-10-02.
 */

public class addCounter extends AppCompatActivity{

    private static final String FILENAME = "file.sav";

    private EditText counterName;
    private EditText initValue;
    private EditText counterComment;
    private ArrayList<Counter> counters = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counters);

        Button addButton = (Button) findViewById(R.id.ADD);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counterName = (EditText) findViewById(R.id.name);
                initValue = (EditText) findViewById(R.id.initVal);
                counterComment = (EditText) findViewById(R.id.comment);

                String counter_Name = counterName.getText().toString();
                Integer init_value = Integer.parseInt(initValue.getText().toString());
                String counter_comment = counterComment.getText().toString();

                counters.add(new Counter(counter_Name, init_value, counter_comment));
                saveInFile();
                finish();

            }
        });
    }

    public void saveInFile(){
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
