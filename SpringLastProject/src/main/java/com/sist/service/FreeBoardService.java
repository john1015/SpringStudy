package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.sist.vo.*;

public interface FreeBoardService {
	
	public List<FreeBoardVO> freeboardListData(int start , int end);
	public int freeboardRowCount();
	public void freeboardInsert(FreeBoardVO vo);
	public FreeBoardVO freeboardDetailData(int no);
	public void freeboardDelete(int no	);
}
