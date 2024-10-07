package com.sist.vo;

import java.util.*;
import lombok.Data;

@Data
public class HotelVO {
	private int hno,price,jjimcount,likecount,hit;
	private String name,address,checkin,checkout,location,poster,images,rdays;
	private Double score;
}
