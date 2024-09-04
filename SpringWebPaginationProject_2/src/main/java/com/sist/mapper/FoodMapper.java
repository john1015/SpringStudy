package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface FoodMapper {
	@Select("SELECT fno,name,poster,theme,phone,type,address,score,num "
			+ "  FROM(SELECT fno,name,poster,theme,phone,type,address,score,rownum as num "
			+ "          FROM(SELECT /*+ Index_ASC(project_food_house project_food_house_pk)*/ fno,name,poster,theme,phone,type,address,score "
			+ "                  FROM project_food_house)) "
			+ "  WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);
	@Select("SELECT count(*) from project_food_house")
	public int foodRowCount();
}
