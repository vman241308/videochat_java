package THEFinalProject;

import java.net.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;

import java.io.*;

public class VideoServer // extends JFrame
{

	ServerSocket s;
	Socket audioClient;
	Socket videoClient;
	InputStream audioStream;
	InputStream videoStream;
	BufferedInputStream bufVideoStream;
	// JLabel screen;

	VideoServer(int Port) {

		try {
			s = new ServerSocket(Port);
			videoClient = s.accept();
			videoStream = videoClient.getInputStream();
			bufVideoStream = new BufferedInputStream(videoStream);
			ServerGUI g = new ServerGUI();
			// setLayout(null);
			// setSize(new Dimension(640, 560));
			// setLocationRelativeTo(null);
			// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// setVisible(true);
			// screen = new JLabel();
			// screen.setBounds(0, 0, resX, resY);
			// add(screen);
			BufferedInputStream bin = new BufferedInputStream(videoStream);
			while (true) {
				BufferedImage b = ImageIO.read(bin);
				// if(b!=null);
				// screen.setIcon(new ImageIcon(b));
				g.setScreenView(g.screen, b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")

	public static void main(String[] args) {
		try {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			VideoServer ms = new VideoServer(65535);
//			VideoServer s=new VideoServer(Integer.parseInt(args[0]));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
