package com.sist.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.HotelService;
import com.sist.service.ReserveService;
import com.sist.vo.ReserveVO;

@RestController
public class MyPageRestController {
	@Autowired 
	private ReserveService rService;
	@Autowired
	private HotelService hService;
	
	@GetMapping(value="mypage/reserve_info_vue.do",produces="text/plain;charset=UTF-8")
	public String reserve_info(int hno , int rno) throws Exception{
		ReserveVO vo = rService.reserveInfoData(rno);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(vo);
		return json;
	}
	@GetMapping(value="mypage/mypage_reserve_vue.do",produces = "text/plain;charset=UTF-8")
	  public String mypage_reserve(HttpSession session) throws Exception
	  {
		  String id=(String)session.getAttribute("userId");
		  System.out.println(id);
		  List<ReserveVO> list=rService.reserveMyPageListData(id);
		  ObjectMapper mapper=new ObjectMapper();
		  String json=mapper.writeValueAsString(list);
		  return json;
	  }
	
}
