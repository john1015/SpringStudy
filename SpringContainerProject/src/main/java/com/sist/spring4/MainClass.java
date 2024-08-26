package com.sist.spring4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 *    1. XML ���� => ���
 *    2. ���� => ������̼�
 *    =================== ����
 *    3. ��� => Container ����
 *    		DI
 *    		AOP
 *    		MVC
 *    		Transaction
 *    		Security
 *    		WebSocket
 *    		Betch : �����췯
 *    ==========
 */
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("application.xml");
		Sawon sa = (Sawon)app.getBean("sa");
		sa.setSabun(5);
		sa.setName("������");
		sa.setName("�ѹ���");
		//System.out.println(sa);
		Sawon sa1 = (Sawon)app.getBean("sa");
		//System.out.println(sa1);
		sa1.print();
		Sawon sa3 = (Sawon)app.getBean("sa");
		//System.out.println(sa3);
		sa3.print();
		Sawon sa4 = (Sawon)app.getBean("sa");
		//System.out.println(sa4);
		sa4.print();
		
		//setter �ʱ�ȭ
		//System.out.println(sa);
		sa.print();
		Sawon sa2 = (Sawon)app.getBean("sa2");
		// ������ => �ʱ�ȭ
		sa2.print();
	}
}
