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
import com.web.app.upbit.service.Upbit3Service;
import com.web.app.upbit.service.UpbitService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/3")
public class Upbit3Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Upbit3Controller.class);
	
	@Autowired private Upbit3Service upbit3Service;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {		
		upbit3Service.test();			
		return "upbit3/index";
	}	
}
