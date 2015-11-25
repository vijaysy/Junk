package vsy.example.followme;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class FirstActivity extends Activity implements LocationListener{
	
	
	//###########################################
		 String[] menu;
	    DrawerLayout dLayout;
	    ListView dList;
	    ArrayAdapter<String> adapter;
	    ActionBarDrawerToggle mDrawerToggle;
	    Intent it;
	//#####################################
    
    
    TextView lat,lon,spd,addr;
    Button meetBtn,emrgBtn,followBtn;
    String provider;
    LocationManager lmanager;
    float speed;
    MyDB db;
    String addressText;
    Double Glat,Glon;
    ArrayList<String> numsArray;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_activity);
		
		
		
		lat=(TextView)findViewById(R.id.lattextView);
		lon=(TextView)findViewById(R.id.lontextView);
		spd=(TextView)findViewById(R.id.spdtextView);
		addr=(TextView)findViewById(R.id.addrtextView);
		
		db=new MyDB(this);
		
	//################################ 3 Buttons ############################################################	
		meetBtn=(Button)findViewById(R.id.meet_fa_btn);
		emrgBtn=(Button)findViewById(R.id.emrg_fa_btn);
		followBtn=(Button)findViewById(R.id.follow_fa_btn);
		
		
		
		meetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SharedPreferences prefs = getSharedPreferences("MyLocationPref", MODE_PRIVATE);
			    // name.setText( prefs.getString("Name", "112"));
				
				it=new Intent();
				it.setAction(Intent.ACTION_SEND);
				it.putExtra(Intent.EXTRA_TEXT,"Hi this is "+prefs.getString("Name", "User")+",\nMeet me at: "+addressText+"\nLat:"+Glat+"\nLon:"+Glon);
				it.setType("text/plain");
				startActivity(it);
				
				
			}
		});
		
		emrgBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SharedPreferences prefs = getSharedPreferences("MyLocationPref", MODE_PRIVATE);
				numsArray=db.getNums();
				
				for(int i=0;i<numsArray.size();i++){
				 try {
				     SmsManager smsManager = SmsManager.getDefault();
				     smsManager.sendTextMessage(numsArray.get(i), null, "Hi this is "+prefs.getString("Name", "User")+",\nI am in danger at: "+addressText+"\nLat:"+Glat+"\nLon:"+Glon, null, null);
				     Toast.makeText(getApplicationContext(), "SMS sent.",Toast.LENGTH_LONG).show();

				      } catch (Exception e) {
				         Toast.makeText(getApplicationContext(),
				         "SMS faild, please try again.",
				         Toast.LENGTH_LONG).show();
				         e.printStackTrace();
				      }
				}
				
			}
		});
		
		
		followBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				startService(new Intent(getBaseContext(), FollowMeMsgs.class));
				
			}
		});
		
		
		
		followBtn.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FirstActivity.this);
			      alertDialogBuilder.setMessage("Are you sure, To stop sending messages" );
			      
			      alertDialogBuilder.setPositiveButton("Yes", 
			      new DialogInterface.OnClickListener() {	
			         @Override
			         public void onClick(DialogInterface arg0, int arg1) {

			             
			        	stopService(new Intent(getBaseContext(), FollowMeMsgs.class));
			        	 
						
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
			    

				
				return false;
			}
		});
		
		
	//################################ 3 Buttons ###########################################################	
		
		
		
		lmanager=(LocationManager)getSystemService(LOCATION_SERVICE);
		
		lmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);


		
		
		
		//###############################################################
		this.setTitle("Alert");
		
	
		
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
		ab.show();
		
		
		
		
		   mDrawerToggle = new ActionBarDrawerToggle(this, dLayout,
	                R.drawable.ic_drawer, //nav menu toggle icon
	                R.string.app_name, // nav drawer open - description for accessibility
	                R.string.app_name // nav drawer close - description for accessibility
	        ){
	            public void onDrawerClosed(View view) {
	                getActionBar().setTitle("Alert");
	                // calling onPrepareOptionsMenu() to show action bar icons
	              
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
//				    	 it =new Intent(getBaseContext(),EmergencyContacts.class);
//						 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//						 startActivity(it);
//							finish();
						 break;
						 
				    case 2:
				    	it =new Intent(getBaseContext(),EmergencyContacts.class);
						it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(it);
						finish();
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
		getMenuInflater().inflate(R.menu.first, menu);
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

	@Override
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		
		Glon=loc.getLongitude();
		Glat=loc.getLatitude();
		
		StaticClass.setSlat(Glat);
		StaticClass.setSlan(Glon);

		 speed=loc.getSpeed();
		 speed=(float) (speed*3.6);

	    lat.setText("Latitude    : "+loc.getLongitude());
		lon.setText("Longitude : "+loc.getLatitude());
		spd.setText("Speed     : "+speed);
		
		
		
		
		
		
		Geocoder geo=new Geocoder(getApplicationContext(),Locale.ENGLISH);
		if(Geocoder.isPresent())
		{
				 try {
					  List<Address> addresses = geo.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
					  if (addresses != null && addresses.size() > 0) 
					  {
						   Address address = addresses.get(0);
						    addressText = String.format("%s, %s, %s",
						      // If there's a street address, add it
						      address.getMaxAddressLineIndex() > 0 ?address.getAddressLine(0) : "",
						      // Locality is usually a city
						      address.getLocality(),
						      // The country of the address
						      address.getCountryName());
						   addr.setText("My Location : "+addressText);
						   StaticClass.setAddr(addressText);
					   
					   }
				  } catch (IOException e) {
				    e.printStackTrace();
				 }
		} else addr.setText("My Location : ");

		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	

	
	
	

}
