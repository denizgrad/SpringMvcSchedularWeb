package com.denizozen.scape.schedulerWeb.utility;

/**
 * screen action
 * @author deniz.ozen
 *
 */
public class Action {
	private String url;
	private String label;
	
	public Action (String url,  String label) {
		super();
		this.url = url;
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
