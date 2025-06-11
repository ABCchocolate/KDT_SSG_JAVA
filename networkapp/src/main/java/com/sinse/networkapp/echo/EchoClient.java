package com.sinse.networkapp.echo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame {
	JPanel p_north;
	JComboBox<String> cb_port;
	JTextField t_port;
	JButton bt_port;
	JPanel p_center;
	JTextArea t_text;
	JTextField t_input;

	Socket socket;// 이 객체를 메모리에 올릴 때 접속이 발생한다.
					// 또한 접속이 성공되면, 그 시점부터 연결이 이루어진 것이므로, 스트림을 통해 데이터를 주고 받을 수 있
	BufferedWriter buffw;
	BufferedReader buffr;


	public EchoClient() {
		p_north = new JPanel();
		cb_port = new JComboBox<String>();
		t_port = new JTextField("9999", 8);
		bt_port = new JButton("접속");
		p_center = new JPanel();
		t_text = new JTextArea();
		t_input = new JTextField();

		cb_port.setPreferredSize(new Dimension(200, 30));
		t_port.setPreferredSize(new Dimension(60, 30));
		bt_port.setPreferredSize(new Dimension(40, 30));

		t_text.setPreferredSize(new Dimension(300, 350));
		t_input.setPreferredSize(new Dimension(300, 50));

		p_north.add(cb_port);
		p_north.add(t_port);
		p_north.add(bt_port);

		p_center.add(t_text);
		p_center.add(t_input);

		add(p_north, BorderLayout.NORTH);
		add(p_center);

		cb_port.addItem("192.168.60.4");
		cb_port.addItem("192.168.60.21");
		cb_port.addItem("192.168.60.45");
		cb_port.addItem("192.168.60.32");

		bt_port.addActionListener(e -> {
			connect();
		});

		// 람다는 반드시 함수형 인터페이스일 때에만...
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//서버로 내보내기(출력)
					String msg = t_input.getText();
					try {
						send(msg);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});

		setVisible(true);
		setSize(new Dimension(400, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	//내보내야 함으로 ... 필요한 스트림은output tmxmfla 
	public void send(String msg) throws IOException {
		buffw.write(msg + "\n"); //서버로 한줄보기
		
	}

	public void connect() {
		String ip = (String) cb_port.getSelectedItem();
		String port = t_port.getText();
		try {
			socket = new Socket(ip, Integer.parseInt(port));

			// socket stream 을 얻어오자
		
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new EchoClient();
		new EchoGUIServer();
	}

}
