//Writting 
SharedPreferences sharedPref = TestData.this.getPreferences(Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putInt("D", Integer.parseInt(td.getText().toString()));
				editor.putInt("N", Integer.parseInt(tn.getText().toString()));
				editor.commit();
				
				
				
				
// Reading				
SharedPreferences sharedPref = TestData.this.getPreferences(Context.MODE_PRIVATE);
				Integer D = sharedPref.getInt("D", 3);
				Integer N = sharedPref.getInt("N", 3);
				dv.append(D.toString());
				nv.append(N.toString());				