package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.HotelVO;

public interface HotelMapper {
	@Select("SELECT hno,name,price,address,score,poster,images,num "
			+ "  FROM (SELECT hno,name,price,address,score,poster,images,rownum as num"
			+ "			  FROM (SELECT hno,name,price,address,score,poster,images "
			+ "  						 FROM hotel "
			+ "						 ORDER BY hno asc)) "
			+ "  WHERE num BETWEEN #{start} AND #{end}")
	public List<HotelVO> hotelListData(@Param("start") int start,@Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/6) FROM hotel ")
	public int hotelTotalPage();
}
