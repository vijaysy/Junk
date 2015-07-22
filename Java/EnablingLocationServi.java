LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
boolean gps_enabled = false;
boolean network_enabled = false;

try {
    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
} catch(Exception ex) {}

try {
    network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
} catch(Exception ex) {}

if(!gps_enabled && !network_enabled) {
    // notify user
    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
    dialog.setMessage(context.getResources().getString(R.string.gps_network_not_enabled));
    dialog.setPositiveButton(context.getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(myIntent);
                //get gps
            }
        });
    dialog.setNegativeButton(context.getString(R.string.Cancel), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                // TODO Auto-generated method stub

            }
        });
    dialog.show();      
}