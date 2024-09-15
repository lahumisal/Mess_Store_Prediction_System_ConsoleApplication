package org.mess.pridict.model;
import java.sql.*;
import java.time.*;
public class BetweenDateStockModel {
	
	private String Pr_name;
	private int Pr_rate;
	private int Total_Qty;
	private String startdate;
	private String enddate;
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getPr_name() {
		return Pr_name;
	}
	public void setPr_name(String pr_name) {
		Pr_name = pr_name;
	}
	public int getPr_rate() {
		return Pr_rate;
	}
	public void setPr_rate(int pr_rate) {
		Pr_rate = pr_rate;
	}
	public int getTotal_Qty() {
		return Total_Qty;
	}
	public void setTotal_Qty(int total_Qty) {
		Total_Qty = total_Qty;
	}
	
	public BetweenDateStockModel() {
	}
	
}
