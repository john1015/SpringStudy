package com.sist.service;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface CommentService {
	
	public List<CommentVO> commentListData(Map map);
	public int commentTotalpage(Map map);
	public void commentInsert(CommentVO vo);
}
