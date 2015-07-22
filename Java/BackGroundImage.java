//Class................................................................
class backImage extends JComponent {
	private static final long serialVersionUID = 1L;
  Image i;

public backImage(Image i) {
this.i = i;
}
@Override
public void paintComponent(Graphics g) {
g.drawImage(i, 0, 0, null);  // Drawing image using drawImage method
}
}
//...........................................................................


// Inside Constructor ..............................................................
BufferedImage bf = ImageIO.read(new File("Path of background image"));
backImage bf2=new backImage(bf);
this.setContentPane(bf2);
//.............................................................................