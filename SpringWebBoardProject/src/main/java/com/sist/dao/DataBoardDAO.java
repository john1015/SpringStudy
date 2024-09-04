package com.sist.dao;

import java.util.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public List<DataBoardVO> databoardListData(int start, int end) {
		return mapper.databoardListData(start, end);
	}

	public int databoardRowCount() {
		return mapper.databoardRowCount();
	}

	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}

	public DataBoardVO databoardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}

	public DataBoardVO databoardFileInfoData(int no) {
		return mapper.databoardFileInfoData(no);
	}

	public boolean databoardDelete(int no,String pwd) {

		boolean bcheck = false;
		String db_pwd = mapper.databoardGetPassword(no);
		// 복호화 => 원상 복귀
		// encode() : 암호화 , matches() : 바교하는 메소드
		if(encoder.matches(pwd,db_pwd)) {
			bcheck=true;
			mapper.databoardDelete(no);
		} else {
			bcheck=false;
		}
		return bcheck;
	}
}