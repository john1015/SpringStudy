package com.sist.main;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass2 {
	public static void main(String[] args) {
		GenericXmlApplicationContext app = new GenericXmlApplicationContext("app2.xml"); // ��ü ����
		Board b = (Board)app.getBean("board"); // �ʿ��� ��� ȣ��
		// init()�޼ҵ� �ڵ� ȣ��
		b.list();
		b.insert();
		app.close(); //��ü �Ҹ� destroy() �ڵ� ȣ��
		// �����̳ʴ� ��ü�� ��Ƽ� ����
		// ��ü ���� = setter = ������ ��� = ��ü �Ҹ�
		// ==========						=====
	}
}
