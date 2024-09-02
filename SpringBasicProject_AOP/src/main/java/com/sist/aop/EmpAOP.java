package com.sist.aop;

import java.util.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.dao.*;

@Component
public class EmpAOP {
	@Autowired
	private EmpDAO dao;
	/*
	 * public void insert(){
	 * 		SqlSession session = null; => before
	 *     try{
	 *     		session=ssf.openSession()
	 *         session.insert("id")
	 *     } catch (Exception ex){
	 *     		ex.printStackTrace() => afterThrowing
	 *     }
	 * 
	 * }
	 */
	@Before("exection(* com.sist.dao.EmpDAO.*(..))")
	public void getConnection() {
		dao.getConnection();
	}
	@After("exection(* com.sist.dao.EmpDAO.*(..))")
	public void disConnection() {
		dao.disConnection();
	}
	@Around(value="exection(* com.sist.dao.EmpDAO.*(..))")
	public Object log(ProceedingJoinPoint jp){
		Object obj = null;
		try {
			long start=System.currentTimeMillis();
			obj=jp.proceed();
			System.out.println(jp.getSignature().getName());
			long end = System.currentTimeMillis();
			System.out.println((end-start));
		} catch (Throwable ex) {}
		return obj;
	}
	@AfterReturning(value="executuion(*com.sist.dao.EmpDAO.*())",returning = "obj")
	public void afterReturning(Object obj) {
		List<EmpVO> list = (List<EmpVO>)obj;
		for (EmpVO vo : list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob()+" "+vo.getHiredate().toString()+" "+vo.getSal() );
		}
	}
	@AfterThrowing(value="executuion(*com.sist.dao.EmpDAO.*())",throwing="obj")
	public void afterThrowing(Throwable ex) throws Throwable{
		System.out.println("==================");
		ex.printStackTrace();
	}
	}
