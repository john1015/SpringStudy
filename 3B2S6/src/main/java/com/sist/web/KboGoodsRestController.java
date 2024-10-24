package com.sist.web;


import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
import com.sist.vo.*;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;

import java.util.*;

import javax.servlet.http.HttpSession;

@RestController
public class KboGoodsRestController {
	@Autowired
	private KboGoodsService kgService;
	
	@GetMapping(value="kboGoods/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String kboGoods_list(int page) throws Exception{
	int rowSize=15;
	int start=(rowSize*page)-(rowSize-1);
	int end=rowSize*page;
	
	List<KboGoodsVO> list=kgService.kboGoodsListData(start, end);
	int totalpage=kgService.kboGoodsTotalPage();
	
	final int BLOCK=5;
	int startPage=((page-1)/BLOCK*BLOCK)+1;
	int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
	
	if(endPage>totalpage) {
		endPage=totalpage;
	}
		   
	Map map=new HashMap();
	map.put("list", list);
	map.put("curpage", page);
	map.put("totalpage", totalpage);
	map.put("startPage", startPage);
	map.put("endPage", endPage);
	
	// JSON으로 변환후 전송 
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(map);
	return json;
	}
	
	@GetMapping(value="kboGoods/find_vue.do",produces = "text/plain;charset=UTF-8")
	public String kboGoods_find(int page, String fd) throws Exception{
	int rowSize=15;
	int start=(rowSize*page)-(rowSize-1);
	int end=rowSize*page;
	Map map=new HashMap();
	map.put("start",start);
	map.put("end", end);
	map.put("fd", fd);
	
	List<KboGoodsVO> list=kgService.kboGoodsFindListData(map);
	int totalpage=kgService.kboGoodsFindTotalPage(map);
	
	final int BLOCK=5;
	int startPage=((page-1)/BLOCK*BLOCK)+1;
	int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
	
	if(endPage>totalpage) {
		endPage=totalpage;
	}
		   
	map=new HashMap();
	map.put("list", list);
	map.put("curpage", page);
	map.put("totalpage", totalpage);
	map.put("startPage", startPage);
	map.put("endPage", endPage);
	
	// JSON으로 변환후 전송 
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(map);
	return json;
	}

	@GetMapping(value="kboGoods/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String kboGoods_detail(int gno) throws Exception{
		KboGoodsVO vo=kgService.kboGoodsDetailData(gno);
		String temp=vo.getPrice();
		temp=temp.replaceAll("[^0-9]", "");	//글자 제거
		vo.setOrigin_price(Integer.parseInt(temp.trim()));
		
		String temp1=vo.getDelivery();
		temp1=temp1.replaceAll("/ 배송비 :", "");	//글자 제거
		vo.setSimple_delivery(temp1.trim());
		
		String temp2=vo.getDelivery();
		temp2=temp2.replaceAll("[^0-9]", "");	//글자 제거
		vo.setOrigin_delivery(Integer.parseInt(temp2.trim()));		 
		
		String contemp1=vo.getContent();
		String origincontent[]=contemp1.split(", ");
		vo.setOrigin_content(origincontent[0]);
		vo.setOrigin_content1(origincontent[1]);


		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
	return json;
	}
	
	//장바구니
	@PostMapping(value="kbogoods/cart_insert.do",produces = "text/plain;charset=UTF-8")
	  public String cart_insert(int gno,int account,HttpSession session)
	  {
		  String result="";
		  try
		  {
			  String id=(String)session.getAttribute("userId");
			  KboGoodsCartVO vo=new KboGoodsCartVO();
			  vo.setGno(gno);
			  vo.setAccount(account);
			  vo.setId(id);
			  // 오라클 저장 
			  int count=kgService.goodsCartGnoCount(gno);
			  if(count==0)
			  {
				  kgService.GoodsCartInsert(vo);
			  }
			  else
			  {
				  kgService.goodsCartAccountUpdate(vo);
			  }
			  result="yes";
		  }catch(Exception ex)
		  {
			  result=ex.getMessage();  
		  }
		  return result;
	  }
	
	@GetMapping(value="kbogoods/cart_cancel_vue.do",produces = "text/plain;charset=UTF-8")
	  public String cart_cancel(int cno,HttpSession session) throws Exception
	  {
		  kgService.goodsCartCancel(cno);
		  String id=(String)session.getAttribute("userId");
		  List<KboGoodsCartVO> list=kgService.goodsCartListData(id);
		  ObjectMapper mapper=new ObjectMapper();
		  String json=mapper.writeValueAsString(list);
		  return json;
	  }
	  @GetMapping(value="kbogoods/goods_detail_vue.do",produces = "text/plain;charset=UTF-8")
	  public String goods_detail_cart(int gno) throws Exception
	  {
		  KboGoodsVO vo=kgService.kboGoodsDetailData(gno);
		  String temp=vo.getPrice();
		  temp=temp.replaceAll("[^0-9]", "");	//글자 제거
		  vo.setOrigin_price(Integer.parseInt(temp.trim()));
		  
		  ObjectMapper mapper=new ObjectMapper();
		  String json=mapper.writeValueAsString(vo); // vo = response.data
		  
		  return json;
	  }
	
	  @GetMapping(value="kbogoods/buy_vue.do",produces = "text/plain;charset=UTF-8")
	  public String goods_buy_vue(HttpSession session)throws Exception
	  {
		  String id=(String)session.getAttribute("userId");
		  List<KboGoodsCartVO> list=kgService.goodsBuyListData(id);
		  ObjectMapper mapper=new ObjectMapper();
		  String json=mapper.writeValueAsString(list);
		  return json;
	  }
	  
	  @GetMapping(value="kbogoods/cart_cancel_vue2.do",produces = "text/plain;charset=UTF-8")
	  public String cart_cancel2(int cno,HttpSession session) throws Exception
	  {
		  kgService.goodsCartCancel(cno);
		  String id=(String)session.getAttribute("userId");
		  List<KboGoodsCartVO> list=kgService.goodsBuyListData(id);
		  ObjectMapper mapper=new ObjectMapper();
		  String json=mapper.writeValueAsString(list);
		  return json;
	  }
}
