package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface RecipeMapper {
	@Select("select no,poster , title,chef ,num "
			+ "  from (select no,poster , title,chef ,rownum as num "
			+ " 			 from (select /*+ INDEX_ASC(recipe recipe_no_pk)*/ no,poster , title,chef "
			+ "                              from recipe where no in(select no from recipe intersect select no from recipedetail))) "
			+ "  where num between #{start} and #{end}")
	public List<RecipeVO> recipeListData(Map map);
	
	@Select("select count(*) from recipe where no in(select no from recipe intersect select no from recipedetail)")
	public int recipeRowCount();
}
