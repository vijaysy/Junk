package vsy.example.followme;


import java.util.Calendar;

import org.json.JSONObject;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class FollowMeMsgs extends Service {
	
	Calendar c;
	Thread t2;
	boolean f=true;

	public FollowMeMsgs() {
		// TODO Auto-generated constructor stub
		
		c = Calendar.getInstance(); 
		new SendingSMS().execute("");
	
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	   public void onDestroy() {
		
		 f=false;
		  
	   }
	
	protected void sendSMSMessage() {
		
		String TmpNums[]=StaticClass.getNums();
		for(int i=0;i<TmpNums.length;i++){
		
	     String  message="My current location is\nLatitude:"+StaticClass.getSlat() +"\nLongitude:" + StaticClass.getSlan()+"\nAddress"+StaticClass.getAddr();
	      try {
	    	  SmsManager smsManager = SmsManager.getDefault();
	          smsManager.sendTextMessage(TmpNums[i], null, message, null, null);
	         Toast.makeText(getBaseContext(), "Message is sent",Toast.LENGTH_LONG).show();
	      } catch (Exception e) {
	         Toast.makeText(getApplicationContext(),"SMS faild, please try again.",Toast.LENGTH_LONG).show();
	         e.printStackTrace();
	      }
	   }
	
	}
	
	
	private class SendingSMS extends AsyncTask<String, Void, JSONObject>{
	
		

	@Override
	protected JSONObject doInBackground(String... params) {
	    Log.i("thread", "Doing Something...");
	    
	    while (f){
	    	
	    	if(c.get(Calendar.MINUTE)%3==0)
	    		sendSMSMessage();
	    	
	    }
	    
	    Intent it=new Intent(getBaseContext(),FirstActivity.class);		
		 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 startActivity(it);
		 Toast.makeText(getBaseContext(), "On Destroy",Toast.LENGTH_LONG).show();	

	


	    return null;
	}




	protected void onPreExecute(){
	    //dialog.dismiss();
	    Log.i("thread", "Started...");
	   
	}



	protected void onPostExecute(JSONObject result){
	    Log.i("thread", "Done...");
	  
	    
	
	    }
	   

	  }
	

	
}
