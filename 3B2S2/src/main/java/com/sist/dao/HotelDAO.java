package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.HotelMapper;
import com.sist.vo.HotelVO;

@Repository
public class HotelDAO {
	@Autowired
	private HotelMapper mapper;
	public List<HotelVO> hotelListData(int start,int end){
		return mapper.hotelListData(start,end);
	}
	public int hotelTotalPage() {
		return mapper.hotelTotalPage();
	}
	
}