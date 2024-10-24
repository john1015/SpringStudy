package com.sist.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.Date;
import java.text.*;
import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
import java.sql.*;


@RestController
public class StadiumRestController {
	@Autowired
    private StadiumService sService;
	
	@GetMapping(value="stadium/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String stadium_detail(int no, String fd,String hd) throws Exception
	   {
		
		   StadiumVO vo=sService.stadiumDetailData(no);
		  
		   Map map=new HashMap();
		   List<FoodVO> fList=sService.stadiumFoodListData(fd);
		   List<HotelVO> hList=sService.stadiumHotelListData(hd);
		   
		   map.put("fList", fList);
		   map.put("hList", hList);
		   map.put("fd", fd);
		   map.put("hd", hd);
		   
		   ObjectMapper mapper=new ObjectMapper();
		   String json=mapper.writeValueAsString(map);
		   return json;
	   }
}
