package org.mess.pridict.model;

import java.util.List;

public class ProductModel {
	private int Pr_Id;
	private String Pr_Name;
	private String Pr_cate;
	private int Pr_Price;
	@Override
	public String toString() {
		return  " getPr_Name()=" + getPr_Name() + ", getPr_cate()="
				+ getPr_cate() + ", getPr_Price()=" + getPr_Price() + ", getClass()=" + getClass() ;
	}
	public int getPr_Id() {
		return Pr_Id;
	}
	public void setPr_Id(int Pr_Id) {
		this.Pr_Id = Pr_Id;
	}
	public String getPr_Name() {
		return Pr_Name;
	}
	public void setPr_Name(String pr_Name) {
		this.Pr_Name = pr_Name;
	}
	public String getPr_cate() {
		return Pr_cate;
	}
	public void setPr_cate(String pr_cate) {
		this.Pr_cate = pr_cate;
	}
	public int getPr_Price() {
		return Pr_Price;
	}
	public void setPr_Price(int pr_Price) {
		this.Pr_Price = pr_Price;
	}
	public ProductModel(int Pr_Id,String pr_Name, String pr_cate, int pr_Price) {
		this.Pr_Id=Pr_Id;
		this.Pr_Name = pr_Name;
		this.Pr_cate = pr_cate;
		this.Pr_Price = pr_Price;
	}
	public ProductModel() {
	}
	
	
}
