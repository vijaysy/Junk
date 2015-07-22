package com.example.networkdemo;

import com.example.networkdemo.network.APIObserver;
import com.example.networkdemo.network.NetworkConstants;
import com.example.networkdemo.network.NetworkHelper;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity implements APIObserver {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		NetworkHelper.getAnd(this, this);
		//NetworkHelper.getAnd2(this, this);

	}

	@Override
	public void onAPIResponse(boolean success, String response,
			int responseCode, int apiIndex) {

		
		switch (NetworkConstants.Communicate.getFromApiIndex(apiIndex)) {
		case GET_DEMO:
			Log.e("DEMO","The Response is " +response );
			break;
		case GET_DEMO2:
			Log.e("DEMO2","The Response is " +response );
			break;
		
		
		}
		
	}


}
