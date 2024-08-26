package com.sist.spring5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	
	//Spring 4 => xml+java
	//Spring 5 => java
	//Spring 6 => domain
	public static void main(String[] args) {
		//String[] xml = {"mysawon.xml","mymember.xml"};
		ApplicationContext app = new ClassPathXmlApplicationContext("my*.xml");
		Sawon sa = (Sawon)app.getBean("sa");
		sa.print();
		Member mem = (Member)app.getBean("mem");
		mem.print();
	}
}
