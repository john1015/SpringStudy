package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		// Ŭ���� ��� (�����̳�)
		ApplicationContext app = new ClassPathXmlApplicationContext("app1.xml");
		// class path : �ڵ� �ν��ϴ� ��ġ => src/main/java
		Sawon sa1=(Sawon)app.getBean("sa1");
		sa1.print();
		System.out.println("=========");
		Sawon sa2=(Sawon)app.getBean("sa2");
		sa2.print();
		System.out.println("=========");
		Sawon sa3=(Sawon)app.getBean("sa3");
		sa3.print();
		System.out.println("=========");
	}
}
