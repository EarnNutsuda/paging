import java.awt.Color;

import javax.swing.JFrame;
public class PagingMain {
	
	public static void main(String [] args) {		
		JFrame frame = new JFrame("Paging Stimulation");
		frame.add(new PagingUI());
		frame.setSize(1200, 800); 
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
}

}
