package com.sinse.networkapp.echo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoGUIServer extends JFrame {
    JPanel p_north;
    JTextField t_port;           // 포트번호 입력
    JButton bt_start;            // 서버 시작 버튼
    JButton bt_stop;             // 서버 중지 버튼
    JPanel p_center;
    JTextArea t_text;            // 로그 출력

    ServerSocket server;
    Thread thread;

    public EchoGUIServer() {
        p_north = new JPanel();
        t_port = new JTextField("9999", 8);
        bt_start = new JButton("서버 시작");
        bt_stop = new JButton("서버 중지");
        p_center = new JPanel();
        t_text = new JTextArea();

        t_port.setPreferredSize(new Dimension(100, 30));
        bt_start.setPreferredSize(new Dimension(100, 30));
        bt_stop.setPreferredSize(new Dimension(100, 30));
        t_text.setPreferredSize(new Dimension(280, 300));

        p_north.add(t_port);
        p_north.add(bt_start);
        p_north.add(bt_stop);
        p_center.add(t_text);

        add(p_north, BorderLayout.NORTH);
        add(p_center);

        // 서버 시작
        bt_start.addActionListener(e -> startServer());

        // 서버 중지
        bt_stop.addActionListener(e -> stopServer());

        setVisible(true);
        setBounds(300, 200, 320, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void startServer() {
        if (server != null && !server.isClosed()) {
            t_text.append("⚠️ 서버는 이미 실행 중입니다.\n");
            return;
        }

        String port = t_port.getText();
        thread = new Thread(() -> {
            try {
                server = new ServerSocket(Integer.parseInt(port));
                t_text.append("✅ 서버가 포트 " + port + " 에서 실행 중입니다...\n");

                while (!server.isClosed()) {
                    Socket socket = server.accept(); // 클라이언트 접속 대기
                    String clientIP = socket.getInetAddress().getHostAddress();
                    t_text.append("🔌 클라이언트 접속: " + clientIP + "\n");
                    socket.close(); // 간단한 접속 확인용 처리
                }
            } catch (IOException e) {
                t_text.append("❌ 서버 실행 실패: " + e.getMessage() + "\n");
                e.printStackTrace();
            }
        });

        thread.start();
    }

    public void stopServer() {
        try {
            if (server != null && !server.isClosed()) {
                server.close();
                t_text.append("🛑 서버를 종료했습니다.\n");
            } else {
                t_text.append("⚠️ 서버가 실행 중이 아닙니다.\n");
            }
        } catch (IOException e) {
            t_text.append("❌ 서버 종료 중 오류 발생: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EchoGUIServer();
    }
}
