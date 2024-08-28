package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface EmpMapper {
	@Results({
		@Result(property = "dvo.dname",column="dname"),
		@Result(property = "dvo.loc",column="loc"),
		@Result(property = "svo.grade",column="grade")
	})
	@Select("select empno,ename,job,to_char(hiredate,'YYYY-MM-DD') as dbday , "
			+ "sal,dname,loc,grade "
			+ "from emp,dept,salgrade "
			+ "where emp.deptno = dept.deptno and sal between losal and hisal")
	public List<EmpVO> empListData();
}
