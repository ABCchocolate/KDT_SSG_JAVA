package com.sinse.shopadmin.product.model;


//오직 레코드 1건을 담기 위한 객체를 모델 객체라고 한다.
public class SubCategory {
	private int subcategory_id;
	private String sub_name;
	private TopCategory topcategory; //DB에서 부모를 표현한 모델을 자식이 보유한다.
	public int getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(int subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public TopCategory getTopcategory() {
		return topcategory;
	}
	public void setTopcategory(TopCategory topcategory) {
		this.topcategory = topcategory;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return sub_name;
	}
	
	
	
	
}
