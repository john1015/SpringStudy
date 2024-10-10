package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.vo.*;
import com.sist.mapper.*;
// 형식동일 => 마이페이지 , 관리자페이지에서 여러개를 동시에 삭제
@Repository
public class FreeBoardDAO {
	@Autowired
	private FreeBoardMapper mapper;
	
	public List<FreeBoardVO> freeboardListData(int start , int end){
		return mapper.freeboardListData(start, end);
	}
	public int freeboardRowCount() {
		return mapper.freeboardRowCount();
	}
	public void freeboardInsert(FreeBoardVO vo) {
		mapper.freeboardInsert(vo);
	}
	
	public FreeBoardVO freeboardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.freeboardDetailData(no);
	}
	public void freeboardDelete(int no	) {
		mapper.freeboardDelete(no);
	}
}
