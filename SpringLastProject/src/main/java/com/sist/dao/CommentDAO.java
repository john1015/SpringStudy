package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class CommentDAO {
	@Autowired
	private CommentMapper mapper;
	
	public List<CommentVO> commentListData(Map map){
		return mapper.commentListData(map);
	}
	public int commentTotalpage(Map map) {
		return mapper.commentTotalpage(map);
	}
}
