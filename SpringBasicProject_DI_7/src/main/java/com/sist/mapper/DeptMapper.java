package com.sist.mapper;

import java.util.*;
import org.apache.ibatis.annotations.Select;
import com.sist.vo.DeptVO;
// XML , Annotation => 동시 설정
//				| 사용자 정의
//	| 라이브러리 사용
public interface DeptMapper {
	@Select("SELECT * FROM dept ORDER BY deptno ASC")
	public List<DeptVO> deptListData();
}
