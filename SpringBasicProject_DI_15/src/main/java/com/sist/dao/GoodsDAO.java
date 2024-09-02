package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository("dao")
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper; // 스프링에서 구현한 클래스 주소를 받아온다
	/*
		public List<GoodsVO> goodsListData(Map map);
		public int goodsTotalPage(Map map);
		public GoodsVO goodsDetailData(Map map);
	 */
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map); // ssf.openSession() session.close()
		// session.selectList()
	}
	public int goodsTotalPage(Map map) {
		return mapper.goodsTotalPage(map);
	}
	public GoodsVO goodsDetailData(Map map) {
		return mapper.goodsDetailData(map);
	}
}
