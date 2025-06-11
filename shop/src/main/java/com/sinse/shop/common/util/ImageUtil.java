package com.sinse.shop.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sinse.shop.common.config.Config;

// 이미지와 관련된 유용한 공통 기능을 제공해주는 유틸 클래스
public class ImageUtil {
	
	// 클래스패스로부터 이미지를 반환해주는 메서드
	public Image getImage(String filename,int width, int height) {
		// package 경로로부터 이미지 얻어오기
		URL url = this.getClass().getClassLoader().getResource(filename);
		Image img = null;
		
		try {
			BufferedImage bufferImg = ImageIO.read(url);
			img = bufferImg.getScaledInstance(
					width, 
				height, 
				Image.SCALE_SMOOTH
			);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return img; 
	}
}
