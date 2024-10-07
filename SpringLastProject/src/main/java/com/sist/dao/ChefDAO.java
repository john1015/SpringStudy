package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class ChefDAO {
	@Autowired
	private ChefMapper mapper;
	public ChefVO chefToday() {
		return mapper.chefToday();
	}
}