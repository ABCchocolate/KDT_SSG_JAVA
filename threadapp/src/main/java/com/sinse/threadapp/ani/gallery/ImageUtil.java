package com.sinse.threadapp.ani.gallery;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


// 이미지와 관련된 유용한 공통 기능을 제공해주는 유틸 클래스
public class ImageUtil {
	
	// 클래스패스로부터 이미지를 반환해주는 메서드
	public Image getImage(String filename, int width, int height) {
	    URL url = this.getClass().getClassLoader().getResource(filename);
	    System.out.println("URL 체크: " + url);  // <- 이 부분 꼭 확인

	    if (url == null) {
	        throw new IllegalArgumentException("리소스를 찾을 수 없습니다: " + filename);
	    }

	    try {
	        BufferedImage bufferImg = ImageIO.read(url);
	        return bufferImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
