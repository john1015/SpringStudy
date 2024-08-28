package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.sist.vo.*;

public interface GoodsMapper {
	@Select("select no,goods_name,goods_sub,goods_price,goods_first_price,goods_delivery, num "
			+ " from (select no,goods_name,goods_sub,goods_price,goods_first_price,goods_delivery, rownum as num "
			+ "          from(select no,goods_name,goods_sub,goods_price,goods_first_price,goods_delivery "
			+ "                   from goods_all order by no asc))"
			+ " where num between #{start} and #{end}")
	public List<GoodsVO> goodsListData(@Param("start")int start , @Param("end")int end);
	@Select("select ceil(count(*)/10.0) from goods_all")
	public int goodsTotalPage();
	
	@Select("select no,goods_name,goods_sub,goods_price,goods_first_price,goods_delivery "
			+ " from goods_all"
			+ "  where no = #{no}")
	public GoodsVO goodsDetailData(int no);
}
