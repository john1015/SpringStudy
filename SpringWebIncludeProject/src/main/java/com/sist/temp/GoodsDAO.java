package com.sist.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps ;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public GoodsDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {}
	}
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception ex) {}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception ex) {}
	}
	
	public void insert(StoreVO svo , InputVO ivo) {
		try {
			getConnection();
			conn.setAutoCommit(false); // Around
			
			String sql = "INSERT INTO input VALUES(?,?,?) ";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ivo.getNo());
			ps.setInt(2, ivo.getGno());
			ps.setInt(3, ivo.getPrice());
			ps.executeUpdate();
			
			sql = "INSERT INTO store VALUES(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, svo.getNo());
			ps.setInt(2, svo.getGno());
			ps.setInt(3, svo.getPrice());
			ps.executeUpdate();
			
			conn.commit(); // Around
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback(); // 전체 sql문장취소 after-throwing
			} catch (Exception e) {}
		} finally {
			try {
				conn.setAutoCommit(true); // 원상 복귀 after
			} catch (Exception ex) {}
			disConnection();
		}
	}
}
