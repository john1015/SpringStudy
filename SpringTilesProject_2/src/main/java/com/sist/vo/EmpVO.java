package com.sist.vo;

import java.util.Date;
import lombok.Data;

@Data
// JOIN => 포함 클래스
public class EmpVO {
	private int empno , sal ;
	private String name , job , dbday;
	private Date hiredate;
	// 포함 has-a
	private DeptVO dvo = new DeptVO();
	private SlagradeVO svo = new SlagradeVO();
}
