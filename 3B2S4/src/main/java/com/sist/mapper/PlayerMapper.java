package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface PlayerMapper {

	@Select("SELECT * FROM batter "
			+ "WHERE bno=#{bno}")
	public BatterVO batterDetailData(int bno);
	
	@Select("SELECT * FROM pitcher "
			+ "WHERE pno=#{pno}")
	public PitcherVO pitcherDetailData(int pno);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM batter "
			+ "WHERE name LIKE '%'||#{fd}||'%'")
	public int batterTotalPage(String fd);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM pitcher "
			+ "WHERE name LIKE '%'||#{fd}||'%'")
	public int pitcherTotalPage(String fd);
	
	@Select("SELECT bno,age,game,h1,homerun,rbi,war,name,team,position,logo,image,avg,num "
			+ "FROM (SELECT bno,age,game,h1,homerun,rbi,war,name,team,position,logo,image,avg,rownum as num "
			+ "FROM (SELECT bno,age,game,h1,homerun,rbi,war,name,team,position,logo,image,avg "
			+ "FROM batter "
			+ "WHERE name LIKE '%'||#{fd}||'%' "
			+ "ORDER BY bno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BatterVO> batterListData(Map map);
	
	@Select("SELECT pno,age,game,win,lose,save,hold,era,war,name,team,logo,image,num "
			+ "FROM (SELECT pno,age,game,win,lose,save,hold,era,war,name,team,logo,image,rownum as num "
			+ "FROM (SELECT pno,age,game,win,lose,save,hold,era,war,name,team,logo,image "
			+ "FROM pitcher "
			+ "WHERE name LIKE '%'||#{fd}||'%' "
			+ "ORDER BY pno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<PitcherVO> pitcherListData(Map map);
	
	@Select("SELECT bno,avg,war FROM batter")
	public List<BatterVO> batterChartData();

	@Select("SELECT pno,era,war FROM pitcher")
	public List<PitcherVO> pitcherChartData();
}
