package org.mess.pridict.model;
import java.sql.*;
public class ViewProductStoke {
	private String pr_name;
	private int pr_price;
	private Date date;
	private int pr_quantity;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPr_name() {
		return pr_name;
	}
	public void setPr_name(String pr_name) {
		this.pr_name = pr_name;
	}
	public int getPr_price() {
		return pr_price;
	}
	public void setPr_price(int pr_price) {
		this.pr_price = pr_price;
	}
	public int getPr_quantity() {
		return pr_quantity;
	}
	public void setPr_quantity(int pr_quantity) {
		this.pr_quantity = pr_quantity;
	}
	public ViewProductStoke() {
	}
	
}
