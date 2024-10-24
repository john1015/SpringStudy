package com.sist.web;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HighlightController {
	@GetMapping("highlight/list.do")
	public String highlight_list() {
		return "highlight/list";
	}
}
