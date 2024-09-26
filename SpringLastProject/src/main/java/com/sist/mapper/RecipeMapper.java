package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface RecipeMapper {
   // 1. 오라클에서 실행여부 확인 
   @Select("SELECT no,title,poster,chef,hit,"
		 +"(SELECT content FROM recipedetail WHERE no=recipe.no) as content "
		 +"FROM recipe "
		 +"WHERE hit=(SELECT MAX(hit) FROM recipe)")
   public RecipeVO recipeMaxHitData();
   
   @Select("SELECT no,title,poster,chef,hit,content,rownum "
		  +"FROM (select r.no,r.title,r.poster,r.chef,r.hit,rd.content "
		  +"from recipe r , recipedetail rd "
		  +"where r.no=rd.no "
		  + " order by hit desc) "
		  + "WHERE rownum<=8 ")
   public List<RecipeVO> recipeHitTop8();
}