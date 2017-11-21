package com.denizozen.scape.schedulerWeb.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.denizozen.scape.schedulerWeb.constant.Sex;
@Entity
@Table(name="patients")
public class Patient extends AModel{

	public static final Object EMPTY = new Patient();
	@NotNull
	@Size (min=5, max=250)
	private String name;
	@Enumerated
	private Sex sex;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dayOfBirth;
	
	@OneToMany(mappedBy = "patient")
	private Set<Study> studies;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Date getDayOfBirth() {
		return dayOfBirth;
	}
	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	
	
}
