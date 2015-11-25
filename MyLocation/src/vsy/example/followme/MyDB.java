package vsy.example.followme;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDB extends SQLiteOpenHelper {
	
	Context cnt;

	public MyDB(Context context){
	      super(context, "MyLocationContacts" , null, 1);
	      cnt=context;
	      
	   }

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(
			      "create table contacts " +
			      "(Num text primary key , Name text  )"
			      );		  

	}
	
	


	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		 db.execSQL("DROP TABLE IF EXISTS contacts");
	      onCreate(db);

	}
	
	
	//****************************Inserting Contact ******************************************
	 public boolean insertContact  (String Name, String Num ){
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();

	      contentValues.put("Name", Name);
	      contentValues.put("Num", Num);   
	      db.insert("contacts", null, contentValues);
	      
	      Toast.makeText(cnt, "Inserted", Toast.LENGTH_SHORT).show();
	      return true;
	   }
	 

	 
//*************************** Count of ECash**********************************************
//	 
//	 public int getCoinCount(String Amt) {
//		    String countQuery = "SELECT  * FROM " + "money"+Amt;
//		    SQLiteDatabase db = this.getReadableDatabase();
//		    Cursor cursor = db.rawQuery(countQuery, null);
//		    int cnt = cursor.getCount();
//		    cursor.close();
//		    return cnt;
//		}
	 
//******************************************************************************************	 
	 
	 
	 
	 public ArrayList<String> getContacts(){
	  try{
		 ArrayList<String> array_list = new ArrayList<String>();
	     SQLiteDatabase db = this.getReadableDatabase();
	     Cursor res =  db.rawQuery( "select  * from  contacts",null);
	      res.moveToFirst();
	    
	      while(res.isAfterLast() == false){
		      array_list.add(res.getString(res.getColumnIndex("Name")));      
		      array_list.add(res.getString(res.getColumnIndex("Num")));
		      res.moveToNext();
	      }
	      return array_list;
	      
	      }catch (Exception e){
	    	  e.printStackTrace();
	      }
		  
		  return new ArrayList<String>();
	   
	   }
	 
	 public ArrayList<String> getNums(){
		  try{
			 ArrayList<String> array_list = new ArrayList<String>();
		     SQLiteDatabase db = this.getReadableDatabase();
		     Cursor res =  db.rawQuery( "select  * from  contacts",null);
		      res.moveToFirst();
		    
		      while(res.isAfterLast() == false){      
			      array_list.add(res.getString(res.getColumnIndex("Num")));
			      res.moveToNext();
		      }
		      return array_list;
		      
		      }catch (Exception e){
		    	  e.printStackTrace();
		      }
			  
			  return new ArrayList<String>();
		   
		   }
	 
	 
	//********************************Deleting the Contacts*********************************************
	 public void deleteContact (String id){	 
		 //ArrayList<String> array_list = new ArrayList<String>();
	     SQLiteDatabase db = this.getReadableDatabase();
	     
//	      Cursor res =  db.rawQuery( "select * from contacts where Num="+id, null );
//	      res.moveToFirst();
//	      
//	      while(res.isAfterLast() == false){  
//	      array_list.add(res.getString(res.getColumnIndex("Name")));  
//	      array_list.add(res.getString(res.getColumnIndex("Num")));
//	      res.moveToNext();
//	      }
	       db = this.getWritableDatabase();
	       db.delete("contacts", 
	      "Num = ? ", 
	      new String[] { id });
	   }
	 
	
	 
	//*************************************************************************************** 

}
