package com.codecraft.mcs.network;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONObject;

import java.io.File;

/**
 * Created by yajnesh on 21-Jan-15.
 */
public class NetworkHelper {

    public static void getIncidents(Context context, int apiIndex, APIObserver observer) {

        String url = NetworkConstants.BASE_URL + "/" + NetworkConstants.Communicate.GET_INCIDENTS.url();
        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.GET, apiIndex, observer);

    }

    public static void getUserName(Context context, int apiIndex, APIObserver observer) {

        String url = NetworkConstants.BASE_URL + "/" + NetworkConstants.Communicate.GET_USERNAMES.url();
        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.GET, apiIndex, observer);

    }

    public static void getTemplates(Context context, int apiIndex, APIObserver observer) {

        String url = NetworkConstants.BASE_URL + "/" + NetworkConstants.Communicate.GET_TEMPLATES.url();
        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.GET, apiIndex, observer);

    }

    public static void getWards(Context context, int apiIndex, APIObserver observer) {

        String url = NetworkConstants.BASE_URL + "/" + NetworkConstants.Communicate.GET_WARDS.url();
        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.GET, apiIndex, observer);

    }

    public static void getSources(Context context, int apiIndex, APIObserver observer) {

        String url = NetworkConstants.BASE_URL + "/" + NetworkConstants.Communicate.GET_SOURCES.url();
        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.GET, apiIndex, observer);

    }

    public static void getSourceActionPlans(Context context, int apiIndex, APIObserver observer) {

        String url = NetworkConstants.BASE_URL + "/" + NetworkConstants.Communicate.GET_SOURCE_ACTION_PLANS.url();
        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.GET, apiIndex, observer);

    }

    public static void getSourceActionReviews(Context context, int apiIndex, APIObserver observer) {

        String url = NetworkConstants.BASE_URL + "/" + NetworkConstants.Communicate.GET_SOURCE_ACTION_REVIEWS.url();
        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.GET, apiIndex, observer);

    }

    public static void getIncidentRevisits(Context context, int apiIndex, APIObserver observer) {

        String url = NetworkConstants.BASE_URL + "/" + NetworkConstants.Communicate.GET_INCIDENT_REVISITS.url();
        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.GET, apiIndex, observer);

    }


    public static void sendReports(Context context, String url, int apiIndex, JSONObject jsonObject, APIObserver observer) {
        url = NetworkConstants.BASE_URL + "/" + url;
        VolleyHelper.getInstance(context).asyncTask(url, Request.Method.POST,
                apiIndex, jsonObject, observer);


    }

    public static void uploadPhoto(Context context, int apiIndex, APIObserver observer, File file, String fileName) {

        //String url = NetworkConstants.BASE_URL + "/" + NetworkConstants.URL.UPLOAD_PHOTO.url();
        String url = "http://10.0.0.73/fup/upload.php";
        VolleyHelper.getInstance(context).fileUpload(url, apiIndex, observer, file, fileName);

    }

    public static void sendSupervisorNameAndPassword(Context context,JSONObject jsonObject,int apiIndex,APIObserver observer){

        String url=NetworkConstants.BASE_URL+"/" + NetworkConstants.Communicate.SEND_SUPERVISOR_NAME_PASSWORD.url();
        VolleyHelper.getInstance(context).asyncTask(url,Request.Method.POST,apiIndex,jsonObject,observer);

    }

    public static void destroyAllRequests(Context context) {
        VolleyHelper.getInstance(context).destroyAllRequests();

    }


}
