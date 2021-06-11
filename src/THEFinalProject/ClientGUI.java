package THEFinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame {

	JLabel userViewArea;
	boolean VideoToggled;
	boolean AudioToggled;

	ClientGUI() {
		VideoToggled = true;
		AudioToggled = true;
		setLayout(null);
		setSize(new Dimension(640, 640));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		JLabel j1 = new JLabel("User Video");
		j1.setBounds(25, 500, 100, 50);
		add(j1);
		JLabel j2 = new JLabel("User Audio");
		j2.setBounds(220, 500, 100, 50);
		add(j2);

		JRadioButton userVideoOn = new JRadioButton("ON", true);
		JRadioButton userVideoOff = new JRadioButton("OFF", false);
		ButtonGroup userVideo = new ButtonGroup();
		userVideo.add(userVideoOn);
		userVideo.add(userVideoOff);
		userVideoOn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VideoToggled = userVideoOn.isEnabled();
				System.out.println(VideoToggled);
			}

		});
		userVideoOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VideoToggled = !userVideoOn.isEnabled();
				System.out.println(VideoToggled);
			}

		});
		userVideoOn.setBounds(100, 500, 100, 30);
		userVideoOff.setBounds(100, 530, 100, 30);
		add(userVideoOn);
		add(userVideoOff);

		ButtonGroup userAudio = new ButtonGroup();
		JRadioButton userAudioOn = new JRadioButton("ON", true);
		JRadioButton userAudioOff = new JRadioButton("OFF", false);
		userAudio.add(userAudioOn);
		userAudio.add(userAudioOff);
		userAudioOn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AudioToggled = userVideoOn.isEnabled();
				System.out.println(AudioToggled);
			}

		});
		userAudioOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AudioToggled = !userVideoOn.isEnabled();
				System.out.println(AudioToggled);
			}

		});
		userAudioOn.setBounds(300, 500, 100, 30);
		userAudioOff.setBounds(300, 530, 100, 30);
		add(userAudioOn);
		add(userAudioOff);
		userViewArea = new JLabel();
		userViewArea.setBackground(Color.BLACK);

		// ClientViewArea = new JLabel();
		// ClientViewArea.setBackground(Color.BLACK);
		userViewArea.setBounds(0, 0, 640, 480);
		// ClientViewArea.setBounds(675, 0, 640, 480);
		add(userViewArea);
		// add(ClientViewArea);

	}

	public void setScreenView(JLabel j, BufferedImage b) {
		j.setIcon(VideoToggled ? new ImageIcon(b) : new ImageIcon());
		/*
		 *  if(b==null) { j.setIcon(newImageIcon()); } else {
		 * j.setIcon(VideoToggled? new ImageIcon(b):new ImageIcon()); }
		 */
	}

//	public void setScreenView(JLabel j, BufferedImage b, boolean toggleVideo) {
//		if (toggleVideo)
//			j.setIcon(new ImageIcon(b));
//		else
//			j.setIcon(new ImageIcon());
//	}
}
