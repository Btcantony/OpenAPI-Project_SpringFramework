package com.web.app.upbit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.dummy.dao.DummyDao;
import com.web.app.dummy.service.DummyService;
import com.web.app.upbit.dao.UpbitDao;
import com.web.app.upbit.service.UpbitService;
import com.web.app.upbit.vo.TickerVo;

@Service
public class UpbitServiceImpl implements UpbitService{

	@Autowired private UpbitDao upbitDao;
	
	@Override
	public void test() {
		upbitDao.test();
	}

	@Override
	public void insert_ticker(TickerVo tickerVo) {
		upbitDao.insert_ticker(tickerVo);
	}

}
