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
			AppMain.callAlert("���Ἲ��:DB ���ῡ ������");
		} catch (Exception e) {
			AppMain.callAlert("�������:DB ���ῡ ������"+e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null;
	}	
}
