//package reposetryV1;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


import javax.swing.JFrame;

public class MyFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	int w_,h_;
	
	public MyFrame() {
		w_=(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		h_=(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		
		this.setSize(w_,h_);
		this.setTitle("MY FRAME REPOSETRY");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	
	
	
	
	}
	
	public static void main(String[] args) throws Exception {

		MyFrame f = new MyFrame();
		f.setVisible(true);
		//f.StrtQuiz();
		}
	
}
