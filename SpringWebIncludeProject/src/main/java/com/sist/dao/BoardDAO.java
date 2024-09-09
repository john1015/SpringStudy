package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
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
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<ReplyBoardVO> boardListData(int start , int end){
		return mapper.boardListData(start, end);
	}
	public int boardRowCount() {
		return mapper.boardRowCount();
	}
	public void boardInsert(ReplyBoardVO vo) {
		mapper.boardInsert(vo);
	}
	public ReplyBoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	public ReplyBoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	public String boardUpdate(ReplyBoardVO vo) {
		String result="no";
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			result="yes";
			mapper.boardUpdate(vo);
		}
		return result;
	}
	
	/*
	 * 											no    group_id   group_step   group_tab   root   depth
	 *       AAAAAA						1         1                0				   	  0			   0        1
	 *          => BBBBBB				4         1      			 1 					  1			   1        1
	 *             => CCCCCC    		5 		   1                2         			  2           4        0
	 *       DDDDDD            			2   	   2                0         			  0           0        0
	 *       EEEEEE                       3         3      			 0          		  0           0        0 
	 */
	// SQL 일괄처리
	// 금융권 => 카드결제 , 포인트 등록
	// => SQL 문장의 핵심
	// DML (INSERT , UPDATE , DELETE ) 여러개가 동시에 수행
	// AOP기반으로 만들어져 있다
	// Transaction / Security => AOP가 포함
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void boardReplyInsert(int pno , ReplyBoardVO vo) {
		
//		try {
//			conn.setAutoCommit(false); // Around
			
			ReplyBoardVO pvo = mapper.boardGroupData(pno);
			mapper.boardGroupStepIncrement(pvo);
			vo.setGroup_id(pvo.getGroup_id());
			vo.setGroup_step(pvo.getGroup_step()+1);
			vo.setGroup_tab(pvo.getGroup_tab()+1);
			vo.setRoot(pno);
			mapper.boardReplyInsert(vo);
			mapper.boardDepthIncrement(pno);
			
//			conn.commit(); // Around
//		} catch (Exception ex) {
//			conn.rollback(); // after-throwing
//		} finally {
//			conn.setAutoCommit(); // after
//		}
		
	}
	
	/*
	 * public String boardGetPassword(int no);
	 * public ReplyBoardVO boardDeleteInfoData(int no);
		public void boardDelete(int no);
		public void boardSubjectUpdate(int no);
		public void boardDepthDecrement(int no );
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class) // 괄호는 생략 가능
	public String boardDelete(int no,String pwd) {
		String result = "no";
		// 1. 비밀번호 검색
		String db_pwd = mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			result="yes";
			
			// 2. root , depth를 읽어온다
			ReplyBoardVO vo = mapper.boardDeleteInfoData(no);
			if(vo.getDepth()==0) { // 답변이 없다면 => 삭제
				mapper.boardDelete(no);
			} else { // 답변이 있는 경우
				mapper.boardSubjectUpdate(no);
			}
			//depth감소
			mapper.boardDepthDecrement(no);
			
		}
		return result;
	}
	

}
