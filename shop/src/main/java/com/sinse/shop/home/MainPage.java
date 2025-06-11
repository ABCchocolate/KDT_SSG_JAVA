package com.sinse.shop.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.util.ImageUtil;
import com.sinse.shop.common.view.Page;

public class MainPage extends Page {

	JPanel p_visual; // 메인 비주얼 여역(메인 베너 영역 - carousel영역)
	JPanel p_content; // 상품이 출력될 영역
	ImageUtil imageUtil = new ImageUtil();
	
	public MainPage() {

		// 생성
		p_visual = new JPanel() {
			// panel을 이름없는 익명클래스를 재정의하는 코드를 작성한다.
			// 별도의 .java 파일을 생성할 필요가 없고, 내부 클래스 이다 보니 외부클래스인 MainPage의 멤버를 공유할 수 있다
			@Override
			protected void paintComponent(Graphics g) {
				// super을 남겨놔야 업데이트에의해 지워진 배경을 스스로 복구한다.
				super.paintComponent(g);

				// 우리가 원하는 그림을 그리자... 즉 패널의 그림을 뺏어 그리자!!
				// toolkit 방식은 이미지를 구성하는 바이트 정보 접근이어렵다.
				// BufferedImage 객체를 이용하여 얻어온 이미지는 훨씬 더 다양한 제어가 가능하다.
				g.drawImage(imageUtil.getImage("images/model.jpg", Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT), 0, 0, Config.SHOPMAIN_WIDTH, Config.MAIN_VISUAL_HEIGHT, p_content);
			}
		};
		p_content = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

		// 스타일
		p_visual.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT));
		p_content.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, 410));

		this.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH,
				Config.SHOPMAIN_HEIGHT - (Config.NAVI_HEIGHT + Config.UTIL_HEIGHT)));

		p_visual.setBackground(Color.CYAN);
		p_content.setBackground(Color.RED);

		// 조립
		add(p_visual);
		add(p_content);

		setVisible(true);
	}
}
