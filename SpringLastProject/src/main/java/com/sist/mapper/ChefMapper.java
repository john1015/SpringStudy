package com.sist.mapper;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.ChefVO;

public interface ChefMapper {
	@Select("SELECT chef,poster "
			+ "  FROM chef "
			+ "  WHERE chef=(SELECT chef FROM recipe WHERE hit = (SELECT MAX(hit) FROM recipe))")
	public ChefVO chefToday();
}
