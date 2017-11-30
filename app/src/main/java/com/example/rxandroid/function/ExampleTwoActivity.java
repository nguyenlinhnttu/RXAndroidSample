package com.example.rxandroid.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.rxandroid.R;
import com.example.rxandroid.main.RestClient;
import com.example.rxandroid.utils.AppLog;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nguyenvanlinh on 11/30/17.
 * Lean about: Asynchronous Loading
 */

public class ExampleTwoActivity extends AppCompatActivity {
    private RecyclerView mTvShowListView;
    private  StringAdapter mStringAdapter;
    private RestClient mRestClient;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_2);
        mRestClient = new RestClient(this);
        mProgressBar = (ProgressBar) findViewById(R.id.loader);
        mTvShowListView = (RecyclerView) findViewById(R.id.tv_show_list);
        mTvShowListView.setLayoutManager(new LinearLayoutManager(this));
        mStringAdapter = new StringAdapter(this);
        mTvShowListView.setAdapter(mStringAdapter);
        createObservableAsync();
    }

    private void createObservableAsync() {
        Observable<List<String>> obServableAsyn = Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return mRestClient.getFavoriteTvShows();
            }
        });
        obServableAsyn
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        AppLog.log("onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull List<String> strings) {
                        AppLog.log("onNext" + strings.toString());
                        displayTvShows(strings);
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

    private void displayTvShows(List<String> tvShows) {
        mStringAdapter.setStrings(tvShows);
        mProgressBar.setVisibility(View.GONE);
        mTvShowListView.setVisibility(View.VISIBLE);
    }
}
