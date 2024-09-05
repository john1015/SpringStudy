package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface FoodMapper {
	@Select("SELECT fno,name,address,poster,num "
			+ "  FROM (SELECT fno,name,address,poster,rownum as num "
			+ "  			  FROM (SELECT fno,name,address,poster "
			+ " 						 FROM project_food_house ORDER BY fno)) "
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);
	@Select("SELECT COUNT(*) FROM project_food_house ")
	public int foodRowCount();
	@Update("UPDATE project_food_house SET "
			+ "   hit = hit+1 "
			+ "	WHERE fno=#{fno}")
	public void foodHitIncrement(int fno);
	@Select("SELECT * FROM project_food_house WHERE fno = #{fno}")
	public FoodVO foodDetailData(int fno);
	
	@Select("SELECT fno,name,poster FROM project_food_house WHERE fno=#{fno}")
	public FoodVO foodCookieInfoData(int fno);
	
}
