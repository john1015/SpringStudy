package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sist.model.*;
import com.sist.model.MemberModel;

/*
 *     Spring
 *     ===== Web ���̺귯��
 *     	  |
 *     Ŭ���� ���� => �޸� ���� ����(���� = �Ҹ�)
 *     						���ռ��� ���� ���α׷� => �ٸ� Ŭ������ ������ ��ġ�� �ʴ´�
 *     						=================================
 *     						   | ��������
 *     						=> ����� ����
 *     							 == ��� ������ Ŭ������ �°� �ҽ� �ڵ�
 *     								   ===> ������������ ��ӹ޴� ���� ���� �幰��
 *     								   ===> POJO (2.5����) => �Ϲ� �ڹ�
 *     								   ===> �����̳� : �淮 / Ŭ����ȭ
 *     											 ==== 
 *     											  ������ => ������ / �����Ǿ� / ���콺 => ����
 *     									�б� / ���� / ����� / ���� => ������
 *     									================ �������� (������ ����)
 *     1. ������Ʈ�� �´� Ŭ���� ����
 *     2. ���� => Ŭ������ Ŭ������ ���� ���踦 ���� : .xml , .annotation
 *     3. �ش� ��ġ���� ���۵� �޼ҵ�
 *     ====================================================
 */

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		BoardModel bmodel = (BoardModel)app.getBean("board");
		bmodel.list();
		MemberModel memmodel = (MemberModel)app.getBean("member");
		memmodel.list();
		FoodModel fmodel = (FoodModel)app.getBean("food");
		fmodel.list();
		RecipeModel remodel = (RecipeModel)app.getBean("recipe");
		remodel.list();
		MainModel model = (MainModel)app.getBean("main");
		model.list();
	}
}
