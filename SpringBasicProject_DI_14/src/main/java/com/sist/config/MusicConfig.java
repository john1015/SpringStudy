package com.sist.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
// �ڵ��� �ܼ� : ������ ���̺귯�� ���� => XML , Annotation
// �����ӿ�ũ�� �⺻ Ʋ�� ����(Ʋ�� �°� �ҽ� �ڵ�)
// Ŭ���� ��� ���� application-context.xml
/*
 * 		��� ó�� Ŭ���� : �����ͺ��̽� ���� (DAO) , Jsoup (Component)
 * 							   Model 
 * 		@Component
 *     @Repository
 *     @Service
 *     @Controller
 *     @RestController
 *     @ControllerAdvice
 *     @Configuration
 *     ===> Ŭ�������� ������ ���� �޸� �Ҵ� (��ü ����)
 *     	          �������� �޸� �Ҵ�
 *     �Ϲ� ������
 *     ======
 *        1) DI : Ŭ���� ���� (���)
 *        2) AOP : ���� ��� ���� ==> CommonsModel => �ڵ� ȣ�� (�ݹ�)
 *            AOP�� ���ͼ�Ʈ�� ������
 *            => ȸ������
 *        3) MVC 
 *        --------------------------------------------------------------------------------
 *        1) Security
 *        2) Batch
 *        =========
 *        3) Cloud
 *        4) AI
 */
@Configuration
@ComponentScan(basePackages = {"com.sist.*"})
public class MusicConfig {

}
