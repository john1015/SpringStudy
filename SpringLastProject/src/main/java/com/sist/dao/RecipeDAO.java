package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.RecipeMapper;
import com.sist.vo.RecipeVO;

@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
	public RecipeVO recipeMaxHitData() {
		return mapper.recipeMaxHitData();
	}
	
	public List<RecipeVO> recipeHitTop8(){
		return mapper.recipeHitTop8();
	}
}
