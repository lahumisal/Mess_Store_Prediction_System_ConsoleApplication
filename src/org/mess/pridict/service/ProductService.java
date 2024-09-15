package org.mess.pridict.service;

import java.util.*;

import org.mess.pridict.model.*;
import org.mess.pridict.repository.*;

public class ProductService {
	ProductRepository proRepo = new ProductRepository();

	public boolean isLoginUser(LoginModel model) {
		return proRepo.isLoginUser(model);
	}

	public boolean isAddProduct(ProductModel model) {
//		boolean b=proRepo.isAddProduct(model);
//		return b;
		return proRepo.isAddProduct(model);
	}

	public ArrayList<ProductModel> isViewProductList() {

		return proRepo.isViewProductList();
	}

	public boolean isDelProduct(String Name) {
		return proRepo.isDelProduct(Name);
	}

	public boolean isupdatePrice(ProductModel model) {
		return proRepo.isupdatePrice(model);
	}

	public List<MaintainPerchessModel> isViewDateWiseProductList() {
		return proRepo.isViewDateWiseProductList();
	}

	public LinkedHashMap<String, ArrayList<String>> isMonthWiseData() {
		return proRepo.isMonthWiseData();
	}

	public LinkedHashMap<String, ArrayList<MonthWiseProductModel>> isMonthWiseRateData(MonthWiseProductModel model) {
		return proRepo.isMonthWiseRateData(model);
	}

	public LinkedHashMap<Integer, ArrayList<YearWiseProductModel>> isYearWiseProductList(YearWiseProductModel model) {
		return proRepo.isYearWiseProductList(model);
	}
	

	public HashMap<Integer, ArrayList<ViewProductStoke>> isviewprdstoke(ViewProductStoke model){
		return proRepo.isviewprdstoke(model);
	}
	
	public HashMap<String,ArrayList<MonthWiseStockModel>> isMonthWiseStock(MonthWiseStockModel model){
		return proRepo.isMonthWiseStock(model);
	}
	public ArrayList<ViewProductStoke> isDateWiseStoke(ViewProductStoke model){
		return proRepo.isdateWiseStoke(model);
	}
	public ArrayList<BetweenDateStockModel> isBetweenDate(BetweenDateStockModel model){
		return proRepo.isBetweenDate(model);
	}
	
	public ArrayList<currStoke> isCurrecntStock(int getUsed_qty){
		return proRepo.isCurrectStock(getUsed_qty);
	}

//	public boolean isAddGrocery() {
//		return proRepo.isAddGrocery();
//	}
	
	public int getProductId(getProductId model) {
		return proRepo.getProductId(model);
	}
	/*
	public int getMeanOfX(int productId) {
		return proRepo.getMeanOfX(productId);
	}
	public int getmeanOfY(int productId) {
		return proRepo.getMeanOfY(productId);
	}
	public double calculateSlope(int productId, double meanX, double meanY) {
		return proRepo.calculateIntercept(meanX, meanY, meanY);
	}
	 public double calculateIntercept(double meanX, double meanY, double slope) {
		 return proRepo.calculateIntercept(meanX, meanY, slope);
	 }
	 */
	public Map<String, Double> getMonthlyMeanPrices(int productId){
		return proRepo.getMonthlyMeanPrices(productId);
	}
	public String getCheapestMonth(Map<String, Double> monthlyPrices) {
		return proRepo.getCheapestMonth(monthlyPrices);
	}
}
