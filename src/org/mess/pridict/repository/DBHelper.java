package org.mess.pridict.repository;
import java.sql.*;
import java.util.*;
public class DBHelper {
	protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/messpredict"; // Replace with your database URL
    private static final String DB_USER = "root"; // Replace with your database username
    private static final String DB_PASSWORD = "root"; // Replace with your database password

    public DBHelper() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            if(conn!=null) {
            System.out.println("Database connection successful.");
            }else {
            	System.out.println("not connect");
            }
        } catch (Exception e) {
          System.out.println(e);
        }
    }

}
