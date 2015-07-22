
JLabel tim;
  int timeRemaining = 60*20;
  public static final DateFormat df = new SimpleDateFormat("hh:mm:ss");
  Timer countdownTimer;


countdownTimer = new Timer(1000, new ActionListener(){
public void actionPerformed(ActionEvent e) {
     if (--timeRemaining > 0) {
  	   String frmt=new String();
  	   
  	   frmt=String.valueOf(timeRemaining/60)+":"+String.valueOf(timeRemaining%60);
  	   tim.setText("Time Remaining: "+frmt);
      } else { 
      	
         tim.setText("Time's up!");
         
     	try {
			FileWriter fw = new FileWriter(CodeFile.fname);
			BufferedWriter bw=new BufferedWriter(fw);
			
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    	bw.write( sdf.format(cal.getTime()) );
	    	bw.newLine();
			
      	bw.write("Player 1:  "+CodeFile.pl1);
      	bw.newLine();
      	bw.write("Player 2:  "+CodeFile.pl2);
      	bw.newLine();
      	bw.write("Mobile No:  "+CodeFile.pn);
      	bw.newLine();
      	bw.write("Team Name:  "+CodeFile.tn);
      	bw.newLine();
      	bw.write("Clg:  "+CodeFile.cl);
      	bw.newLine();
      	bw.write("Score: "+CodeFile.scoure);
      	bw.newLine();
      	bw.close();
      	tqFrame();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
      countdownTimer.stop();
      }
     
  }

}); countdownTimer.start();