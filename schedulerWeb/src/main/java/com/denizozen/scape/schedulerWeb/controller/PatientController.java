package com.denizozen.scape.schedulerWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.denizozen.scape.schedulerWeb.utility.Action;

@Controller
@RequestMapping(value="/patient")
public class PatientController extends AController{

	@Override
	protected Action[] createHomeActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
