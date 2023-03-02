package com.web.app.dummy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.dummy.dao.DummyDao;
import com.web.app.dummy.service.DummyService;

@Service
public class DummyServiceImpl implements DummyService{

	@Autowired private DummyDao dao;
	@Override
	public List<Map<String, Object>> getList(Map<String, Object> params) {

		return dao.getList(params);
	}

}
