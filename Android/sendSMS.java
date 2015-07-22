 SmsManager smsManager = SmsManager.getDefault();
					         smsManager.sendTextMessage(phoneNo, null, message, null, null);
					         db1.deleteMoney(amnt.getText().toString());
					         Toast.makeText(getApplicationContext(), "SMS sent.",
					         Toast.LENGTH_LONG).show();