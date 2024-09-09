package com.sist.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
 * 1. ��ü ���� -> ����
 * 2. ����ټ��� �ʱ�ȭ �Ŀ� ��ü ���� => ����
 * 	   = setXxx() => setter DI
 * 	   = ������ => constructor DI
 *  <bean id="mem" class="com.sist.main.Member"> => ���� �߻�
 *  class A{
 *  	private int a,b;
 *  }
 */

public class Member {
	private int mno;
	private String name;
	private String sex;
	private String address;
	private String phone;
	
	public Member(int mno, String name, String sex, String address, String phone) {
		this.mno = mno;
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.phone = phone;
	}

	public void print() {
		System.out.println("ȸ�� ��ȣ :"+  mno);
		System.out.println("�̸�:"  +name);
		System.out.println("����:" + sex);
		System.out.println("�ּ�:" + address);
		System.out.println("��ȭ��ȣ:" + phone);
	}
}
