package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.vo.*;
import com.sist.mapper.*;

// �޸� �Ҵ� : 1. DAO , 2.Service , 3.Model . . .  .
// VO : �޸� �Ҵ��� �ƴϴ� (�Ϲ� ��������)
@Repository("dao")
public class MusicDAO {
	// Spring�ȿ� mybatis���� �ڵ� ���� => �ڵ����� �ּҸ� �о� �´�
	@Autowired
	private MusicMapper mapper;
	public List<MusicVO> musicListData(){
		return mapper.musicListData();
	}
	/*
	 * 
	public List<MusicVO> musicTitleFindData(String title);
	
	public List<MusicVO> musicSingerFindData(String singer);
	
	public List<MusicVO> musicAlbumFindData(String album);
	 */
	public List<MusicVO> musicTitleFindData(String title){
		return mapper.musicTitleFindData(title);
	}
	public List<MusicVO> musicSingerFindData(String singer){
		return mapper.musicSingerFindData(singer);
	}
	public List<MusicVO> musicAlbumFindData(String album){
		return mapper.musicAlbumFindData(album);
	}
	// ���� �˻� => ���� ����
	/*
	 * <foreach> : IN
	 * <trim> : ����/�߰� => OR/AND
	 * <if> : ���ǹ�
	 * <choose> => ���� ���ǹ�
	 * 		<when>
	 * </choose>
	 * ===================
	 * => <resultMap> : ����
	 * => <parameterMap> : ���ν���
	 */
	public List<MusicVO> musicFindData(int type, String fd){
		return mapper.musicFindData(type, fd);
	}
}
