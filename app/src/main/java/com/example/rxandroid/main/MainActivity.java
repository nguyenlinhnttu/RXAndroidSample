package com.example.rxandroid.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rxandroid.R;
import com.example.rxandroid.function.ExampleOneActivity;
import com.example.rxandroid.function.ExampleThreeActivity;
import com.example.rxandroid.function.ExampleTwoActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupExampleList();
    }

    private void setupExampleList() {
        RecyclerView exampleList = (RecyclerView) findViewById(R.id.example_list);
        exampleList.setHasFixedSize(true);
        exampleList.setLayoutManager(new LinearLayoutManager(this));
        exampleList.setAdapter(new MainAdapter(this, getExamples()));
    }

    private static List<ExampleActivityAndName> getExamples() {
        List<ExampleActivityAndName> exampleActivityAndNames = new ArrayList<>();
        exampleActivityAndNames.add(new ExampleActivityAndName(
                ExampleOneActivity.class,
                "Example 1: Simple Color List"));
        exampleActivityAndNames.add(new ExampleActivityAndName(
                ExampleTwoActivity.class,
                "Example 2: Favorite Tv Shows"));
        exampleActivityAndNames.add(new ExampleActivityAndName(
                ExampleThreeActivity.class,
                "Example 3: Improved Favorite Tv Shows"));
        exampleActivityAndNames.add(new ExampleActivityAndName(
                ExampleOneActivity.class,
                "Example 4: Button Counter"));
        exampleActivityAndNames.add(new ExampleActivityAndName(
                ExampleOneActivity.class,
                "Example 5: Value Display"));
        exampleActivityAndNames.add(new ExampleActivityAndName(
                ExampleOneActivity.class,
                "Example 6: City Search"));
        return exampleActivityAndNames;
    }
}
