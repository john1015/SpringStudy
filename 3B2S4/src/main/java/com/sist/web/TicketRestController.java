package com.sist.web;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.ReserveService;
import com.sist.service.TicketService;
import com.sist.vo.GameReserveVO;
import com.sist.vo.GameVO;
import com.sist.vo.HotelVO;
import com.sist.vo.ReserveVO;


@RestController
public class TicketRestController {
	@Autowired
	private TicketService tService;
	@Autowired
	private ReserveService rService;
	
	@GetMapping(value="ticket/ticketchoice_vue.do",produces="text/plain;charset=UTF-8")
	public String ticket_list() throws Exception {
		List<GameVO> glist = tService.gameListData();
		Map map = new HashMap();
		map.put("glist", glist);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	@GetMapping(value="ticket/ticket_main_vue.do",produces = "text/plain;charset=UTF-8")
	  public String reserve_ok(GameReserveVO vo,HttpSession session) throws Exception 
	  {
			//GameVO vo = tService.(hno);
			Map map = new HashMap();
			map.put("hotel_vo", vo);
			
			List<GameReserveVO> nolist = rService.gameNoSeatList(map);
			
			map = new HashMap();
			map.put("nolist", nolist);
			
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(map);
			
			return json;
	  }
   
}