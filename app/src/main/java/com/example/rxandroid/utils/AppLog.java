package com.example.rxandroid.utils;

import android.util.Log;

import com.example.rxandroid.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Framework PHATVT
 *
 * @author PhatVan ヴァン  タン　ファット
 * @since: 11-201
 */


public class AppLog {
    //TAG Class
    static String TAG = "AppLog";


    // --------------------------------------------------------
    // Show Log info
    synchronized public static void log(String content) {

        if (BuildConfig.DEBUG) {
            Log.i(TAG, content);
        }
    }

    synchronized public static void log(String tag, String content) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, content);
        }
    }

    synchronized public static void logJson(String tag, String title, String data) {
        if (BuildConfig.DEBUG) {
            try {
                Log.i(tag, title);
                JSONObject obj = new JSONObject(data);
                Log.d(tag, obj.toString(4));
            } catch (JSONException e) {
                try {
                    JSONArray jsonArray = new JSONArray(data);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = new JSONObject(jsonArray.get(i).toString());
                        Log.d(tag, obj.toString(4));
                    }
                } catch (JSONException e1) {
                    Log.d(TAG, "Data: " + data);
                }
            }
        }
    }

    // --------------------------------------------------------
    // Show Log Error
    synchronized public static void log_error(String content) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, content);
        }
    }

    // --------------------------------------------------------
    // Show Log URL
    synchronized public static void log_url(String content) {
        if (BuildConfig.DEBUG) {
            Log.i("", "--------------------------------------");
            Log.i(TAG, content);
            Log.i("", "--------------------------------------");
        }
    }
}
