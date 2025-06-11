package common.config;

//유저용 쇼핑몰에서 사용되는 모든 상수를 관리하는 클래스
public class Config {

	/*
	 * ================================ 쇼핑몰 앱 메인 설정 ================================
	 */
	// 전체창의 크기
	public static final int ADMINMAIN_WIDTH = 1300;
	public static final int ADMINMAIN_HEIGHT = 800;

	// utility box의 크기
	public static final int UTIL_WIDTH = ADMINMAIN_WIDTH;
	public static final int UTIL_HEIGHT = 50;

	// navigation box의 크기
	public static final int SIDE_WIDTH = 200;
	public static final int SIDE_HEIGHT = ADMINMAIN_HEIGHT - UTIL_HEIGHT;

	/*
	 * ================================ 관리자 앱 메인 설정 ================================
	 */
	public static final int LOGIN_PAGE = 0; // 환경설정페이지
	public static final int MAIN_PAGE = 1; // 페이
	public static final int PRODUCT_PAGE = 2; // 상품관리페이지
	public static final int ORDER_PAGE = 3; // 주문관리페이지
	public static final int MEMBER_PAGE = 4; // 회원관리페이지
	public static final int CUSTOMER_PAGE = 5; // cs관리페이지
	public static final int CONFIG_PAGE = 6; // 환경설정페이지
	public static final int JOIN_PAGE = 7; //관리자 가입 페이
	
	/*
	 * ================================ 메인 페이지 상수 추가 ================================
	 */
	public static final int MAIN_PAGE_WIDTH = ADMINMAIN_WIDTH;

	//================================ 데이터 베이스 상수 추가=================================
	public static final String url = "jdbc:mysql://localhost:3306/shop";
	public static final String user = "shop";
	public static final String pwd = "12341234";
}
