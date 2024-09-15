package org.mess.pridict.model;
import java.sql.*;
public class MonthWiseStockModel {
	private String Pr_name;
	private int stk_qty;
	private Date date;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPr_name() {
		return Pr_name;
	}
	public void setPr_name(String pr_name) {
		Pr_name = pr_name;
	}
	public int getStk_qty() {
		return stk_qty;
	}
	public void setStk_qty(int stk_qty) {
		this.stk_qty = stk_qty;
	}
	public MonthWiseStockModel(String pr_name, int stk_qty) {
		this.Pr_name = pr_name;
		this.stk_qty = stk_qty;
	}
	public MonthWiseStockModel() {
	}
	
}
