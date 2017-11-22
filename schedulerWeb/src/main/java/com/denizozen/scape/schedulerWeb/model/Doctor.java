package com.denizozen.scape.schedulerWeb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * @author deniz.ozen
 *
 *
 */
@Entity
@Table(name="doctors")
public class Doctor extends AModel{

	public Doctor() {
		super();
	}

	public Doctor(String name) {
		super();
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "doctors")
	private Set<Study> studies;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Study> getStudies() {
		if(studies == null) {
			studies = new HashSet<Study>();
		}
		return studies;
	}

	public void setStudies(Set<Study> studies) {
		this.studies = studies;
	}
}
