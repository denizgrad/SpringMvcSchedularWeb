package com.denizozen.scape.schedulerWeb.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="studies")
public class Study extends AModel{
	
	public static final Object EMPTY = new Study();
	private String name;
	private String description;
	private Date startTime;
	private Date endTime;
	
	@OneToOne
	private Patient patient;
	
	@Transient
	private String patientId;
	
	@OneToOne
	private Room room;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Doctor> doctors;
	
	@Transient
	private List<String> doctorIds;

	@Formula("(select count(*) from studies_doctors sd where sd.studies_id = id)")
	int doctorCount;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setTeams(Set<Doctor> doctors) {
		this.doctors= doctors;
	}

	public List<String> getDoctorIds() {
		return doctorIds;
	}

	public void setDoctorIds(List<String> doctorIds) {
		this.doctorIds = doctorIds;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public int getDoctorCount() {
		return doctorCount;
	}

	public void setDoctorCount(int doctorCount) {
		this.doctorCount = doctorCount;
	}
	
}
