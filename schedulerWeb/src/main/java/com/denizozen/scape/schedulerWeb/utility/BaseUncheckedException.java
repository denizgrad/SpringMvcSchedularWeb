package com.denizozen.scape.schedulerWeb.utility;

/**
 * @author deniz.ozen
 * 
 */
public abstract class BaseUncheckedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This code is used to show meaningful error message.
	 * Code key is read from label.properties file
	 */
	public abstract String getLabelCode ();
}
