package com.sist.spring4;

import lombok.Data;

@Data
public class Sawon {
	private int sabun;
	private String name;
	private String dept;
	public Sawon() {}
	public Sawon(int sabun,String name,String dept) {
		this.sabun=sabun;
		this.name=name;
		this.dept=dept;
	}
	public void print() {
		System.out.println("사번 : "+sabun);
		System.out.println("이름 : "+name);
		System.out.println("부서 : "+dept);
	}
}
