package com.sist.vo;

import lombok.Data;

/*
 * NO	NUMBER(38,0)
	GOODS_NAME	VARCHAR2(4000 BYTE)
	GOODS_SUB	VARCHAR2(4000 BYTE)
	GOODS_PRICE	VARCHAR2(4000 BYTE)
	GOODS_DISCOUNT	NUMBER(38,0)
	GOODS_FIRST_PRICE	VARCHAR2(4000 BYTE)
	GOODS_DELIVERY	VARCHAR2(4000 BYTE)
	GOODS_POSTER	VARCHAR2(4000 BYTE)
	HIT	NUMBER(38,0)
 */
@Data
public class GoodsVO {
	private int no , goods_discount ,hit;
	private String goods_name , goods_sub , goods_price , goods_first_price , goods_delivery , goods_poster;
}
