package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sist.service.*;
import com.sist.vo.*;
import java.util.*;

@Controller
public class HotelController {
	@Autowired
	private HotelService hService;
	
	@GetMapping("hotel/list.do")
	public String hotel_list()
	{
		return "hotel/list";
	}
}
