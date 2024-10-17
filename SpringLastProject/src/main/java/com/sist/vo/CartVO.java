package com.sist.vo;

import lombok.Data;
import java.util.* ;
import com.sist.vo.*;

@Data
public class CartVO {
	private int cno , gno , account , isbuy ;
	private String id,dbday;
	private Date regdate;
	private MemberVO mvo = new MemberVO();
	private GoodsVO gvo = new GoodsVO();
}
