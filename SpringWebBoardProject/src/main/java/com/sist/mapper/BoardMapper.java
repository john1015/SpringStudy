package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface BoardMapper {
	// 목록 = 페이지 나누기
	@Select("select no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
			+ "  from(select no,subject,name,regdate,hit,rownum as num "
			+ "          from (select no,subject,name,regdate,hit "
			+ "                   from spring_board order by no desc)) "
			+ "   where num between #{start} and #{end}")
	public List<BoardVO> boardListData(@Param("start") int start , @Param("end") int end);
	/*
	 * <select id="boardListData" resultType="" parameterType="">
	 * 						메소드				 리턴형				매개변수
	 * 		select ~
	 * </select>
	 */
	// boardList(1,10) => 구분자 전송 => @Param => 매개변수를 여러개 사용이 가능
	// Map => 1개 이용시에는 , VO 
	@Select("select count(*) from spring_board")
	public int boardRowCount();
	// 추가
	@Insert("insert into spring_board(no,name,subject,content,pwd) values(sb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
	public void boardInsert(BoardVO vo);
	// 상세보기
	@Update("update spring_board set "
			+ "   hit=hit+1"
			+ "   where no=#{no}")
	public void hitIncrement(int no);
	
	@Select("select no ,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday , hit "
			+ "  from spring_board "
			+ "  where no=#{no}")
	public BoardVO boardDetailData(int no);
	// 수정
	@Select("select pwd from spring_board where no=#{no}")
	public String boardGetPassword(int no); // 비밀번호 가지고 오기
	
	@Update("update spring_board set "
			+ "   name=#{name},subject=#{subject},content=#{content} "
			+ "   where no=#{no}")
	public void boardUpdate(BoardVO vo);
	// 삭제
	@Delete("delete from spring_board "
			+ "  where no=#{no}")
	public boolean boardDelete(int no);

	// 검색
}
