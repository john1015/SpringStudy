package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
/*
 * 1. ~DAO : 데이터베이스 연결
 * 2. ~Service : DAO여러개를 한개로 통합 , 결합성이 낮은 프로그램
 * 3. ~Manager : Open API
 * 4. ~Controller : Model
 * 5. ~RestController : 자바스크립트 , 코틀린 , 플러터
 * 		========== JSON
 * 6. ~ControllerAdvice : 통합 예외처리
 *  ===================== 스프링에서 관리
 *  	~VO : 사용자 데이터형
 *  	~Mapper : 관리할 수 없다 (인터페이스)
 *  ===================== 개발자 관리
 */

@Repository // DAO메모리 할당 요청 => 관리 => 싱글턴으로 생성
public class BoardDAO {
	// 필요한 객체를 스프링으로 부터 얻어온다 => 주입 (DI) => 자동 주입 : @Autowired
	@Autowired
	private BoardMapper mapper;// 구현된 클래스의 주소값을 설정
	// 메소드안에서는 어노테이션을 사용할 수 없다
	/*
	 *   ===> 어노테이션 아래 , 어노테이션 옆에 있는 것을 제어
	 *   1. class위 => TYPE (클래스 구분자)
	 *   2. 메소드 위 => METHOD (메소드 구분자)
	 *   3. 멤버변수 위 => FIELD (멤버변수 구분자)
	 *   4. 매개변수 옆 => PARAMETER (매개변수 구분자)
	 *   5. 생성자 위 => CONTSRUCTOR (생성자 구분자)
	 */
	public List<BoardVO> boardListData( int start ,int end){
		
		return mapper.boardListData(start, end);
	}
	
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	public int boardRowCount() {
		return mapper.boardRowCount();
	}
	
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	public boolean boardUpdate(BoardVO vo) {
		boolean bcheck=false;
		String db_pwd=mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bcheck=true;
			mapper.boardUpdate(vo);
		}
		return bcheck;
	}
	public boolean boardDelete(int no,String pwd) {
		boolean bcheck=false;
		String db_pwd=mapper.boardGetPassword(no);
		if(pwd.equals(db_pwd)) {
			bcheck=true;
			mapper.boardDelete(no);
		}
		return bcheck;
	}
}
