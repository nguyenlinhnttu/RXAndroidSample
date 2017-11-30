package com.example.rxandroid.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rxandroid.R;
import com.example.rxandroid.main.RestClient;
import com.example.rxandroid.utils.AppLog;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nguyenvanlinh on 11/30/17.
 * Lean about: Singles
 * Observables are great, but in many cases they’re kind of overkill.
 */

public class ExampleThreeActivity extends AppCompatActivity {
    private RecyclerView mTvShowListView;
    private ProgressBar mProgressBar;
    private TextView mErrorMessage;
    private StringAdapter mSimpleStringAdapter;
    private RestClient mRestClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_3);
        mRestClient = new RestClient(this);
        mErrorMessage = (TextView) findViewById(R.id.error_message);
        mProgressBar = (ProgressBar) findViewById(R.id.loader);
        mTvShowListView = (RecyclerView) findViewById(R.id.tv_show_list);
        mTvShowListView.setLayoutManager(new LinearLayoutManager(this));
        mSimpleStringAdapter = new StringAdapter(this);
        mTvShowListView.setAdapter(mSimpleStringAdapter);
        createObservableAsync();
    }

    private void createObservableAsync() {
        Single<List<String>> single = Single.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                /**
                 * Uncomment me (and comment out the line below) to see what happens when an error occurs.
                 *
                 * return RestClient.getFavoriteTvShowsWithException();
                 */
                return mRestClient.getFavoriteTvShows();
            }
        });
        single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<String> strings) {
                        displayTvShows(strings);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        displayErrorMessage();
                        e.printStackTrace();
                    }
                });

    }

    private void displayTvShows(List<String> tvShows) {
        mSimpleStringAdapter.setStrings(tvShows);
        mProgressBar.setVisibility(View.GONE);
        mTvShowListView.setVisibility(View.VISIBLE);
    }

    private void displayErrorMessage() {
        mProgressBar.setVisibility(View.GONE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }
}
