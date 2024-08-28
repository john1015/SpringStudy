package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//��ü ����
public class MainClass {
	public static void main(String[] args) {
		// 1.�����̳ʿ� XML�Ľ� ��û
		ApplicationContext app = new ClassPathXmlApplicationContext("app1.xml");
		// 2. �ʿ��� ��ü�� ��û 
		Board b = (Board)app.getBean("board");
		System.out.println("b : "+b);
		b.list();
		b.insert();
		Board b1 = app.getBean("board",Board.class); // ���׸� => �ڵ� ����ȯ
		System.out.println("b1 : "+b1);
		// 3. �ʿ信 ���� �޼ҵ� ȣ�� �Ŀ� ��� : �̱��� = �Ѱ��� �޸� �ּҸ� �̿��ؼ� ����
		b1.list();
		b1.insert();
	}
}
