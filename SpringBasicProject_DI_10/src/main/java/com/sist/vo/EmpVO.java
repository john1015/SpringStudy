package com.sist.vo;
// �޸� �Ҵ� ����� �ƴ�
import java.util.Date;
import lombok.Data;

@Data
public class EmpVO {
	private int empno , sal ;
	private String ename , job , dbday;
	private Date hiredate;
}