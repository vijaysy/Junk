login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 new login().execute("");
				
			}
		});


//#####################################################################




private class login extends AsyncTask<String, Void, JSONObject>{
		HttpResponse respons;

	ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "", "Authenticating, Please wait...");

	@Override
	protected JSONObject doInBackground(String... params) {
	    Log.i("thread", "Doing Something...");
	   //authentication operation
	try{

	   
		HttpClient client=new DefaultHttpClient();
		HttpGet request = new HttpGet();
		request.setURI(new URI("http://192.168.224.173/and.php?cnum="
		+username.getText().toString()+"&password="+password.getText().toString()));
		respons=client.execute(request);
		
		
	}

	  catch(Exception e){
	    e.printStackTrace();   

	}

	    return null;
	}




	protected void onPreExecute(){
	    //dialog.dismiss();
	    Log.i("thread", "Started...");
	    dialog.show();
	}



	protected void onPostExecute(JSONObject result){
	    Log.i("thread", "Done...");
	    dialog.dismiss();
	    
	    try {
	    	BufferedReader inBuff=new BufferedReader(new InputStreamReader(respons.getEntity().getContent()));
	    	String line=null;
	    	while((line=inBuff.readLine())!=null){
	            Toast.makeText(getApplicationContext(), "Authenticated.."+line, Toast.LENGTH_LONG).show();
	    		if(line.equals("true")){
	    		Toast.makeText(getApplicationContext(), "Authenticated"+line, Toast.LENGTH_LONG).show();
				Intent it =new Intent(getBaseContext(),AfterLogin.class);
				it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(it);
				f=true;
					
	    	}
	    	
	    	}
	    	
	    	if(!f)
	    	Toast.makeText(getApplicationContext(), "Not Authenticated try again", Toast.LENGTH_LONG).show();
	    	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	   }

	  }
