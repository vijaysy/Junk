package com.codecraft.mcs.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codecraft.mcs.util.CCLog;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Map;

public class VolleyHelper {
    private static final int DEFAULT_TIMEOUT_MS = 60000;
    private static VolleyHelper singleInstance = new VolleyHelper();
    private RequestQueue requestQueue = null;
    private ImageLoader imageLoader = null;
    private ImageLoader.ImageCache imageCache = null;
    // private APIObserver observer = null;

    private Request<String> stringRequest = null;

    private VolleyHelper() {
    }

    public static synchronized VolleyHelper getInstance(Context context) {
        if (singleInstance == null)
            singleInstance = new VolleyHelper();
        singleInstance.setVolleyRequestQueue(context.getApplicationContext());
        singleInstance.initImageLoader();
        return singleInstance;
    }

    private void setVolleyRequestQueue(Context context) {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);
    }

    public RequestQueue getVollyRequestQueue() {
        return requestQueue;
    }

	/*
     * private void initImageLoader() { if (imageLoader == null) { imageLoader =
	 * new ImageLoader(this.requestQueue, new ImageLoader.ImageCache() { private
	 * final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(
	 * 100);
	 * 
	 * @Override public Bitmap getBitmap(String url) { return cache.get(url); }
	 * 
	 * @Override public void putBitmap(String url, Bitmap bitmap) {
	 * cache.put(url, bitmap); } }); } }
	 */

    private void initImageLoader() {
        if (imageLoader == null) {
            setImageCache(new BitmapLruCache());
            imageLoader = new ImageLoader(this.requestQueue, getImageCache());
        }
    }


    public void asyncTask(String apiUrl, int requestMethod, int apiIndex,
                          APIObserver observer) {
        CCLog.d(" API INDEX " + apiIndex + "\nRequest URL: " + apiUrl);

        stringRequest = new StringRequest(requestMethod, apiUrl,
                new VolleyStringResponseListener(observer, apiIndex),
                new VolleyErrorListener(observer, apiIndex));
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setTag(String.valueOf(apiIndex));
        requestQueue.add(stringRequest);
    }

    public void asyncTask(String apiUrl, int requestMethod, int apiIndex,
                          final Map<String, String> header, APIObserver observer) {
        // CCUtility.CCDisplayLog("Request URL: " + apiUrl);
        stringRequest = new StringRequest(requestMethod, apiUrl,
                new VolleyStringResponseListener(observer, apiIndex),
                new VolleyErrorListener(observer, apiIndex)) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setTag(String.valueOf(apiIndex));
        requestQueue.add(stringRequest);
    }

    public void asyncTask(String apiUrl, int requestMethod, int apiIndex,
                          String cancelTag, APIObserver observer) {
        // CCUtility.CCDisplayLog("Request URL: " + apiUrl);
        stringRequest = new StringRequest(requestMethod, apiUrl,
                new VolleyStringResponseListener(observer, apiIndex),
                new VolleyErrorListener(observer, apiIndex));
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setTag(cancelTag);
        requestQueue.add(stringRequest);
    }

    public void asyncTask(String url, int requestMethod, int apiIndex,
                          JSONObject jsonObject, APIObserver observer) {
      //  String url = NetworkConstants.BASE_URL + "/" + apiUrl;
        // CCUtility.CCDisplayLog("Request URL: " + url);
        //CCUtility.CCDisplayLog("Request URL: " + jsonObject.toString());

        VolleyStringRequest volleyStringRequest = new VolleyStringRequest(requestMethod, url,
                jsonObject, new VolleyNetworkResponseListener(observer,
                apiIndex), new VolleyErrorListener(observer, apiIndex)
        );
        volleyStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        volleyStringRequest.setTag(String.valueOf(apiIndex));
        requestQueue.add(volleyStringRequest);
    }


    public void fileUpload(String apiUrl, int apiIndex,
                           APIObserver observer, File file, String fileName) {
        CCLog.d(" API INDEX " + apiIndex + "\nRequest URL: " + apiUrl);

        MultipartRequest mpr = new MultipartRequest(apiUrl, file, "fileToUpload", new VolleyNetworkResponseListener(observer, apiIndex),
                new VolleyErrorListener(observer, apiIndex));

        mpr.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mpr.setTag(String.valueOf(apiIndex));
        requestQueue.add(mpr);
    }

	/*
     * private String validateNameValuePair(List<NameValuePair> valuePairs) {
	 * for (NameValuePair valuePair : valuePairs) { if (valuePair != null)
	 * valuePair.getValue().replace(" ", "%20"); }
	 * 
	 * StringBuilder parameters = new StringBuilder(); for (NameValuePair
	 * valuePair : valuePairs) { parameters.append(valuePair.getId() + "=" +
	 * valuePair.getValue() + "&"); } return parameters.toString(); }
	 */

    private String validateURL(String apiName, String params) {
        return apiName + "?" + params;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void cancelVolleyTask(String tag) {
        requestQueue.cancelAll(tag);
    }

    public ImageLoader.ImageCache getImageCache() {
        return imageCache;
    }

    public void setImageCache(ImageLoader.ImageCache imageCache) {
        this.imageCache = imageCache;
    }


    private static class VolleyStringResponseListener implements
            Response.Listener<String> {

        private int apiIndex = -1;
        private WeakReference<APIObserver> weakReference = null;

        public VolleyStringResponseListener(APIObserver observer, int apiIndex) {
            weakReference = new WeakReference<APIObserver>(observer);
            this.apiIndex = apiIndex;
        }

        @Override
        public void onResponse(String s) {
            CCLog.i("API INDEX " + apiIndex + " TRUNCATED RESPONSE\n" + ((s == null) ? null : s.substring(0, 20)));
            if (weakReference != null && weakReference.get() != null) {
                if (s != null)
                    weakReference.get().onAPIResponse(true, s, HttpStatus.SC_OK, apiIndex);
                else
                    weakReference.get().onAPIResponse(false, s, 0, apiIndex);
                weakReference = null;
            }
        }


    }

    private static class VolleyErrorListener implements Response.ErrorListener {

        private int apiIndex = -1;
        private WeakReference<APIObserver> weakReference = null;

        public VolleyErrorListener(APIObserver observer, int apiIndex) {
            weakReference = new WeakReference<APIObserver>(observer);
            this.apiIndex = apiIndex;
        }

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            CCLog.e("API INDEX " + apiIndex + " ERROR\n" + volleyError.getMessage());
            if (weakReference != null && weakReference.get() != null) {
                weakReference.get().onAPIResponse(false,
                        volleyError.getMessage(), 0, apiIndex);
                weakReference = null;
            }
        }
    }

    private static class VolleyNetworkResponseListener implements
            Response.Listener<NetworkResponse> {

        private int apiIndex = -1;
        private WeakReference<APIObserver> weakReference = null;

        public VolleyNetworkResponseListener(APIObserver observer, int apiIndex) {
            weakReference = new WeakReference<APIObserver>(observer);
            this.apiIndex = apiIndex;
        }

        @Override
        public void onResponse(NetworkResponse response) {
            CCLog.i("API INDEX " + apiIndex + "  RESPONSE\n");
            if (weakReference != null && weakReference.get() != null) {

                String parsed;
                int responseCode = 0;
                try {
                    parsed = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    responseCode = response.statusCode;
                } catch (UnsupportedEncodingException e) {
                    parsed = new String(response.data);
                }
                if (weakReference != null && weakReference.get() != null) {
                    weakReference.get().onAPIResponse(true, parsed, responseCode,
                            apiIndex);
                    CCLog.i("response code:" + responseCode + " response:" + parsed);
                }
                weakReference = null;
            }
        }
    }

    public void destroyAllRequests() {
        // CCUtility.CCDisplayLogError("Cancelling all requests");
        if (requestQueue == null) {
            return;
        }
        try {
            requestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    //CCUtility.CCDisplayLog("cancelling " + request.toString());
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //CCUtility.CCDisplayLogError("Error Cancelling all requests");
        }
    }


}
