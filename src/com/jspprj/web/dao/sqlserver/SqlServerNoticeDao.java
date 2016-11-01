/*package com.jspprj.web.dao.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jspprj.web.dao.NoticeDao;
import com.jspprj.web.entities.Notice;
import com.jspprj.web.model.NoticeModel;

public class SqlServerNoticeDao implements NoticeDao {

	@Override
	public Notice get(String code) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServer");		
		String url = "jdbc:sqlserver://jsp.newlecture.com:1433;databaseName=newlecture3";
		String sql = "select * from notice_view where code = ?";
		Connection con = DriverManager.getConnection(url, "jsp", "dclass");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, code);
		ResultSet rs = st.executeQuery();

		Notice notice = new Notice();

		if (rs.next()) {
			notice.setCode(rs.getString("code"));
			notice.setTitle(rs.getString("title"));
			notice.setWriter(rs.getString("writer"));
			notice.setContent(rs.getString("content"));
			notice.setRegdate(rs.getDate("regdate"));
			notice.setHit(rs.getInt("hit"));
		}

		rs.close();
		st.close();
		con.close();

		return notice;
	}

	@Override
	public List<NoticeModel> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {  //왜 NoticeModel 인가?
		String sql = "SELECT * " +                     //view를 사용하는 이유?
				"   FROM ( " +
				"         SELECT ROWNUM NUM , N.* " +
				"         FROM ( " +
				"               SELECT * " + 
				"               FROM  " +
				"                  NOTICE_VIEW  " +
				"               WHERE "+field+" LIKE ?  " +
				"               ORDER BY REGDATE DESC " +
				"            ) N  " +
				"         ) " +
				"         WHERE NUM BETWEEN ? AND ?";

		int startNum = 1 + (page-1)*10;
		int endNum = startNum*10;
		query = "%" + query + "%";

		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, query);
		pstmt.setInt(2, startNum);
		pstmt.setInt(3, endNum);

		ResultSet rs = pstmt.executeQuery();

		List<NoticeModel> list = new ArrayList<>();

		while (rs.next()) {
			NoticeModel notice = new NoticeModel();
			notice.setCode(rs.getString("code"));
			notice.setTitle(rs.getString("title"));
			notice.setWriter(rs.getString("writer"));
			notice.setContent(rs.getString("content"));
			notice.setRegdate(rs.getDate("regdate"));
			notice.setHit(rs.getInt("hit"));
			notice.setCmtCnt(rs.getInt("CMTCNT"));		        
			list.add(notice);
		}

		rs.close();
		pstmt.close();
		con.close();

		return list;
	}

	@Override
	public List<NoticeModel> getList(int page) throws ClassNotFoundException, SQLException {

		return this.getList(page, "TITLE", "");
	}

	@Override
	public List<NoticeModel> getList() throws ClassNotFoundException, SQLException {

		return this.getList(1, "TITLE", "");
	}

	
	public int insert(Notice notice) throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String sqlCode = "SELECT NVL(MAX(TO_NUMBER(CODE)), 0)+1 CODE FROM NOTICE";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";	
		Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
		Statement stCode = con.createStatement();
		ResultSet rs = stCode.executeQuery(sqlCode);

		String sql = "INSERT INTO NOTICE("
				+ "CODE, "
				+ "TITLE, "
				+ "WRITER, "
				+ "CONTENT, "
				+ "REGDATE, "
				+ "HIT) "
				+ "VALUES(?,?,?,?,SYSDATE,0)";
		String code=null;
		if(rs.next())
			code = rs.getString("CODE");
		rs.close();
		stCode.close();
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, code);
		st.setString(2, notice.getTitle());
		st.setString(3, notice.getWriter());
		st.setString(4, notice.getContent());
		
		int result = st.executeUpdate();
		
		con.close();
		st.close();

		

		return result;

	}

	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String sql = "UPDATE NOTICE "
				+ "SET TITLE = ?, "
				+ "WRITER = ?, "
				+ "CONTENT = ?, ";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";	
		Connection con = DriverManager.getConnection(url, "c##sist", "dclass");

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getWriter());
		st.setString(3, notice.getContent());
		int result = st.executeUpdate();

		st.close();
		con.close();
		
		return result;

	}

	public int delete(String code) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String sql = "DELETE NOTICE WHERE CODE=?";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";	
		Connection con = DriverManager.getConnection(url, "c##sist", "dclass");

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, code);
		int result = st.executeUpdate();

		st.close();
		con.close();

		return result;
	}

	@Override
	public int add(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}
}
*/