package com.sist.vo;

import lombok.Data;
import java.util.*;

@Data
public class KboGoodsCartVO {
	private int cno,gno,account,isbuy;
	private Date regdate;
	private String dbday,id;
	private MemberVO mvo=new MemberVO();
	private KboGoodsVO gvo=new KboGoodsVO();
}
