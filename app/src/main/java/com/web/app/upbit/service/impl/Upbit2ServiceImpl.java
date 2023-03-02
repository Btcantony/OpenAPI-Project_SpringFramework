package com.web.app.upbit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.dummy.dao.DummyDao;
import com.web.app.dummy.service.DummyService;
import com.web.app.upbit.dao.Upbit2Dao;
import com.web.app.upbit.dao.UpbitDao;
import com.web.app.upbit.service.Upbit2Service;
import com.web.app.upbit.service.UpbitService;
import com.web.app.upbit.vo.TickerVo;

@Service
public class Upbit2ServiceImpl implements Upbit2Service{

	@Autowired private Upbit2Dao upbit2Dao;
	
	@Override
	public void test() {
		upbit2Dao.test();
	}

	@Override
	public void insert_ticker(TickerVo tickerVo) {
		upbit2Dao.insert_ticker(tickerVo);
	}

}
