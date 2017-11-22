package com.denizozen.scape.schedulerWeb.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author deniz.ozen
 *
 */
@Entity
@Table(name="rooms")
public class Room extends AModel{
	private String name;
	
	public Room() {
		super();
	}

	@OneToMany(mappedBy = "room")
	private Set<Study> studies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room(String name) {
		super();
		this.name = name;
	}
	
	
}
