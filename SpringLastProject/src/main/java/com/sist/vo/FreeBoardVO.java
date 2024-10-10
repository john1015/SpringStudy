package com.sist.vo;

import java.util.*;
import lombok.Data;

@Data // getter / setter
public class FreeBoardVO {
	private int no , hit;
	private String id,name , subject , content ,dbday;
	private Date regdate;
}
