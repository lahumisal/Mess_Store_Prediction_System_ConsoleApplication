package org.mess.pridict.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.mess.pridict.model.*;

public class ProductRepository extends DBHelper {

	public List<ProductModel> list = new ArrayList<ProductModel>();
	public List<MaintainPerchessModel> maintainlist = new ArrayList<MaintainPerchessModel>();
	public LinkedHashMap<String, ArrayList<String>> map;
	public LinkedHashMap<String, ArrayList<MonthWiseProductModel>> monthwiseprice;
	public LinkedHashMap<Integer, ArrayList<YearWiseProductModel>> lis;
	public ArrayList<ProductModel> data = new ArrayList<ProductModel>();
	public HashMap<Integer, ArrayList<ViewProductStoke>> prodstoke;
	public HashMap<String, ArrayList<MonthWiseStockModel>> month_wise;
	public ArrayList<BetweenDateStockModel> betstk = new ArrayList<BetweenDateStockModel>();
	public ArrayList<ViewProductStoke> datewise = new ArrayList<ViewProductStoke>();
	public ArrayList<currStoke> stockList = new ArrayList<currStoke>();
	public LinkedHashMap<String, Double> monthlyPrices = new LinkedHashMap<String, Double>();// For prediction map

// ______________________________________________________________________________________________________________________________________

	/* Admin Login page */
	public boolean isLoginUser(LoginModel model) {
		try {
			ps = conn.prepareStatement("SELECT user, pass FROM Login WHERE user = ? AND pass = ?;");
			ps.setString(1, model.getUrname());
			ps.setString(2, model.getPass());
			rs = ps.executeQuery();

			return rs.next(); // Return true if a record is found, meaning the credentials are valid
		} catch (Exception e) {
			System.out.println("Error in isLoginUser method: " + e);
			return false;
		}
	}

// ______________________________________________________________________________________________________________________________________

	/* 1. Add Product in database page */
	public boolean isAddProduct(ProductModel model) { // add Product
		try {
			ps = conn.prepareStatement("insert into ProductMaster values('0',?,?,?)");
			ps.setString(1, model.getPr_Name());
			ps.setString(2, model.getPr_cate());
			ps.setInt(3, model.getPr_Price());
			int val = ps.executeUpdate();
			ps.close();
			rs.close();
			return val > 0 ? true : false;
		} catch (Exception e) {
			System.out.println(
					"You got error in ProductRepository class\nmethod name: isAddProduct \n your error is " + e);
			return false;
		}
	}

// ______________________________________________________________________________________________________________________________________

	/* View All Product List */
	public ArrayList<ProductModel> isViewProductList() { // View Product List

		try {

			ps = conn.prepareStatement("select *from ProductMaster ");
			rs = ps.executeQuery();
			while (rs.next()) {
				/* ArrayList<ProductModel> list = new ArrayList<ProductModel>(); */
				ProductModel model = new ProductModel();
				model.setPr_Id(rs.getInt(1));
				model.setPr_Name(rs.getString(2));
				model.setPr_cate(rs.getString(3));
				model.setPr_Price(rs.getInt(4));
				data.add(model);
			}
//			System.out.println(data);
			ps.close();
			rs.close();
			return data.size() > 0 ? data : null;

		}

		catch (Exception e) {
			System.out.println(
					"You got error in ProductRepository class\nmethod name: List<ProductModel> isViewProductList() \n your error is "
							+ e);
			return null;
		}
	}

// ______________________________________________________________________________________________________________________________________
	/* add bulk grocery data */
/*
	public boolean isAddGrocery() {
		try {
			FileReader fr = new FileReader(PathHelper.path + "PerchessProdList.csv");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			int val = 0;
			while ((line = br.readLine()) != null) {
				String data[] = line.split(",");

				int prodId = Integer.parseInt(data[1]);
				int quantity = Integer.parseInt(data[2]);
				String sDate = data[3];

				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date parsed = format.parse(sDate);
				java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());

				try (PreparedStatement ps = conn
						.prepareStatement("insert into grocerymaster (Pr_id, G_Quantity, G_Date) values(?,?,?)")) {
					ps.setInt(1, prodId);
					ps.setInt(2, quantity);
					ps.setDate(3, sqlDate);
					val = ps.executeUpdate();
					ps.close();
					rs.close();
				}
			}
			return val > 0 ? true : false;
		} catch (Exception e) {
			System.out.println("Error is " + e);
			return false;
		}
	}
*/
// ______________________________________________________________________________________________________________________________________

