package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.RecipeDAO;
import com.sist.vo.ChefVO;
import com.sist.vo.RecipeDetailVO;
import com.sist.vo.RecipeVO;

import java.util.*;

@Controller
@RequestMapping("recipe/")
public class RecipeController {
	@Autowired
	private RecipeDAO rDao;
	/*
	 * 1. int
	 * 2. String
	 * 3. String[]
	 * 4. ~VO
	 * ========
	 * 1. Model
	 * 2. HttpSession
	 * 3. HttpServletRequest , HttpServletResponse
	 * =>Cookie / 업로드
	 * 4. RedirectAttributes : sendRedirect시
	 * 
	 */
	@GetMapping("detail.do")
	public String recipe_detail(int no , Model model) {
		
		// DB연동
		RecipeDetailVO vo = rDao.recipeDetailData(no);
		String data = vo.getData();
		data = data.replace("구매", "");
		vo.setData(data.trim());
		
		List<String> mList = new ArrayList<String>();
		List<String> iList = new ArrayList<String>();
		
		String[] foodMake = vo.getFoodmake().split("\n");
		/*
		 *     1 ....... ^
		 *     2
		 *     3
		 *     4 ........
		 */
		for (String fm : foodMake) {
			StringTokenizer st = new StringTokenizer(fm,"^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());
		}
		
		// JSP => 출력할 데이터 전송
		model.addAttribute("vo", vo);
		model.addAttribute("mList", mList);
		model.addAttribute("iList", iList);
		
		return "recipe/detail";
	}
	// 실제 검색 : POST / GET  둘다 사용
	@RequestMapping("find.do")
	public String recipe_find(String fd , String page , Model model) {
		if(fd==null) fd="비빔밥";
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("fd", fd);
		
		// DB연동 
		List<RecipeVO> list = rDao.recipeFindData(map);
		int totalpage = rDao.recipefindTotalPage(map);
		
		final int BLOCK = 10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) endPage=totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("fd", fd);
		return "recipe/find";
	}
	@GetMapping("chef_list.do")
	public String recipe_chef_list(String page ,Model model) {
		
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		// DB연동 
		List<ChefVO> list = rDao.chefListData(map);
		int totalpage = rDao.chefTotalPage();
		
		final int BLOCK = 10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) endPage=totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalpage", totalpage);
		
		return "recipe/chef_list";
	}
	@GetMapping("chef_make.do")
	public String recipe_chef_make(String page , String chef , Model model) {
		
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("chef", chef);
		
		// DB연동 
		List<RecipeVO> list = rDao.chefMakeData(map);
		int totalpage = rDao.chefMakeTotalData(map);
		
		final int BLOCK = 10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) endPage=totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("chef", chef);
		
		return "recipe/chef_make";
	}
}