package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class RecipeServiceImpl implements RecipeService{
	@Autowired
	private RecipeDAO rDao;
	@Autowired
	private ChefDAO cDao;
	
	@Override
	public RecipeVO recipeMaxHitData() {
		return rDao.recipeMaxHitData();
	}
	@Override
	public List<RecipeVO> recipeHitTop8() {
		return rDao.recipeHitTop8();
	}
	@Override
	public ChefVO chefToday() {
		return cDao.chefToday();
	}
}