	/* Delete Product from database */
	public boolean isDelProduct(String Name) { // Delete product

		try {
			ps = conn.prepareStatement("DELETE FROM ProductMaster WHERE Pr_Name = ?");
			ps.setString(1, Name);
			int val = ps.executeUpdate();
			ps.close();
			rs.close();
			return val > 0 ? true : false;
		} catch (Exception e) {
			System.out.println("You got error in ProductRepository class(method name:isDelProduct(String Name))"
					+ " \n and your error is " + e);
			return false;
		}
	}

// ______________________________________________________________________________________________________________________________________

	/* Update Product price using ProductName */
	public boolean isupdatePrice(ProductModel model) { // update product price

		try {
			ps = conn.prepareStatement("update ProductMaster set Pr_rate=? where Pr_name like ?");
			ps.setInt(1, model.getPr_Price());
			ps.setString(2, model.getPr_Name());
			int rs = ps.executeUpdate();
			ps.close();
			return rs > 0 ? true : false;
		} catch (Exception e) {
			System.out.println(
					"You got error in ProductRepostory class\nmethod: isupdatePrice(ProductModel model)\nYour error is "
							+ e);
			return false;
		}
	}

// ______________________________________________________________________________________________________________________________________

	/* 2. View Product Date Wise */
	public List<MaintainPerchessModel> isViewDateWiseProductList() {
		List<MaintainPerchessModel> maintainlist = new ArrayList<>();

		try {
			ps = conn.prepareStatement(
					"select g.G_date, g.pr_id, p.Pr_name, sum(g.G_Quantity) as total_quantity,sum(g.G_Quantity * p.Pr_rate) as total_price from GroceryMaster g join ProductMaster p on g.pr_id = p.Pr_id group by g.G_date, g.pr_id order by g.G_date;");
			rs = ps.executeQuery();
			while (rs.next()) {
				MaintainPerchessModel MPmodel = new MaintainPerchessModel();
				MPmodel.setG_date(rs.getDate(1));
				MPmodel.setPr_id(rs.getInt(2));
				MPmodel.setPr_name(rs.getString(3));
				MPmodel.setTotal_QTY(rs.getInt(4));
				MPmodel.setTotal_Price(rs.getInt(5));
				maintainlist.add(MPmodel);
			}
			ps.close();
			rs.close();
			return maintainlist.size() > 0 ? maintainlist : null;
		} catch (Exception e) {
			System.out.println(
					"You got error in ProductRepository class\nmethod name: List<MaintainPerchessModel> isViewDateWiseProductList \n your error is "
							+ e);
			return null;
		}
	}

// ______________________________________________________________________________________________________________________________________

	/* View Product Month Wise */
	public LinkedHashMap<String, ArrayList<String>> isMonthWiseData() {

		try {
			this.map = new LinkedHashMap<String, ArrayList<String>>();
			ps = conn.prepareStatement("SELECT MONTHNAME(G_Date) AS Month from grocerymaster;");
			rs = ps.executeQuery();
			while (rs.next()) {
				String monthname = rs.getString(1);
				PreparedStatement ps1 = conn.prepareStatement(
						"SELECT p.Pr_Name FROM ProductMaster p INNER JOIN GroceryMaster g ON p.Pr_id = g.Pr_id where MONTHNAME(g.G_Date)=?;");
				ps1.setString(1, monthname);
				ResultSet rs1 = ps1.executeQuery();

				ArrayList<String> Products = new ArrayList<String>();
				while (rs1.next()) {
					Products.add(rs1.getString(1));
				}
				this.map.put(monthname, Products);
			}
			ps.close();
			rs.close();
			return map;
		} catch (Exception e) {
			System.out.println(
					"You got error in ProductRepository class\nmethod name: LinkedHashMap<String, ArrayList> isMonthWiseData()\n your error is "
							+ e);
			return null;
		}
	}

// ______________________________________________________________________________________________________________________________________

