package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.*;
import com.sist.vo.*;

//=> 자바스크립트나 다른 언어를 연결할때 사용 => 파일제어하지 않는다 => 문자열 , JSON , JavaScript를 전송이 가능
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@PostMapping(value="board/update_ok.do",produces = "text/html;charset=UTF-8")
	public String board_update_ok(BoardVO vo) {
				boolean bcheck=dao.boardUpdate(vo);
				String js="";
				if(bcheck==true) {
					js="<script> "
						+ "location.href=\"detail.do?no="+vo.getNo()+"\";"
						+ "</script>";
				}
				else {
					js = "<script> "
						+ 		"alert(\"비밀번호가 틀립니다!!!\"); "
						+ 		"history.back(); "
						+ "</script>";
				}
				return js;
	}
	@PostMapping(value="board/delete_ok.do",produces = "text/html;charset=UTF-8")
	public String board_delete_ok(int no , String pwd) {
		String js="";
		boolean bCheck=dao.boardDelete(no, pwd);
		if(bCheck==false) {
			js = "<script> "
					+ 		"alert(\"비밀번호가 틀립니다!!!\"); "
					+ 		"history.back(); "
					+ "</script>";
		} else {
			js="<script> "
					+ "location.href=\"list.do\";"
					+ "</script>";
		}
		
		return js;
	}
}
