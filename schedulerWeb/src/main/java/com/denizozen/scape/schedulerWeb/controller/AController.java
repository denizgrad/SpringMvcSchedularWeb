package com.denizozen.scape.schedulerWeb.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.ModelMap;

import com.denizozen.scape.schedulerWeb.utility.Action;


public abstract class AController {

	public static final String ATTR_HOME_ACTIONS = "actions";
	public static final String HOME = "home";
	
	
	public static final String PATIENT_ADD_FORM = "add-patient-form";

	public static final String STUDY_ADD_FORM = "add-study-form";
	public static final String STUDY_EDIT_FORM = "edit-study-form";
	public static final String STUDY_LIST = "list-of-studies";
	
	@Autowired @Qualifier ("messageSource")
	protected MessageSource labelSource;
	
	/**
	 * home actions to display after forwarding when an action is processed
	 */
	private Action [] homeActions;
	
	/**
	 * Helper method for getting message form label source
	 * @return
	 */
	public String getMessage (String code, Object ... args) {
		return labelSource.getMessage(code, args, getUserLocale());
	}
	
	/**
	 * Helper method for getting user locale<br>
	 * It can be used in controllers easily
	 * @return user locale 
	 */
	public static Locale getUserLocale() {
		return LocaleContextHolder.getLocale();
	}
	/**
	 * Use this when forward to home in any action
	 * @param modelMap
	 */
	protected void addHomeActions (ModelMap modelMap) {
		modelMap.addAttribute(ATTR_HOME_ACTIONS, getHomeActions());
	}
	/**
	 * @return the homeActions
	 */
	private Action [] getHomeActions() {
		if (homeActions == null) {
			homeActions = createHomeActions();
		}
		return homeActions;
	}

	/**
	 * 
	 * @return home action array for the domain subject
	 */
	protected abstract Action [] createHomeActions ();
}
