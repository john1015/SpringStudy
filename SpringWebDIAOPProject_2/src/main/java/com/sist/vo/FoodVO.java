package com.sist.vo;

import lombok.Data;

/*
 *  FNO                                       NOT NULL NUMBER(38)
	 NAME                                               VARCHAR2(900)
	 TYPE                                               VARCHAR2(900)
	 PHONE                                              VARCHAR2(100)
	 ADDRESS                                            VARCHAR2(2000)
	 SCORE                                              NUMBER(2,1)
	 THEME                                              VARCHAR2(2000)
	 POSTER                                             VARCHAR2(2000)
	 IMAGES                                             VARCHAR2(2000)
	 TIME                                               VARCHAR2(1000)
	 PARKING                                            VARCHAR2(2000)
	 CONTENT                                            VARCHAR2(2000)
	 RDAYS                                              VARCHAR2(100)
	 JJIMCOUNT                                          NUMBER(38)
	 LIKECOUNT                                          NUMBER(38)
	 HIT                                                NUMBER(38)
 */
@Data
public class FoodVO {
	private int fno;
	private String name, type , address , time , parking , theme,phone,poster;
}
