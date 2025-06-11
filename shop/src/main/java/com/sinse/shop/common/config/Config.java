package com.sinse.shop.common.config;

//유저용 쇼핑몰에서 사용되는 모든 상수를 관리하는 클래스
public class Config {
	
	/*================================
	 * 쇼핑몰 앱 메인 설정
	 *================================*/
	//전체창의 크기
	public static final int SHOPMAIN_WIDTH = 1400;
	public static final int SHOPMAIN_HEIGHT = 900;
	
	//utility box의 크기
	public static final int UTIL_WIDTH = SHOPMAIN_WIDTH;
	public static final int UTIL_HEIGHT = 40;
	
	//navigation box의 크기
	public static final int NAVI_WIDTH = SHOPMAIN_WIDTH;
	public static final int NAVI_HEIGHT = 50;
	
	/*================================
	 * 메인페이지 설
	 ================================*/
	public static final int MAIN_VISUAL_WIDTH = SHOPMAIN_WIDTH;
	public static final int MAIN_VISUAL_HEIGHT = 400;
	
	
	/*================================
	 *페이지 정의
	 ================================*/
	public static final int MAIN_PAGE =0;
	public static final int PRODUCT_PAGE =1;
	public static final int CUSTOMER_PAGE =2;
	public static final int LOGIN_PAGE =3;
	public static final int JOIN_PAGE =4;
	public static final int CART_PAGE =5;
	public static final int WISHLIST_PAGE =6;
}
