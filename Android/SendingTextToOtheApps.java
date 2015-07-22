it=new Intent();
	        	it.setAction(Intent.ACTION_SEND);
	        	try {
					//Encryption tmp=new Encryption();
					it.putExtra(Intent.EXTRA_TEXT,Encryption.cipher("Ya",FRtnStr.toString()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	it.setType("text/plain");
	        	//startActivityForResult(it, OTHER_SEND_RESULT);
				only startactivity