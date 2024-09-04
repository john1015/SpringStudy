package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@RequestMapping("food/list.do")
	public String food_list(String page , Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		Map map = new HashedMap();
		map.put("start", (curpage*20)-19);
		map.put("end", (curpage*20));
		List<FoodVO> list = dao.foodListData(map);
		int count = dao.foodRowCount();
		int totalpage=(int)(Math.ceil(count/20.0));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) endPage=totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		
		return "food/list";
	}
	@GetMapping("food/detail.do")
	public String recipe_detail(int no , Model model) {
		
		// 데이터베이스 연결 => 상세보기
		// 상세보기에서 출력할 데이터 전송 => Model
		return "food/detail";
	}
}
