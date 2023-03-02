package com.web.app.upbit.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.web.app.upbit.vo.CashMemory;
import com.web.app.upbit.vo.TickerVo;

@Repository
public class Upbit2Dao {

	@Resource(name="sqlSession") private SqlSession session;
	public void test() {
		session.selectOne("Upbit2.test");
	}
	public void insert_ticker(TickerVo tickerVo) {
		session.insert("Upbit2.insert_ticker", tickerVo);		
	}	
}
