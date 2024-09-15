package org.mess.pridict.client;

import java.util.*;
import java.util.Map.Entry;
import org.mess.pridict.model.*;
import org.mess.pridict.service.*;

public class PredictionClientApplication {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			ProductModel pm = new ProductModel();
			ViewProductStoke vps = new ViewProductStoke();
			ProductService ps = new ProductService();
			MonthWiseStockModel monwisestk = new MonthWiseStockModel();
			MonthWiseProductModel mwp = new MonthWiseProductModel();
			LoginModel LogM = new LoginModel();
			BetweenDateStockModel betdatestoke = new BetweenDateStockModel();
			YearWiseProductModel YWPM = new YearWiseProductModel();
			currStoke cs = new currStoke();

			char choice = 'y';
			do {
				System.out.println("Enter username:");
				String urname = sc.next();
				System.out.println("Enter password:");
				String pass = sc.next();
				LogM.setUrname(urname);
				LogM.setPass(pass);

				if (ps.isLoginUser(LogM)) {
					System.err.println("****Login successfull****");
					do {
						System.out.println("1. Add new products or material/view/delete/update\r\n"
								+ "2. Maintain the purchase order of products datewise and month wise as well as year wise\r\n"
								+ "3. View the month wise product list \r\n"
								+ "4. View the product wise stock year wise /month wise /datewise/between date\r\n"
								+ "5. View the available current available stock\r\n"
								+ "6. System should provide predication for new stock in upcoming days or month \r\n"
								+ "7. Exit from project.");

						System.out.println("Enter choice:");
						int ch = sc.nextInt();
						switch (ch) {
						case 1:
							System.err
									.println("You choice is Add new products or material/view/delete/update case\r\n");

							do {
								System.out.println(
										"1.Add new products or material\n 2.Add bulk deta\n3.View Product.\n4.Delete Product.\n5.Update Product.\n");
								System.out.println("Enter choice for above 4 cases");
								ch = sc.nextInt();
								switch (ch) {
								case 1:
									sc.nextLine();
									System.out.println("Enter product Name");
									String product = sc.nextLine();
									System.out.println("Enter category");
									String cate = sc.nextLine();
									System.out.println("Enter Product Price");
									int price = sc.nextInt();
									pm.setPr_cate(cate);
									pm.setPr_Name(product);
									pm.setPr_Price(price);

									boolean b = ps.isAddProduct(pm);
									if (b) {
										System.err.println("Product Added successfully....");
									} else {
										System.out.println("Sorry....Product not added....");
									}
									System.out.println();
									break;
									/*
								case 2:
									boolean add = ps.isAddGrocery();
									if (add)
										System.out.println("Bulk purchess procuct list added successfully");
									else
										System.out.println("bulk purchess product not added...");
									break;
									*/
								case 3:
									System.out.println("All Product List as Follows:");
									ArrayList<ProductModel> list1 = ps.isViewProductList();
									System.out.println("Count====>" + list1.size());
									int count = 0;
									if (count == 0) {
										System.err.println("Pr_id | Pr_Name   | Pr_Catrgory | Pr_Rate");
									}
									list1.forEach((m) -> System.out.println(m.getPr_Id() + "\t" + m.getPr_Name() + "\t"
											+ m.getPr_cate() + "\t" + m.getPr_Price()));

									System.out.println();
									break;
								case 4:
									sc.nextLine();
									System.out.println("Enter product name for delete");
									String Name = sc.nextLine();
									boolean del = ps.isDelProduct(Name);
									if (del) {
										System.out.println("Product successfully deleted");
									} else {
										System.out.println("Product not deleted.....");
									}
									System.out.println();
									break;
								case 5:
									sc.nextLine();
									System.out.println("Enter update Name for update price");
									Name = sc.nextLine();
									System.out.println("Enter update Price");
									price = sc.nextInt();
									pm.setPr_Price(price);
									pm.setPr_Name(Name);

									boolean update = ps.isupdatePrice(pm);

									if (update)
										System.out.println("Updated");
									else
										System.out.println("Not updated");
									break;
								default:
									if (ch > 5)
										System.err.println(
												"OOps...You Enter wrong choice now you are in basic requirement case\n");
									break;
								}
							} while (ch <= 4);
							break;
// =======================================================================================================================================
						case 2:
							System.err.println(
									"You choice is  Maintain the purchase order of products datewise and month wise as well as year wise\n");
							do {
								System.out
										.println("1.Maintain datewise\n2.Maintain month wise.\n3.Maintain year wise\n");
								System.out.println("Enter choice for above 3 cases");
								ch = sc.nextInt();
								switch (ch) {
								case 1:
									System.out.println("All Product List datewise:");
									List<MaintainPerchessModel> MPM = ps.isViewDateWiseProductList();
									int count = 0;
									if (MPM != null) {
										if (count == 0) {
											System.err.println("Pr_name | total_quantity | total_price | G_date");
										}
										MPM.forEach((m) -> System.out.println(m.getPr_name() + "\t" + m.getTotal_QTY()
												+ "\t" + m.getTotal_Price() + "\t" + m.getG_date()));
									} else {
										System.out.println("Sorry....No product found");
									}
									System.out.println();
									break;
								case 2:
									System.out.println("All Product List monthwise");
									LinkedHashMap<String, ArrayList<String>> Products = ps.isMonthWiseData();
									Set<Map.Entry<String, ArrayList<String>>> set = Products.entrySet();
									for (Map.Entry<String, ArrayList<String>> m : set) {
										ArrayList<String> values = m.getValue();
										System.out.println("Month Name---> " + m.getKey());
										System.out.println("=========================================");
										for (String ProductsName : values) {
											System.out.println(ProductsName);
										}
										System.out.println("------------------------------------\n\n");
									}
									break;
								case 3:
									System.out.println("All Product List YearWise");
									LinkedHashMap<Integer, ArrayList<YearWiseProductModel>> prodyear = ps.isYearWiseProductList(YWPM);
									for (Map.Entry<Integer, ArrayList<YearWiseProductModel>> entry : prodyear
											.entrySet()) {
										int year = entry.getKey();
										ArrayList<YearWiseProductModel> products = entry.getValue();
										System.out.println("Year---> " + year);
										count = 0;
										int grandTotalOFPrice = 0;
										if (count == 0) {
											System.out.println("Product\t\tPrice\t\tDate");
											System.out.println("_________________________________________");
										}
										for (YearWiseProductModel product : products) {

											System.out.println(product.getProducts() + "\t" + product.getPrice() + "\t "
													+ product.getDate());
											grandTotalOFPrice += product.getPrice();
										}
										System.err.println(
												"Total " + grandTotalOFPrice + " $ pay in " + year + "for products");
										System.out.println("____________________________________________");
									}
									break;
								default:
									if (ch > 3)
										System.err.println(
												"OOps...You Enter wrong choice now you are in basic requirement case\n");
									break;
								}
							} while (ch <= 3);
							break;
// =======================================================================================================================================
						case 3:
							System.out.println("All Product List monthwise");
							LinkedHashMap<String, ArrayList<MonthWiseProductModel>> Products = ps
									.isMonthWiseRateData(mwp);
							Set<Map.Entry<String, ArrayList<MonthWiseProductModel>>> set = Products.entrySet();

							for (Entry<String, ArrayList<MonthWiseProductModel>> m : set) {

								ArrayList<MonthWiseProductModel> values = m.getValue();
								System.out.println("Month Name---> " + m.getKey());

								System.out.println("=========================================");
								for (MonthWiseProductModel pr_data : values) {
									System.out.println(pr_data.getProducts() + "\t" + pr_data.getPrice());
								}
//							System.err.println("\nYou pay total " + grandTotal + " in this Month on products");
								System.out.println("------------------------------------\n\n");
							}
							break;
// =======================================================================================================================================
						case 4:
							System.err.println(
									"You choice is View the product wise stock year wise /month wise /datewise/between date\r\n");
							do {
								System.out.println(
										"1.view product stock year wise\n2.view product stock month wise.\n3.date wise \n4.view product stock between dates\n");
								System.out.println("Enter choice for above 4 cases");
								ch = sc.nextInt();
								switch (ch) {
								case 1:
									System.out.println("Product Year wise stock is as follows:");
									HashMap<Integer, ArrayList<ViewProductStoke>> prodstk = ps.isviewprdstoke(vps);
									Set<Entry<Integer, ArrayList<ViewProductStoke>>> set1 = prodstk.entrySet();

									for (Map.Entry<Integer, ArrayList<ViewProductStoke>> e1 : set1) {
										ArrayList<ViewProductStoke> values = e1.getValue();
										int year = e1.getKey();

										System.out.println("Year: " + year);
										System.out.println("===============================");

										for (ViewProductStoke p : values) {
											System.out.println(p.getPr_name() + "\t" + p.getPr_quantity() + "\t"
													+ p.getPr_price());
										}
										System.out.println("------------------------------\n");
									}
									break;
								case 2:
									System.out.println("Product month wise stock is as follows:");
									HashMap<String, ArrayList<MonthWiseStockModel>> monlist = ps
											.isMonthWiseStock(monwisestk);
									Set<Entry<String, ArrayList<MonthWiseStockModel>>> monthlist = monlist.entrySet();
									for (Map.Entry<String, ArrayList<MonthWiseStockModel>> entry : monthlist) {
										String key = entry.getKey();
										ArrayList<MonthWiseStockModel> val = entry.getValue();
										System.out.println("month name:" + key);
										System.out.println("_________________________________");
										for (MonthWiseStockModel List : val) {
											System.out.println(List.getPr_name() + "\t" + List.getStk_qty());
										}
										System.err.println("==============================================");
									}
									break;
								case 3:
									System.out.println("Product date wise stock is as follows:");
									System.err.println("================================================");
									ArrayList<ViewProductStoke> datewise = ps.isDateWiseStoke(vps);
									if (datewise != null) {
										datewise.forEach((stoke) -> System.out.println(stoke.getPr_name() + "\t"
												+ stoke.getPr_quantity() + "\t" + stoke.getPr_price()));
									} else {
										System.out.println("data not found");
									}
									break;
								case 4:// please checckk
									sc.nextLine();
									System.out.println("Product stock between dates is as follows:");
									System.out.println("Enter first date(eg.2001/01/23):");
									String fdate = sc.nextLine();
									System.out.println("Enter last date(eg.2001/01/23):");
									String ldate = sc.nextLine();
									betdatestoke.setStartdate(fdate);
									betdatestoke.setEnddate(ldate);
									int c = 0;
									ArrayList<BetweenDateStockModel> betdate = ps.isBetweenDate(betdatestoke);
									if (betdate != null) {
										if (c == 0) {
											System.out.println("Pr_name       Pr_rate      Pr_totalqty");
										}
										betdate.forEach((li) -> System.out.println(li.getPr_name() + "\t"
												+ li.getPr_rate() + " $\t" + li.getTotal_Qty() + " KG"));
									} else {
										System.out.println("Date Not Found");
									}
									break;
								default:
									if (ch > 4)
										System.err.println(
												"OOps...You Enter wrong choice now you are in basic requirement case\n");
									break;
								}
							} while (ch <= 3);
							break;
// =======================================================================================================================================
						case 5:
							System.out.println("Current available stock is as follows:");
							sc.nextLine();
							System.out.println("Enter used product name:");
							String name = sc.nextLine();
							System.out.println("Enter total used quantity:");
							int used = sc.nextInt();
							cs.setPrName(name);
							cs.setUsed_qty(used);
							ArrayList<currStoke> currentStock = ps.isCurrecntStock(used);

							for (currStoke stock : currentStock) {
								System.out.println(
										"Product Name: " + stock.getPrName() + ",\n Quantity: " + stock.getUsed_qty());
								System.out.println();
							}

							break;
// =======================================================================================================================================
						case 6:
							System.err.println(
									"You choice is System should provide predication for new stock in upcoming days or month\n");
							sc.nextLine();
							System.out.println("Enter Product Name:");
							String ProductName = sc.nextLine();
							getProductId prodId=new getProductId();
							prodId.setProductName(ProductName);
							int productId = ps.getProductId(prodId);

							Map<String, Double> monthlyPrices = ps.getMonthlyMeanPrices(productId);
							if (monthlyPrices.isEmpty()) {
								System.out.println("No price data available for the product.");
								return;
							}
							String cheapestMonth = ps.getCheapestMonth(monthlyPrices);
							System.err.println("The month in which the product is cheapest: " + cheapestMonth);
							System.out.println();
							
							break;
						case 7:
							System.out.println("Thank You");
							System.exit(0);
							break;
						default:
							System.out.println(
									"OOps...Worng Choice. You have only 7 requirement cases please Enter currect choice.");
						}
					} while (true);
				} else {
					System.out.println("Wrong username and password");

					System.err.println("Enter (Y/y) if you want to continue login :");
					choice = sc.next().charAt(0);
				}
			} while (choice == 'y' || choice == 'Y');
		}catch (Exception e) {
			System.out.println("You got Error is "+e);
		}
	}

}
