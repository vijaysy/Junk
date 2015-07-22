
import android.app.AlertDialog;
import android.content.DialogInterface;

 //################################################################
			     // http://stackoverflow.com/questions/5796611/dialog-throwing-unable-to-add-window-token-null-is-not-for-an-application-wi
			      //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
				   AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyActivity.this);
			      alertDialogBuilder.setMessage(R.string.spndAlrt);
			      alertDialogBuilder.setPositiveButton(R.string.yes_button, 
			      new DialogInterface.OnClickListener() {
					
			         @Override
			         public void onClick(DialogInterface arg0, int arg1) {
			           
						
			         }
			      });
			      alertDialogBuilder.setNegativeButton(R.string.no_button, 
			      new DialogInterface.OnClickListener() {
						
			         @Override
			         public void onClick(DialogInterface dialog, int which) {
			           
			        	 
					 }
			      });
				    
			      AlertDialog alertDialog = alertDialogBuilder.create();
			      alertDialog.show();     
//#################################################################

new AlertDialog.Builder(this)
    .setTitle("Delete entry")
    .setMessage("Are you sure you want to delete this entry?")
    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) { 
            // continue with delete
        }
     })
    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) { 
            // do nothing
        }
     })
    .setIcon(android.R.drawable.ic_dialog_alert)
     .show();
//#########################################################################