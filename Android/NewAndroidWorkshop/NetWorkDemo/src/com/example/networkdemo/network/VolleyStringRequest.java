package com.example.networkdemo.network;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class VolleyStringRequest extends Request<NetworkResponse> {

	private static final String PROTOCOL_CHARSET = "utf-8";
	private final Response.Listener<NetworkResponse> listener;
	private final String mRequestBody;


	public VolleyStringRequest(int method, String url, JSONObject jsonRequest, Response.Listener<NetworkResponse> listener,
			Response.ErrorListener errorListener) {
		super(method, url, errorListener);
		this.listener = listener;
		mRequestBody = (jsonRequest == null) ? null : jsonRequest.toString();
	}

	public VolleyStringRequest(String url, JSONObject jsonRequest, Response.Listener<NetworkResponse> listener, Response.ErrorListener errorListener) {
		this(Method.GET, url, jsonRequest, listener, errorListener);
	}

	@Override
	protected void deliverResponse(NetworkResponse response) {
		this.listener.onResponse(response);
	}

	@Override
	protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
		return Response.success(response, HttpHeaderParser.parseCacheHeaders(response));
	}

	@Override
	public byte[] getBody() {
		try {
			return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
		} catch (UnsupportedEncodingException uee) {
			VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
					mRequestBody, PROTOCOL_CHARSET);
			return null;
		}
	}
}
