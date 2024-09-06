package com.sist.web;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.service.*;
import com.sist.vo.*;

@Controller
public class RecipeController {
	@Autowired
	private RecipeService rService;
	/*
	 * @Autowired public RecipeController(RecipeService rService) {
	 * this.rService=rService; }
	 */
	@GetMapping("recipe/detail_before.do")
	public String recipe_detail_before(int no,HttpServletResponse response , RedirectAttributes ra) {
		
		// Cookie => 저장 => 브라우저 전송
		// 전송 => Model : forward
		// 전송 => RedirectAttributes : sedRedirect
		Cookie cookie = new Cookie("recipe_"+no,String.valueOf(no));
		// 쿠키는 저장위치 : 브라우저 , 문자열만 저장이 가능
		
		cookie.setMaxAge(60*60*24);
		cookie.setPath("/");
		// 브라우저로 전송
		response.addCookie(cookie);
		ra.addAttribute("no", no);
		//ra.addFlashAttribute("no",no);
		return "redirect:../recipe/detail.do";
	}
	
	@GetMapping("recipe/detail.do")
	public String recipe_detail(int no , Model model) {
		
		// 데이터베이스 연결 => 데이터를 읽기
		RecipeDetailVO vo = rService.recipeDetailData(no);
		String data=vo.getData();
		data = data.replace("구매", "");
		vo.setData(data.trim());
		// detail.jsp로 출력할 데이터 보내준다
		model.addAttribute("vo", vo);
		
		List<String> mList = new ArrayList<String>();
		List<String> iList = new ArrayList<String>();
		
		String[] make=vo.getFoodmake().split("\n");
		for (String m : make) {
			StringTokenizer st = new StringTokenizer(m,"^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());
		}
		model.addAttribute("mList", mList); // 레시피 방식
		model.addAttribute("iList", iList); // 이미지
		// Model은 jsp로 전송시에 사용 : forward
		model.addAttribute("main_jsp", "../recipe/detail.jsp");
		
		return "main/main";
	}
	@GetMapping("recipe/chef_list.do")
	// 반복코딩 =>  메소드 처리 , AOP 이용
	//									====== 자동 호출이 가능
	/*
	 * 				호출 위치 => 지정(JoinPoint)
	 * 				public void display() {
	 * 					== 호출 : before ==
	 *                 try{
	 *                     == 핵심 모듈
	 *                 } catch(Exception ex){
	 *                 		== after-throwing
	 *                 } finally{
	 *                 		== after
	 *                 }
	 *                 return === after-returning
	 * 				}
	 */
	//					==== 반복 제거 => 반드시 호출해서 사용한다
	public String recipe_chef_list(String page , Model model) {
		
		if(page==null) page="1";
		int curpage = Integer.parseInt(page);
		Map map = new HashedMap();
		int rowSize=50;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		// 데이터베이스 목록
		List<ChefVO> list = rService.chefListData(map);
		int totalpage = rService.chefTotalPage();
		// 총페이지 읽기
		// 데이터 전송
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("main_jsp", "../recipe/chef_list.jsp");
		return "main/main";
	}
	@GetMapping("recipe/chef_detail.do")
	public String recipe_chef_detail(String page , String chef , Model model) {
		// chef가 만든 recipe목록
		if(page==null) page="1";
		int curpage = Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end = (rowSize*curpage);
		// List
		Map map = new HashedMap();
		map.put("chef", chef);
		map.put("start", start);
		map.put("end", end);
		List<RecipeVO> list = rService.chefMakeRecipeData(map);
		// 총페이지
		int totalpage = rService.chefMakeRecipeTotalPage(chef);
		// 전송
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("chef", chef);
		model.addAttribute("main_jsp", "../recipe/chef_detail.jsp");
		return "main/main";
	}
	@GetMapping("recipe/cookie_all.do")
	public String recipe_cookie_all(HttpServletRequest request , Model model) {
		
		Cookie[] cookies=request.getCookies();
		List<RecipeVO> cList = new ArrayList<RecipeVO>();
		// 쿠키담는 List
		if(cookies!=null) {
			//최신부터 담는다
			for (int i = cookies.length-1; i >=0; i--) {
				if(cookies[i].getName().startsWith("recipe_")) {
					String no = cookies[i].getValue();
					RecipeVO vo = rService.recipeCookieInfoData(Integer.parseInt(no));
					cList.add(vo);
				}
			}
		}
		model.addAttribute("cList", cList);
		model.addAttribute("size", cList.size());
		model.addAttribute("main_jsp", "../recipe/cookie_all.jsp");
		return "main/main";
	}
	@GetMapping("recipe/cookie_delete.do")
	public String recipe_cookie_delete(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().startsWith("recipe_")) {
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}
		return "redirect:../main/main.do";
	}
	@RequestMapping("recipe/find.do") // get , post 동시 처리
	public String recipe_find(String fd , String page , Model model) {
		
		if(fd==null) fd="감자";
		if (page==null) page="1";
		int curpage = Integer.parseInt(page);
		Map map = new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", (curpage*20));
		map.put("fd", fd);
		// DAO 연동
		List<RecipeVO> list = rService.recipeFindData(map);
		int totalpage = rService.recipeFindTotalPage(map);
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		model.addAttribute("fd", fd);
		model.addAttribute("main_jsp", "../recipe/find.jsp");
		return "main/main";
	}
}
