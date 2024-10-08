package com.sist.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.HotelService;
import com.sist.vo.*;

@RestController
public class HotelRestController {
	@Autowired
	private HotelService hService;
	@GetMapping(value="hotel/list_vue.do",produces="text/plain;charset=UTF-8")
	public String hotel_list(int page) throws Exception {
		int rowSize=6;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		List<HotelVO> list=hService.hotelListData(start, end);
		int totalpage=hService.hotelTotalPage();
		
		System.out.println("h");
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		

		/*
		 * for (HotelVO vo : list) { // images 필드를 쉼표로 구분하여 배열로 나누고, 리스트로 변환하여 설정 if
		 * (vo.getImages() != null && !vo.getImages().isEmpty()) { String[] imagesArray
		 * = vo.getImages().split(","); // 쉼표로 구분된 이미지 URL 분리
		 * vo.setImagesList(Arrays.asList(imagesArray)); // 분리한 결과를 새로운 리스트 필드로 설정 } }
		 */
		
		// 데이터를 모아서 => JSON으로 만들고 => VueJS로 전송 (React/React-Query/Redux/ThymeLeaf)
		// JSON : Map 형식 => {키:값}
		Map map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
}
