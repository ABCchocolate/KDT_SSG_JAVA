package com.sinse.threadapp.ani.gallery;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Gallery extends JFrame {
	JPanel p_west; // 썸네일 그릴 패널
	JPanel p_north; // 컨트롤러 영역
	JPanel p_center; // 메인 이미지 영역
	JPanel p_container; // 북쪽 + 센터 감싸는 컨테이너

	ImageUtil imageUtil = new ImageUtil();
	Image[] imgArr = new Image[9]; // 이미지 배열 생성
	Rectangle[] rectArray = new Rectangle[imgArr.length];

	// 사각형 위치 관련 변수
	float currentX = 5;
	float currentY = 10;
	int targetX = 5;
	int targetY = 10;

	int selectedIndex = -1;

	public Gallery() {
		createImage(); // 이미지 생성

		p_west = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int i = 0; i < imgArr.length; i++) {
					g.drawImage(imgArr[i], 5, 10 + (95 * i), 90, 90, this);
				}
				if (selectedIndex != -1) {
					Graphics2D g2 = (Graphics2D) g;
					g2.setStroke(new BasicStroke(2));
					g2.setColor(Color.RED);
					g2.drawRect((int) currentX, (int) currentY, 90, 90);
				}
			}
		};

		// 썸네일 클릭 이벤트
		p_west.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < rectArray.length; i++) {
					if (rectArray[i].contains(e.getPoint())) {
						selectedIndex = i;
						targetX = rectArray[i].x;
						targetY = rectArray[i].y;
						p_center.repaint();
						System.out.println("You clicked thumbnail " + (i + 1));
						break;
					}
				}
			}
		});

		p_north = new JPanel();
		p_center = new JPanel() {
			private void paintComponenet(Graphics g) {
				super.paintComponent(g);
		
				g.drawImage(imgArr[selectedIndex], 0, 0, 800, 850, p_center);

			}
		};
		p_container = new JPanel();

		// 스타일
		p_west.setPreferredSize(new Dimension(100, 800));
		p_west.setBorder(new LineBorder(Color.BLACK));

		p_container.setLayout(new BorderLayout());
		p_container.add(p_north, BorderLayout.NORTH);
		p_container.add(p_center, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(p_west, BorderLayout.WEST);
		add(p_container, BorderLayout.CENTER);

		setSize(900, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 쓰레드 시작은 모든 컴포넌트 설정 이후!
		Thread thread = new Thread(() -> {
			while (true) {
				move(); // 위치 보정
				p_west.repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	public void createImage() {
		for (int i = 0; i < imgArr.length; i++) {
			imgArr[i] = imageUtil.getImage("geographic/animal" + (i + 1) + ".jpg", 90, 90);
			rectArray[i] = new Rectangle(5, 10 + (95 * i), 90, 90);
		}
	}

	// 감속도 공식을 적용한 부드러운 이동
	public void move() {
		float easing = 0.1f;

		float dx = targetX - currentX;
		float dy = targetY - currentY;

		currentX += dx * easing;
		currentY += dy * easing;

		// 아주 미세한 거리면 정지
		if (Math.abs(dx) < 0.5 && Math.abs(dy) < 0.5) {
			currentX = targetX;
			currentY = targetY;
		}
	}

	public static void main(String[] args) {
		new Gallery();
	}
}
