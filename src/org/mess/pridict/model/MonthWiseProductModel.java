package org.mess.pridict.model;

public class MonthWiseProductModel {
	private int price;
	private String products;
	private int year;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public MonthWiseProductModel(int price, String products, int year) {
		this.year=year;
		this.price = price;
		this.products = products;
	}
	public MonthWiseProductModel() {
	}
	
}
