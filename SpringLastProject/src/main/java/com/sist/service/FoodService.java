package com.sist.service;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface FoodService {
	public List<FoodVO> foodHitTop5();
	public List<FoodVO> foodListData(int start , int end);
	public int foodTotalPage();
	public FoodVO foodDetailData(int fno);
	public FoodVO foodInfoData(int fno);
}
