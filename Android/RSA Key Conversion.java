	while((line=inBuff.readLine())!=null)
	           tmp+=line;
	    	
	    	//tv.append("\n"+tmp+1);
	    	
	    	Log.i("Raw Key",tmp);
	    	
	    	String s1=tmp.replace("null-----BEGIN PUBLIC KEY-----", "");
	    	
	    	Log.i("1st Replace", s1);
	    	
	    	String s2=s1.replace( "-----END PUBLIC KEY-----", "");

	    	Log.i("2st Replace", s2);
	    	
	    	byte [] encode = Base64.decode(s2.getBytes("utf-8"),Base64.DEFAULT);
	    	X509EncodedKeySpec keySpec= new X509EncodedKeySpec(encode);
	    	KeyFactory kf=KeyFactory.getInstance("RSA");
	    	publicKey=kf.generatePublic(keySpec); 
	    	
	    	Log.i("Android Key",publicKey.toString());
	    	
	    	tv.append("\n"+publicKey.toString());
	    	
	    	Toast.makeText(getApplicationContext(), "Key : "+publicKey.toString(), Toast.LENGTH_LONG).show();    	
	    	