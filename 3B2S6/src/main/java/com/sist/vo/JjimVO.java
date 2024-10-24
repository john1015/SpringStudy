package com.sist.vo;

import java.util.Date;
import lombok.Data;

/*
 * JNO	NUMBER
HNO	NUMBER
ID	VARCHAR2(100 BYTE)
REGDATE	DATE
 */
@Data
public class JjimVO {
	private int jno;
	private int hno;
	private String id,dbday;
	private Date regdate;
}
