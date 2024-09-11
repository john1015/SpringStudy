package com.sist.dao;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.BoardMapper;
import com.sist.vo.BoardVO;

// 오라클 연동
@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
}
