	package ahmed;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GameFrame {

	JFrame frame;
	
	public GameFrame(Launcher launcher, Dimension size) {
		frame = new JFrame("Jam With Band");
	
	
		frame.setPreferredSize(size);
		frame.setMinimumSize(size);
		frame.setMaximumSize(size);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		frame.add(launcher, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
	}
	
}
