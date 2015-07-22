package com.example.networkdemo.network;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONObject;

import java.io.File;

/**
 * Created by yajnesh on 21-Jan-15.
 */
public class NetworkHelper {

    public static void getAnd(Context context, APIObserver observer) {

        String url = NetworkConstants.BASE_URL + "/" + NetworkConstants.Communicate.GET_DEMO.url();
        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.GET, NetworkConstants.Communicate.GET_DEMO.apiIndex(), observer);

    }


//
//    public static void sendReports(Context context, String url, int apiIndex, JSONObject jsonObject, APIObserver observer) {
//        url = NetworkConstants.BASE_URL + "/" + url;
//        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.POST,
//                apiIndex, jsonObject, observer);
//
//
//    }

  

    public static void destroyAllRequests(Context context) {
        VolleyHelper.getInstance(context).destroyAllRequests();

    }


}
