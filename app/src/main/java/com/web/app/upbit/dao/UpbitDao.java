package com.web.app.upbit.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.web.app.upbit.vo.TickerVo;

@Repository
public class UpbitDao {

	@Resource(name="sqlSession") private SqlSession session;
	public void test() {
		session.selectOne("Upbit.test");
	}
	public void insert_ticker(TickerVo tickerVo) {
		session.insert("Upbit.insert_ticker", tickerVo);
	}	
}
