package com.denizozen.scape.schedulerWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.denizozen.scape.schedulerWeb.utility.Action;

@Controller
public class RootController extends AController {
	@RequestMapping(value="/")
	public ModelAndView mainPage() {
		ModelAndView modelAndView = new ModelAndView("home");
		addHomeActions(modelAndView.getModelMap());
		return modelAndView;
	}
	
	@RequestMapping(value="/index")
	public ModelAndView indexPage() {
		return mainPage();
	}

	@Override
	protected Action[] createHomeActions() {
		Action[] actions = new Action [2];
		actions[0] = new Action("/study/add", getMessage("add.study"));
		actions[1] = new Action("/patient/add", getMessage("add.patient"));
		return actions;
	}
}
