/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package THEFinalProject;

import java.net.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import java.io.*;

public class AudioServer {
	ServerSocket s;
//	ServerSocket s1;
	Socket audioClient;
	// Socket videoClient;
	InputStream audioStream;
	// InputStream videoStream;
	BufferedInputStream bufVideoStream;
	// JLabel screen;

	@SuppressWarnings("unused")
	AudioServer(int Port) {

		try {
			s = new ServerSocket(Port);
			audioClient = s.accept();
			audioStream = audioClient.getInputStream();
			AudioFormat af = new AudioFormat(10000, 16, 2, true, true);
			// SourceDataLine used for speaker
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
			SourceDataLine speaker = (SourceDataLine) AudioSystem.getLine(info);
			speaker.open();
			speaker.start();
			byte b[];
//			ByteArrayInputStream bin;
			while (true) {
				b = new byte[32];

				if (b == null)
					System.out.println("AudOff");
				else {
					audioStream.read(b, 0, 32);
					speaker.write(b, 0, 32);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			AudioServer ms = new AudioServer(65534);
//			AudioServer s=new AudioServer(Integer.parseInt(args[0]));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
