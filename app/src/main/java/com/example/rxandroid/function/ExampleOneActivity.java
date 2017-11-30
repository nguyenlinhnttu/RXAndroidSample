package com.example.rxandroid.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rxandroid.R;
import com.example.rxandroid.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by nguyenvanlinh on 11/30/17.
 * Lean about:
 * - Observable
 * - Subscribers
 */

public class ExampleOneActivity extends AppCompatActivity {
    RecyclerView mColorListView;
    StringAdapter mStringAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_1);
        mColorListView = (RecyclerView) findViewById(R.id.color_list);
        mColorListView.setLayoutManager(new LinearLayoutManager(this));
        mStringAdapter = new StringAdapter(this);
        mColorListView.setAdapter(mStringAdapter);
        createObservable();
    }

    private void createObservable() {
        Observable <List<String>> listObservable = Observable.just(getColorList());
        listObservable.subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<String> strings) {
                AppLog.log(String.valueOf(strings));
                mStringAdapter.setStrings(strings);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                AppLog.log("onError");
            }

            @Override
            public void onComplete() {
                AppLog.log("onComplete");
            }
        });
    }

    private List<String> getColorList() {
        ArrayList<String> colors = new ArrayList<>();
        for (int i = 0; i < 10000; i ++) {
            colors.add("blue" + String.valueOf(i));
        }
        return colors;
    }
}
