package vsy.example.followme;



import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;;

public class UserProfile extends Activity {
	
	

	//###########################################
	 String[] menu;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> adapter;
    ActionBarDrawerToggle mDrawerToggle;
    Intent it;
	//#####################################
    
    EditText name,num;
    Button up;
    SharedPreferences shf;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile);
		
		
		name=(EditText)findViewById(R.id.NameeditText1);
		num=(EditText)findViewById(R.id.NumeditText2);
		up=(Button)findViewById(R.id.up_UP_btn);
		
		shf = getSharedPreferences("MyLocationPref", Context.MODE_PRIVATE);
		if(!shf.contains("Name")){
			name.setHint("User Name");
			num.setHint("User Number");			
		}else{
			
			SharedPreferences prefs = getSharedPreferences("MyLocationPref", MODE_PRIVATE);
		      name.setText( prefs.getString("Name", "112"));
		      num.setText(prefs.getString("Num", "123")); 
		         
			
		}
		
		up.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
		SharedPreferences.Editor editor = getSharedPreferences("MyLocationPref", MODE_PRIVATE).edit();
   			 editor.putString("Name", name.getText().toString());
   			 editor.putString("Num", num.getText().toString());
   			 editor.commit();
   			 Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
				
			}
		});
		
		
		
		//###############################################################
		this.setTitle("User Profile");
		
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
	                getActionBar().setTitle("User Profile");
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
//				    	 it =new Intent(getBaseContext(),UserProfile.class);
//						 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//						 startActivity(it);
//							finish();
				         break;
				      
				    case 1:
				    	 it =new Intent(getBaseContext(),FirstActivity.class);
						 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						 startActivity(it);
							finish();
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}
	
	

}
