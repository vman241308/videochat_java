package THEFinalProject;

import java.awt.color.ColorSpace;
//import java.awt.Frame;
//import javax.media.* ;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

public class Client {

	Socket audioClient;
	Socket videoClient;
	OutputStream videoStream;
	BufferedOutputStream bufVideoStream;
	OutputStream audioStream;
	TargetDataLine mic;
	VideoWriter videoWriter;

	Client(final String ip, final int audioPort, final int videoPort) {
		ClientGUI g = new ClientGUI();
		int i = 0;
		for (i = 0; i < 2; i++) { 

			if (i == 1) {
				Thread t1 = new Thread() {
					public void run() {
						try {
							audioClient = new Socket(ip, audioPort);
							AudioFormat af = new AudioFormat(10000, 16, 2, true, true); // Speaker's access
							DataLine.Info micInfo = new DataLine.Info(TargetDataLine.class, af); // Targetdataline means
							// mike
							mic = (TargetDataLine) AudioSystem.getLine(micInfo);
							audioStream = audioClient.getOutputStream();
							mic.open();
							mic.start();

							ByteArrayOutputStream bout;
							byte[] b;
							while (true) {
								b = new byte[32];
								bout = new ByteArrayOutputStream(32);
								// mic.
								// System.out.println(g.AudioToggled+" "+mic.isRunning());
								// if(g.AudioToggled){
								// if(mic.isRunning())
								mic.read(b, 0, 32);
								// else
								// mic.start();
								// }
								// else
								// mic.stop();
								bout.write(b);
								if (g.AudioToggled)
									bout.writeTo(audioStream);
							}

						} catch (Exception e) {
							// System.out.println("Audio Off");
							e.printStackTrace();
						}
					}
				};
				t1.start();

			} else if (i == 0) {

				Thread t2 = new Thread() {
					public void run() {
						try {
							// LoadLibrary.... DLL files function access
							System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
							videoClient = new Socket(ip, videoPort);
//							videoClient.setSoTimeout(5000);
							VideoCapture vc = new VideoCapture(0);
							Mat img = new Mat();
							BufferedImage B;
							videoStream = videoClient.getOutputStream();
							BufferedOutputStream bufVideoStream = new BufferedOutputStream(videoStream);

							if (vc.isOpened()) {
								while (true) {
									vc.grab();
									vc.read(img);
									B = toBufferedImage(img);
									g.setScreenView(g.userViewArea, B);
									if (g.VideoToggled) {
										ImageIO.write(B, "bmp", bufVideoStream);
										bufVideoStream.flush();
										B.flush();
									}
									else if(! g.VideoToggled) {
										BufferedImage bi = new BufferedImage(640, 480, ColorSpace.TYPE_RGB);
										ImageIO.write(bi, "bmp", bufVideoStream);
										bufVideoStream.flush();
										B.flush();
									}

								}
							}

						} catch (Exception e) {
							System.out.println("Video Off");
						}
						
					}
				};
				t2.start();
			}
		}

	}

	// Opencv MAT image to buffered image.
	public static BufferedImage toBufferedImage(Mat m) {

		if (!m.empty()) {
			int type = BufferedImage.TYPE_BYTE_GRAY;
			if (m.channels() > 1) {
				type = BufferedImage.TYPE_3BYTE_BGR;
			}
			int bufferSize = m.channels() * m.cols() * m.rows();
			byte[] b = new byte[bufferSize];
			m.get(0, 0, b); // get all the pixels
			BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
			final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			System.arraycopy(b, 0, targetPixels, 0, b.length);
			return image;
		}
		return null;
	}

    
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Client mc = new Client("192.168.0.166", 65534, 65535);
//		Client c=new Client(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]));
		// 127.0.0.1 for self testing
		// 192.168.249.69
		// 192.168.0.136
	}

}
