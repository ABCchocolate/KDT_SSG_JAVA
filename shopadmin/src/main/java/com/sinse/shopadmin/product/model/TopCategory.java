package com.sinse.shopadmin.product.model;


//아래의 클래스는 로직을 담기위한 객체가 아니라 단지 DB의 topcategory 테이블의
//레코드 1건을 담기 위한 모델 객체
//또한 DB의 레코드를 담고 있기 떄문에 보안상 중요함..
//은닉화시켜주어야함!!!
public class TopCategory {
	private int topcategory_id;
	private String top_name;
	
	
	public int getTopcategory_id() {
		return topcategory_id;
	}
	public void setTopcategory_id(int topcategory_id) {
		this.topcategory_id = topcategory_id;
	}
	public String getTop_name() {
		return top_name;
	}
	public void setTop_name(String top_name) {
		this.top_name = top_name;
	}
	
	@Override
	public String toString() {
		// 누군가가 이 클래스를 String 취급하면서 출력하려고 한다면 자동으로호출되는
		//메서드인 Object로 부터 상속받은 toString() 메서드가 호출되므로,
		// 만일 이 객체의 데이터 중 원하는 데이터를 출력하려면 오버라이딩 하면된다.
		return top_name;
	}
}
