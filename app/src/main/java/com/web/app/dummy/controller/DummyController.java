package com.web.app.dummy.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.app.dummy.service.DummyService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DummyController {
	
	private static final Logger logger = LoggerFactory.getLogger(DummyController.class);
	
	@Autowired private DummyService service;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/dummy.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		Map<String , Object> params = new HashMap<String, Object>();
		List<Map<String , Object>> list = service.getList(params);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
