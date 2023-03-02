package com.web.app.upbit.controller;

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
import com.web.app.upbit.service.UpbitService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class UpbitController {
	
	private static final Logger logger = LoggerFactory.getLogger(UpbitController.class);
	
	@Autowired private UpbitService upbitService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {		
		upbitService.test();			
		return "upbit/index";
	}	
}
