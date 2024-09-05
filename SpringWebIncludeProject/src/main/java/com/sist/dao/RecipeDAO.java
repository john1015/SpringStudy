package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
	public List<RecipeVO> recipeListData(Map map){
		return mapper.recipeListData(map);
	}
	public int recipeRowCount() {
		return mapper.recipeRowCount();
	}
	public RecipeDetailVO recipeDetailData(int no) {
		mapper.recipeHitIncrement(no);
		return mapper.recipeDetailData(no);
	}
    public List<ChefVO> chefListData(Map map){
    	return mapper.chefListData(map);
    }
    public int chefTotalPage() {
    	return mapper.chefTotalPage();
    }
    public List<RecipeVO> chefMakeRecipeData(Map map){
    	return mapper.chefMakeRecipeData(map);
    }
	public int chefMakeRecipeTotalPage(String chef) {
		return mapper.chefMakeRecipeTotalPage(chef);
	}
	/*
	 * 쿠키에 출력할 데이터
	 */
	public RecipeVO recipeCookieInfoData(int no) {
		return mapper.recipeCookieInfoData(no);
	}
}
