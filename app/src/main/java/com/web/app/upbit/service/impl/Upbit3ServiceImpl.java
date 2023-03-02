package com.web.app.upbit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.dummy.dao.DummyDao;
import com.web.app.dummy.service.DummyService;
import com.web.app.upbit.dao.Upbit3Dao;
import com.web.app.upbit.dao.UpbitDao;
import com.web.app.upbit.service.Upbit3Service;
import com.web.app.upbit.service.UpbitService;
import com.web.app.upbit.vo.TickerVo;

@Service
public class Upbit3ServiceImpl implements Upbit3Service{

	@Autowired private Upbit3Dao upbit3Dao;
	
	@Override
	public void test() {
		upbit3Dao.test();
	}

	@Override
	public void insert_ticker(TickerVo tickerVo) {
		upbit3Dao.insert_ticker(tickerVo);
	}

}
