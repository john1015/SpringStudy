package com.sist.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sist.dao.CommentDAO;
import com.sist.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO cDao;
	
	@Override
	public List<CommentVO> commentListData(Map map) {
		// TODO Auto-generated method stub
		return cDao.commentListData(map);
	}

	@Override
	public int commentTotalpage(Map map) {
		// TODO Auto-generated method stub
		return cDao.commentTotalpage(map);
	}
	
}