	/* View Product Month Wise */
	public LinkedHashMap<String, ArrayList<MonthWiseProductModel>> isMonthWiseRateData(MonthWiseProductModel model) {

		try {

			this.monthwiseprice = new LinkedHashMap<String, ArrayList<MonthWiseProductModel>>();

			ps = conn.prepareStatement("SELECT MONTHNAME(G_Date) AS Month from grocerymaster");
			rs = ps.executeQuery();

			while (rs.next()) {
				String monthname = rs.getString(1);
				PreparedStatement ps1 = conn.prepareStatement(
						"SELECT p.Pr_Name,p.Pr_rate FROM ProductMaster p INNER JOIN GroceryMaster g ON p.Pr_id = g.Pr_id where MONTHNAME(g.G_Date)=?;");
				ps1.setString(1, monthname);
				
				ResultSet rs1 = ps1.executeQuery();

				ArrayList<MonthWiseProductModel> Products = new ArrayList<MonthWiseProductModel>();
				while (rs1.next()) {
					MonthWiseProductModel product = new MonthWiseProductModel();
					product.setProducts(rs1.getString(1)); // Assuming setPrName is a method in MonthWiseProductModel
					product.setPrice(rs1.getInt(2)); // Assuming setPrRate is a method in MonthWiseProductModel
					Products.add(product);
				}
				this.monthwiseprice.put(monthname, Products);
			}
			return monthwiseprice;
		} catch (Exception e) {
			System.out.println(
					"You got error in ProductRepository class\nmethod name: LinkedHashMap<String, ArrayList> isMonthWiseData()\n your error is "
							+ e);
			return null;
		}
	}

//______________________________________________________________________________________________________________________________________

	/* 3. View Product Year Wise */
	public LinkedHashMap<Integer, ArrayList<YearWiseProductModel>> isYearWiseProductList(YearWiseProductModel model) {

		try {
			this.lis = new LinkedHashMap<Integer, ArrayList<YearWiseProductModel>>();
			PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT year(G_Date) AS Year FROM grocerymaster;");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int year = rs.getInt(1);
				PreparedStatement ps1 = conn.prepareStatement(
						"SELECT p.Pr_Name,p.pr_rate,g.G_Date FROM ProductMaster p INNER JOIN GroceryMaster g ON p.Pr_id = g.Pr_id WHERE year(g.G_Date) = ?;");
				ps1.setInt(1, year);
				ResultSet rs1 = ps1.executeQuery();

				ArrayList<YearWiseProductModel> productYear = new ArrayList<>();
				while (rs1.next()) {
					YearWiseProductModel ProductYearWise = new YearWiseProductModel();
					ProductYearWise.setProducts(rs1.getString(1));
					ProductYearWise.setDate(rs1.getDate(3)); // Assuming you have a setter for the date
					ProductYearWise.setPrice(rs1.getInt(2));
					productYear.add(ProductYearWise);
				}
				this.lis.put(year, productYear);
			}
			return lis;
		} catch (Exception e) {
			System.out.println(
					"You got error in ProductRepository class\nmethod name: LinkedHashMap<String, ArrayList> isMonthWiseData()\n your error is "
							+ e);
			return null;
		}
	}
