package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
		return mapper.commentTotalPage(map);
	}
	public void commentInsert(CommentVO vo) {
		mapper.commentInsert(vo);
	}
	
	  // 일괄 처리
	  @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	  public void commentReplyReplyInsert(int cno ,CommentVO vo) {
		  CommentVO pvo=mapper.commentParentInfoData(cno);
		  vo.setGroup_id(pvo.getGroup_id());
		  vo.setGroup_step(pvo.getGroup_step()+1);
		  vo.setGroup_tab(pvo.getGroup_tab()+1);
		  
		  mapper.commentGroupStepIncrement(pvo);
		  mapper.commentReplyReplyInsert(vo);
		  mapper.commentDepthIncrement(cno);
	  }
	  
}
