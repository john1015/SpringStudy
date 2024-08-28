package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository("dao")
public class FoodDAO {
	@Autowired
    private FoodMapper mapper;
	/*
	 *   @Select("SELECT fno,name,score,address,phone,num "
		 +"FROM (SELECT fno,name,score,address,phone,rownum as num "
		 +"FROM (SELECT fno,name,score,address,phone "
		 +"FROM project_food_house ORDER BY fno ASC)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
	 */
	public List<FoodVO> foodListData(int start,int end)
	{
		return mapper.foodListData(start, end);
	}
	/*
	 *   @Select("SELECT CEIL(COUNT(*)/10.0) FROM project_food_house")
  
	 */
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
	
	/*
	 *   @Select("SELECT fno,name,score,address,phone,theme,type,time,parking "
		 +"FROM project_food_house "
		 +"WHERE fno=#{fno}")
  
	 */
	public FoodVO foodDetailData(int fno)
	{
		return mapper.foodDetailData(fno);
	}
}