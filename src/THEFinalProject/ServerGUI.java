package THEFinalProject;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ServerGUI extends JFrame {
	JLabel screen;
	JLabel videoReceived;

	ServerGUI() {
		setLayout(null);
		setSize(new Dimension(640, 560));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		screen = new JLabel();
		screen.setBounds(0, 0, 640, 480);
		add(screen);
		videoReceived = new JLabel("Client Not Started");
		videoReceived.setBounds(440, 450, 100, 50);
		add(videoReceived);
	}

	public void setScreenView(JLabel j, BufferedImage b) {
		j.setIcon(new ImageIcon(b));
//		BufferedImage bi = new BufferedImage(80, 40, ColorSpace.TYPE_RGB);
//		j.setIcon((b != null) ? new ImageIcon(b) : new ImageIcon());
//		videoReceived.setText((b != null) ? "Video Receiving" : "Video Turned Off");
		/*
		 * try this also: if(b==null) { j.setIcon(newImageIcon()); } else {
		 * j.setIcon(new ImageIcon(b)); }
		 */

	}

}
