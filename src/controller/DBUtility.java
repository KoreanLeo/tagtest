package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import application.AppMain;

public class DBUtility {
	public static Connection getConnetction() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jbc:mysql://localhost/studentdb","root", "123456");
			AppMain.callAlert("연결성공:DB 연결에 성공함");
		} catch (Exception e) {
			AppMain.callAlert("연결실패:DB 연결에 실패함"+e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null;
	}	
}
