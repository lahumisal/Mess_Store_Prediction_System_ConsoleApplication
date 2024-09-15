package org.mess.pridict.config;

import java.util.*;
import java.io.*;
import java.sql.*;

public class DBConfig {
	protected Connection conn;
	protected ResultSet rs;
	protected CallableStatement cs;
	protected PreparedStatement ps;

/*	public static void main(String[] args) {
		new DBConfig();
	}
*/
	public DBConfig() {
		try {
			
			Properties p=new Properties();
			p.load(PathHelper.fin);
			
			String classname=p.getProperty("driver.classname");
			String user=p.getProperty("db.username");
			String pass=p.getProperty("db.password");
			String url=p.getProperty("db.url");

			
			Class.forName(classname);
			conn=DriverManager.getConnection(url,user,pass);
			if(conn!=null) {
				System.out.println("Database connect successfully");
			}else {
				System.out.println("Database is not connected");
			}
		} catch (Exception e) {
			System.out.println("You got error in DBConfig Class and error is "+e);
		}
	}
}
