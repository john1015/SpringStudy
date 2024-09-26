package com.sist.vo;

import java.util.Date;
import lombok.Data;

@Data
public class MemberVO {
	private String userId , userPwd,userName,sex,post,addr1,addr2,email,phone,content;
	private int enabled;
	private Date regdate,modifydate,lastlogin;
	private String msg,authority;
}
