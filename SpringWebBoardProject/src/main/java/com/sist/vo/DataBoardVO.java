package com.sist.vo;

import java.util.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class DataBoardVO {
	private int no , hit ,filecount;
	private String  name , subject , content , pwd , dbday,filename,filsize;
	private List<MultipartFile> files;
	// List 전송을 받는 경우
	// <input type=file name="files[0]">
}