// ______________________________________________________________________________________________________________________________________

	/* 4. Stock of Product Year Wise */
	public HashMap<Integer, ArrayList<ViewProductStoke>> isviewprdstoke(ViewProductStoke model) {
		HashMap<Integer, ArrayList<ViewProductStoke>> prodstoke = new HashMap<Integer, ArrayList<ViewProductStoke>>();

		try {
			ps = conn.prepareStatement("SELECT DISTINCT YEAR(G_Date) FROM grocerymaster;");
			rs = ps.executeQuery();

			while (rs.next()) {
				int year = rs.getInt(1);

				PreparedStatement ps1 = conn.prepareStatement(
						"SELECT p.Pr_Name,g.g_quantity,p.pr_rate FROM ProductMaster p INNER JOIN GroceryMaster g ON p.Pr_id = g.Pr_id WHERE year(g.G_Date) = ?;");
				ps1.setInt(1, year);
				ResultSet rs1 = ps1.executeQuery();

				ArrayList<ViewProductStoke> prodstk = new ArrayList<ViewProductStoke>();
				while (rs1.next()) {
					ViewProductStoke vps = new ViewProductStoke();
					vps.setPr_name(rs1.getString(1));
					vps.setPr_quantity(rs1.getInt(2));
					vps.setPr_price(rs1.getInt(3));
					prodstk.add(vps);
				}

				prodstoke.put(year, prodstk);
			}
			ps.close();
			rs.close();
			return prodstoke;
		} catch (Exception e) {
			System.out.println("Error in isviewprdstoke method: " + e);
			return null;
		}
	}
// ______________________________________________________________________________________________________________________________________

	/* Stock of Product month wise */
	public HashMap<String, ArrayList<MonthWiseStockModel>> isMonthWiseStock(MonthWiseStockModel model) {
		try {
			this.month_wise = new HashMap<String, ArrayList<MonthWiseStockModel>>();
			ps = conn.prepareStatement("SELECT MONTHNAME(stk_Date) AS Month from stoke;");
			rs = ps.executeQuery();
			while (rs.next()) {
				String monthname = rs.getString(1);

				PreparedStatement ps1 = conn.prepareStatement(
						"SELECT p.Pr_Name, p.Pr_rate FROM ProductMaster p INNER JOIN stoke g ON p.Pr_id = g.Pr_id  WHERE MONTHNAME(g.stk_Date) = ?;");
				ps1.setString(1, monthname);
				ResultSet rs1 = ps1.executeQuery();
				ArrayList<MonthWiseStockModel> monthwisestk = new ArrayList<MonthWiseStockModel>();
				while (rs1.next()) {
					MonthWiseStockModel monstk = new MonthWiseStockModel();
					monstk.setPr_name(rs1.getString(1));
					monstk.setStk_qty(rs1.getInt(2));
					monthwisestk.add(monstk);
				}
				this.month_wise.put(monthname, monthwisestk);
			}
			return month_wise;
		} catch (Exception e) {
			System.out.println("Error in isMonthWiseStoke" + e);
			return null;
		}
	}
// ______________________________________________________________________________________________________________________________________

	/* Stock of Product date wise */
	public ArrayList<ViewProductStoke> isdateWiseStoke(ViewProductStoke model) {
		try {
			ps = conn.prepareStatement(
					"SELECT s.Stk_date, p.Pr_Name,p.Pr_Rate, SUM(s.Stk_Quantity) AS Total_Quantity FROM stoke s INNER JOIN productmaster p ON s.Pr_id = p.Pr_id GROUP BY s.Stk_date, p.Pr_Name, p.Pr_Catrgory, p.Pr_Rate ORDER BY s.Stk_date;");
			rs = ps.executeQuery();
			while (rs.next()) {
				ViewProductStoke prodstk = new ViewProductStoke();
				prodstk.setDate(rs.getDate(1));
				prodstk.setPr_name(rs.getString(2));
				prodstk.setPr_price(rs.getInt(3));
				prodstk.setPr_quantity(4);
				datewise.add(prodstk);
			}
			return datewise.size() > 0 ? datewise : null;
		} catch (Exception e) {
			System.out.println("Error in isdateWiseStoke " + e);
			return null;
		}
	}

