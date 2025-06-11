package com.sinse.threadapp.ani;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class FrameAnimation extends JFrame {
    private JPanel panel;
    private int frameIndex = 1;
    private final int MAX_FRAMES = 18;
    Thread thread;

    public FrameAnimation() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = getImage("warrior/image" + frameIndex + ".png", 100, 100);
                if (img != null) {
                    g.drawImage(img, 0, 0, null);
                } 
            }
        };

        add(panel);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 내부 익명 클래스로 스레드 정의
        thread = new Thread() {
            public void run() {
                while (true) {
                    move();             // 프레임 인덱스만 갱신
                    panel.repaint();    // 다시 그리기 요청
                    try {
                        Thread.sleep(100); // 100ms 간격 (10fps)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start(); // 애니메이션 시작
    }

    // 이미지 로딩 메서드
    public Image getImage(String filename, int width, int height) {
        URL url = this.getClass().getClassLoader().getResource(filename);
        if (url == null) return null;

        try {
            BufferedImage bufferImg = ImageIO.read(url);
            return bufferImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 프레임 인덱스 증가만 담당
    public void move() {
        frameIndex++;
        if (frameIndex > MAX_FRAMES) {
            frameIndex = 1;
        }
    }

    public static void main(String[] args) {
        new FrameAnimation(); // 애니메이션 실행
    }
}
