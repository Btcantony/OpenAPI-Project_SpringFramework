package com.web.app.dummy.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class DummyDao {

	@Resource(name="sqlSession") private SqlSession session;
	public List<Map<String, Object>> getList(Map<String, Object> params) {
		return session.selectList("SqlGoodsMapper.view", params);
	}
	
}
