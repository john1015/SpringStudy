package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{
	@Autowired FreeBoardDAO fDao;

	@Override
	public List<FreeBoardVO> freeboardListData(int start, int end) {
		// TODO Auto-generated method stub
		return fDao.freeboardListData(start, end);
	}

	@Override
	public int freeboardRowCount() {
		// TODO Auto-generated method stub
		return fDao.freeboardRowCount();
	}

	@Override
	public void freeboardInsert(FreeBoardVO vo) {
		// TODO Auto-generated method stub
		fDao.freeboardInsert(vo);
	}
	
	
}