//_______________________________________________________________________________________________________________________________________

	/* Stock of Product between 2 date */
	public ArrayList<BetweenDateStockModel> isBetweenDate(BetweenDateStockModel model) {
		try {

			ps = conn.prepareStatement(
					"SELECT s.Stk_date,p.Pr_Name, p.Pr_Rate ,SUM(s.Stk_Quantity) AS Total_Quantity FROM stoke s INNER JOIN productmaster p ON s.Pr_id = p.Pr_id WHERE s.Stk_date BETWEEN ? AND ? GROUP BY s.Stk_date,p.Pr_Name, p.Pr_Rate ORDER BY s.Stk_date;");
			ps.setString(1, model.getStartdate());
			ps.setString(2, model.getEnddate());
			rs = ps.executeQuery();
			while (rs.next()) {
				BetweenDateStockModel betdatestk = new BetweenDateStockModel();
				betdatestk.setDate(rs.getString(1));
				betdatestk.setPr_name(rs.getString(2));
				betdatestk.setPr_rate(rs.getInt(3));
				betdatestk.setTotal_Qty(rs.getInt(4));
				betstk.add(betdatestk);
			}
			return betstk.size() > 0 ? betstk : null;

		} catch (Exception e) {
			System.out.println("Error in isBetweenDate " + e);
			return null;
		}
	}

// ______________________________________________________________________________________________________________________________________

	/* 5. Available Stock of Product */

	public ArrayList<currStoke> isCurrectStock(int getUsed_qty) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT p.pr_name, s.stk_quantity FROM productmaster p INNER JOIN stoke s ON p.pr_id = s.stk_id;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				currStoke stock = new currStoke();
				stock.setPrName(rs.getString(1));
				int updatedQty = rs.getInt(2) - getUsed_qty;
				stock.setUsed_qty(updatedQty);

				// Update the stock quantity in the database
				PreparedStatement ps1 = conn.prepareStatement(
						"UPDATE stoke SET stk_quantity = ? WHERE pr_id = (SELECT pr_id FROM productmaster WHERE pr_name = ?)");
				ps1.setInt(1, updatedQty);
				ps1.setString(2, stock.getPrName());
				ps1.executeUpdate();

				stockList.add(stock);
			}
			return stockList;
		} catch (Exception e) {
			System.out.println("Error in method isCurrectStock " + e);
			return null;
		}
	}

// ______________________________________________________________________________________________________________________________________
	/* 6.Prediction Logic */
	public int getProductId(getProductId model) {
		try {
			ps = conn.prepareStatement(
					"select Pm.pr_id,pm.pr_name from productmaster pm inner join grocerymaster gm on pm.pr_id=gm.pr_id where pm.Pr_name=?;");
			ps.setString(1, model.getProductName());
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Error is (getProductId)" + e);
			return 0;
		}
	}
	public Map<String, Double> getMonthlyMeanPrices(int productId) {
        try {
            ps = conn.prepareStatement("select monthname(gm.g_date) as month, avg(pm.pr_rate) as avg_price from productmaster pm inner join grocerymaster gm on pm.pr_id = gm.pr_id  where pm.pr_id = ? group by month;");
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String month = rs.getString("month");
                double avgPrice = rs.getDouble("avg_price");
                monthlyPrices.put(month, avgPrice);
            }
        } catch (Exception e) {
            System.out.println("Error in getMonthlyMeanPrices: " + e);
        }
        return monthlyPrices;
    }
/*
mysql> SELECT MONTHNAME(gm.g_date) as month, AVG(pm.pr_rate) as avg_price FROM productmaster pm INNER JOIN grocerymaster gm ON pm.pr_id = gm.pr_id  WHERE pm.pr_id = 2 GROUP BY month;
+----------+-----------+
| month    | avg_price |
+----------+-----------+
| March    |  201.0000 |
| April    |  201.0000 |
| December |  201.0000 |
+----------+-----------+
3 rows in set (0.01 sec)
 */
	public String getCheapestMonth(Map<String, Double> monthlyPrices) {
	    String cheapestMonth = "No data available";
	    double lowestPrice = Double.MAX_VALUE; // calculate the highest max Average value we assign max value first for lowestPrice

	    for (Map.Entry<String, Double> entry : monthlyPrices.entrySet()) {
	        if (entry.getValue() < lowestPrice) {
	            lowestPrice = entry.getValue();
	            cheapestMonth = entry.getKey();
	        }
	    }

	    return cheapestMonth;
	}

}
