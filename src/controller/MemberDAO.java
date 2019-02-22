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
				AppMain.callAlert("select 실패 : select 쿼리문 실패 점검바람");
				return null;
			}
			while (rs.next()) {
				Member member = new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7));
				db1MemberList.add(member);
			}
		} catch (SQLException e) {
			AppMain.callAlert("삽입 실패 : 데이터 베이스에 자료 삽입 실패\n점검바람");
			e.printStackTrace();
		} finally {
			// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AppMain.callAlert("닫기 실패 : 자원 자료 psmt, con 닫기에 실패\n점검바람");
				e.printStackTrace();
			}
		}
return db1MemberList;
}
}
