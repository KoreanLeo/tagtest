package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.AppMain;
import model.Member;

public class MemberDAO {
	public static ArrayList<Member> db1MemberList= new ArrayList<>();
	public static ArrayList<Member>getMemberTotalData(){
		String selectMember= "select * from member";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnetction();
			psmt=con.prepareStatement(selectMember);
			rs = psmt.executeQuery();
			if (rs == null) {
				AppMain.callAlert("select ���� : select ������ ���� ���˹ٶ�");
				return null;
			}
			while (rs.next()) {
				Member member = new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7));
				db1MemberList.add(member);
			}
		} catch (SQLException e) {
			AppMain.callAlert("���� ���� : ������ ���̽��� �ڷ� ���� ����\n���˹ٶ�");
			e.printStackTrace();
		} finally {
			// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AppMain.callAlert("�ݱ� ���� : �ڿ� �ڷ� psmt, con �ݱ⿡ ����\n���˹ٶ�");
				e.printStackTrace();
			}
		}
return db1MemberList;
}
}
