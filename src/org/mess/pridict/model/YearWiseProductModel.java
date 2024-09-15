package org.mess.pridict.model;
import java.sql.*;
public class YearWiseProductModel {
	private int price;
	private String products;
	private Date date;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public YearWiseProductModel(int price, String products,Date date) {
		this.price = price;
		this.products = products;
		this.date =date;
	}
	public YearWiseProductModel() {
	}
	
}
