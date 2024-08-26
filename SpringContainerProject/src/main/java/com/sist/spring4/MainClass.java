package com.sist.spring4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 *    1. XML 사용법 => 등록
 *    2. 구분 => 어노테이션
 *    =================== 사용법
 *    3. 기능 => Container 종류
 *    		DI
 *    		AOP
 *    		MVC
 *    		Transaction
 *    		Security
 *    		WebSocket
 *    		Betch : 스케쥴러
 *    ==========
 */
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("application.xml");
		Sawon sa = (Sawon)app.getBean("sa");
		sa.setSabun(5);
		sa.setName("강감찬");
		sa.setName("총무부");
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
		
		//setter 초기화
		//System.out.println(sa);
		sa.print();
		Sawon sa2 = (Sawon)app.getBean("sa2");
		// 생성자 => 초기화
		sa2.print();
	}
}
