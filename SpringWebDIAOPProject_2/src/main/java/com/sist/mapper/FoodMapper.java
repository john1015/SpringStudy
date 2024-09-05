package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface FoodMapper {
	@Select("select fno,poster,name, num "
			+ "  from (select fno,poster,name,rownum as num "
			+ "           from (select fno,poster,name "
			+ "                    from project_food_house order by fno asc )) "
			+ "  where num between #{start} and #{end}")
	public List<FoodVO> foodListData(@Param("start") int start , @Param("end") int end);
	
	@Select("select ceil(count(*)/20.0) from project_food_house")
	public int foodTotalPage();
}
