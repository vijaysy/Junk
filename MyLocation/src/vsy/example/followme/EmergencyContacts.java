package vsy.example.followme;



import java.util.ArrayList;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class EmergencyContacts extends Activity {
	
	
	
     //###########################################
		   String[] menu;
		   DrawerLayout dLayout;
		   ListView dList;
		   ArrayAdapter<String> adapter;
		   ActionBarDrawerToggle mDrawerToggle;
		   Intent it;
	//#####################################
   
   private ArrayAdapter<String> Contadapter;
   ListView lv;
   Button addC , subC;
   EditText NameTxt , NumTxt;
   MyDB db;
   ArrayList<String> contArray;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emergency_contacts);
		
		lv=(ListView)findViewById(R.id.llvv);
		Contadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1); 
	    lv.setAdapter(Contadapter);
	    
		
		addC=(Button)findViewById(R.id.addcon_EC_btn);
		subC=(Button)findViewById(R.id.submt_EC_btn);
		NameTxt=(EditText)findViewById(R.id.contactnameTBox);
		NumTxt=(EditText)findViewById(R.id.contactnumTBox);
		
		db=new MyDB(this);
		
		contArray=db.getContacts();
		Contadapter.clear();
		for(int i=0;i<contArray.size();i++){
			Contadapter.add(contArray.get(i)+"\t\t"+contArray.get(++i));
		}
		
		
		
		
		subC.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(NameTxt.getText().toString().length()==0||NumTxt.getText().toString().length()==0)
					Toast.makeText(getApplicationContext(), "Empty Conatct", Toast.LENGTH_LONG).show();
				else{
					db.insertContact(NameTxt.getText().toString(), NumTxt.getText().toString());
					Contadapter.add(NameTxt.getText().toString()+"\t\t" +NumTxt.getText().toString());
					NumTxt.setText(null);
					NameTxt.setText(null);
				}
				
			}
		});
		
		
		addC.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				 Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,Contacts.CONTENT_URI);  
			     startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);  
				
			}
		});
		
		
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                // TODO Auto-generated method stub

			      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EmergencyContacts.this);
			      final int p=pos;
			      alertDialogBuilder.setMessage("Are you sure, To delete this" );
			      
			      alertDialogBuilder.setPositiveButton("Yes", 
			      new DialogInterface.OnClickListener() {	
			         @Override
			         public void onClick(DialogInterface arg0, int arg1) {
			        	 
			           String  itemValue = (String) lv.getItemAtPosition(p);
					   deletCont(itemValue);
			           
						
			         }
			      });
			      alertDialogBuilder.setNegativeButton("No", 
			      new DialogInterface.OnClickListener() {
						
			         @Override
			         public void onClick(DialogInterface dialog, int which) {
			        	// Intent it =new Intent(getBaseContext(),Spend.class);
							//it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							//startActivity(it);
			        	 
					 }
			      });
				    
			      AlertDialog alertDialog = alertDialogBuilder.create();
			      
			      alertDialog.show();     
			    
            	
               // Log.v("long clicked","pos: " + pos);

                return true;
            }
        });
		
		
		
		
		//###############################################################
				this.setTitle("Emergency Contacts");
				
		     	menu = new String[]{"User Profile","Home","Emergency Contacts"/*,"My Balance","History","User Details","About Us","Logout"*/};
		        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		        dList = (ListView) findViewById(R.id.left_drawer);

		        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
		        
		        dList.setAdapter(adapter);
		        dList.setCacheColorHint(Color.BLACK);
				//dList.setSelector(android.R.color.holo_blue_dark);
				
				ActionBar ab=getActionBar();
				//ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));
				ab.setDisplayHomeAsUpEnabled(true);
			    ab.setHomeButtonEnabled(true);
			    
			   // ab.setIcon(R.drawable.smlic);
				ab.show();
				
				
				
				   mDrawerToggle = new ActionBarDrawerToggle(this, dLayout,
			                R.drawable.ic_drawer, //nav menu toggle icon
			                R.string.app_name, // nav drawer open - description for accessibility
			                R.string.app_name // nav drawer close - description for accessibility
			        ){
			            public void onDrawerClosed(View view) {
			                getActionBar().setTitle("Emergency Contacts");
			                // calling onPrepareOptionsMenu() to show action bar icons
			                this.setDrawerIndicatorEnabled(true);
			                invalidateOptionsMenu();
			            }
			 
			            public void onDrawerOpened(View drawerView) {
			                getActionBar().setTitle("Menu");
			                // calling onPrepareOptionsMenu() to hide action bar icons
			                invalidateOptionsMenu();
			            }
			        };	
				
				
				dLayout.setDrawerListener(mDrawerToggle);

		       dList.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
						
						dLayout.closeDrawers();		
						
						  switch (position) {
						    case 0:
						    	 it =new Intent(getBaseContext(),UserProfile.class);
								 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								 startActivity(it);
									finish();
						         break;
						      
						    case 1:
						    	 it =new Intent(getBaseContext(),FirstActivity.class);
								 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								 startActivity(it);
									finish();
								 break;
								 
						    case 2:
//						    	it =new Intent(getBaseContext(),EmergencyContacts.class);
//								it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//								startActivity(it);
//								finish();
								break;
								
						    case 3:
						    	it =new Intent(getBaseContext(),FirstActivity.class);
								it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								startActivity(it);
								finish();
								break;	
								
						    case 4:
						    	it =new Intent(getBaseContext(),FirstActivity.class);
								it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								startActivity(it);
								finish();
								break;	
								
						    case 5:
						    	it =new Intent(getBaseContext(),FirstActivity.class);
								it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								startActivity(it);
								finish();
								break;	
								
						    case 6:
						    	it =new Intent(getBaseContext(),FirstActivity.class);
								it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								startActivity(it);
								finish();
								break;	
								
								
						
						    default:
						      break;
						    }
						
						
						
					}
		       	
		       });
				
				//######################################################

		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emergency_contacts, menu);
		return true;
	}
	
	
	//###################################################################
		 @Override
		    public boolean onOptionsItemSelected(MenuItem item) {
		        // toggle nav drawer on selecting action bar app icon/title
		        if (mDrawerToggle.onOptionsItemSelected(item)) {
		            return true;
		        }
		        // Handle action bar actions click
		        switch (item.getItemId()) {
		        case R.id.action_settings:
		            return true;
		            
		        //case R.id.action_refresh:
		        	
		        default:
		            return super.onOptionsItemSelected(item);
		        }
		    }
		 
		 
			@Override
			protected void onPostCreate(Bundle savedInstanceState) {
			    super.onPostCreate(savedInstanceState);
			    mDrawerToggle.syncState();
			}
   //######################################################################
		 
		 
		//######################################################################
		 private static final int CONTACT_PICKER_RESULT = 1001;
			
			

		    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		         String phone="";
		         Cursor contacts=null;
		         try{
		            if (resultCode == RESULT_OK) {  
		                switch (requestCode) {  
		                case CONTACT_PICKER_RESULT:  
		                    // gets the uri of selected contact
		                 Uri result = data.getData();
		                 // get the contact id from the Uri (last part is contact id) 
		                 String id = result.getLastPathSegment(); 
		                     //queries the contacts DB for phone no
		                 contacts=getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?", new String[] { id },  null);
		                    //gets index of phone no
		                 int phoneIdx = contacts.getColumnIndex(Phone.DATA); 
		                 int nameId=contacts.getColumnIndex(Phone.DISPLAY_NAME);
		                 if (contacts.moveToFirst()) {
		                       //gets the phone no  
		                	 	String p2=contacts.getString(phoneIdx); 
		                       phone = p2.replace(" ","");
		                       if(phone.length()>10)	                    	   
		                    	   NumTxt.setText(phone.substring(3, phone.length())); 
		                       else
		                    	   NumTxt.setText(phone);
		                      // f=false;
		                     // String name=new String(contacts.getString(nameId));
		                      NameTxt.setText(contacts.getString(nameId));
		                 } 
		                 else {  
		                  Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
		                 }  
		                 break;  
		                }  
		          
		            } 
		            else {  
		                // gracefully handle failure  
		              //  Toast.makeText(this, "Warning: activity result not ok",Toast.LENGTH_SHORT).show();  
		            }  
		        }
		         catch (Exception e) {  
		            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();  
		         } 
		         finally{  
		            if (contacts != null) {  
		                contacts.close();  
		            }
		         }
		        }
	//#######################################################################	

   void deletCont(String itemValue){
	   
	   db.deleteContact(itemValue.substring(itemValue.length() - 10));
	   
	    contArray=db.getContacts();
		Contadapter.clear();
		for(int i=0;i<contArray.size();i++){
			Contadapter.add(contArray.get(i)+"\t\t"+contArray.get(++i));
		}
	   
   }

}
