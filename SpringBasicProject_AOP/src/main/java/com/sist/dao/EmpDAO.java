package com.sist.dao;

import java.util.*;
import java.sql.*;

public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private MyDataSoruce ds;
	public EmpDAO(MyDataSoruce ds) {
		this.ds=ds;
		try {
			Class.forName(ds.getDriver());
		} catch (Exception ex) {}
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(ds.getUrl(),ds.getUsername(),ds.getPassword());
		} catch (Exception ex) {}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception ex) {}
	}
	// 기능설정
	// around => 시간 (수행시간) => 트랜잭션
	//					========= log파일
	public List<EmpVO> empListData(){
		List<EmpVO> list = new ArrayList<EmpVO>();
		try {
			String sql="select empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD'),sal "
					+ "     from emp order by empno ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setSal(rs.getInt(5));
				list.add(vo); // selectList
			}
		} catch (Exception ex) {
		
		} finally {
			
		}
		return list;
	}
	public EmpVO empDetailData(int empno) {
		EmpVO vo = new EmpVO();
		try {
			String sql = "select empno,ename,job,hiredate,sal "
					+ "		from emp"
					+ "		where empno="+empno;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setEmpno(rs.getInt(1));
			vo.setEname(rs.getString(2));
			vo.setJob(rs.getString(3));
			vo.setHiredate(rs.getDate(4));
			vo.setSal(rs.getInt(5));
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vo;
	}
}
