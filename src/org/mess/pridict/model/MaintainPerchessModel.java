package org.mess.pridict.model;

import java.sql.*;

public class MaintainPerchessModel {
	private int Pr_id;
	private String Pr_name;
	private int Total_QTY;
	private int Total_Price;
	
	private Date G_date;

	public Date getG_date() {
		return G_date;
	}

	public void setG_date(Date g_date) {
		G_date = g_date;
	}

	public int getPr_id() {
		return Pr_id;
	}

	public void setPr_id(int pr_id) {
		this.Pr_id = pr_id;
	}

	public String getPr_name() {
		return Pr_name;
	}

	public void setPr_name(String pr_name) {
		this.Pr_name = pr_name;
	}

	public int getTotal_QTY() {
		return Total_QTY;
	}

	public void setTotal_QTY(int total_QTY) {
		this.Total_QTY = total_QTY;
	}

	public int getTotal_Price() {
		return Total_Price;
	}

	public void setTotal_Price(int total_Price) {
		this.Total_Price = total_Price;
	}

	public MaintainPerchessModel(int pr_id, String pr_name, int total_QTY, int total_Price) {
		this.Pr_id = pr_id;
		this.Pr_name = pr_name;
		this.Total_QTY = total_QTY;
		this.Total_Price = total_Price;
	}

	public MaintainPerchessModel() {
	}

}
