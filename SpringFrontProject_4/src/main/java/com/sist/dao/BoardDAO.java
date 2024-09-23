package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.BoardMapper;
import com.sist.vo.BoardVO;

@Repository
public class BoardDAO {
	// 스프링으로 부터 구현된 Mapper 의 주소를 대입 => 요청
	@Autowired // 주소값 자동주입 => @Autowired => 객체에만 적용이 된다
	// 스프링 => 클래스의 생명주기 관리 => 주로 객체 주소 주입
	// @Autowired => 1)멤버 변수 , 2) 생존자 3) setXxx()
	// 어노테이션은 밑에있는 변수 . 매ㅔ소드 . 생성자 적용 , 옆에있는 내용을 제어
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData( int start , int end){
		return mapper.boardListData(start, end);
	}
	
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	public String boardDelete(int no,String pwd) {
		String result = "no";
		String db_pwd = mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			result="yes";
			mapper.boardDelete(no);
		}
		return result;
	}
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	public String boardUpdate(BoardVO vo) {
		String result="no";
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())){
			result="yes";
			mapper.boardUpdate(vo);
		}
		return result;
	}
}
