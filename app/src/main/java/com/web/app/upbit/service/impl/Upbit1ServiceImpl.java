package com.web.app.upbit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.dummy.dao.DummyDao;
import com.web.app.dummy.service.DummyService;
import com.web.app.upbit.dao.Upbit1Dao;
import com.web.app.upbit.dao.UpbitDao;
import com.web.app.upbit.service.Upbit1Service;
import com.web.app.upbit.service.UpbitService;
import com.web.app.upbit.vo.TickerVo;

@Service
public class Upbit1ServiceImpl implements Upbit1Service{

	@Autowired private Upbit1Dao upbit1Dao;
	
	@Override
	public void test() {
		upbit1Dao.test();
	}

	@Override
	public void insert_ticker(TickerVo tickerVo) {
		upbit1Dao.insert_ticker(tickerVo);
	}

}
