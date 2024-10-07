package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {
	
	@GetMapping("hotel/list.do")
	public String hotel_list()
	{
		return "hotel/list";
	}
}
