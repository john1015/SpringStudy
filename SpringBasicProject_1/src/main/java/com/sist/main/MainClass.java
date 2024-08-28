package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//객체 생성
public class MainClass {
	public static void main(String[] args) {
		// 1.컨테이너에 XML파싱 요청
		ApplicationContext app = new ClassPathXmlApplicationContext("app1.xml");
		// 2. 필요한 객체를 요청 
		Board b = (Board)app.getBean("board");
		System.out.println("b : "+b);
		b.list();
		b.insert();
		Board b1 = app.getBean("board",Board.class); // 제네릭 => 자동 형변환
		System.out.println("b1 : "+b1);
		// 3. 필요에 따라 메소드 호출 후에 사용 : 싱글턴 = 한개의 메모리 주소를 이용해서 재사용
		b1.list();
		b1.insert();
	}
}
