package com.denizozen.scape.schedulerWeb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import com.denizozen.scape.schedulerWeb.constant.Status;

@Entity
@Table(name="studies")
public class Study extends AModel{
	
	public static final Object EMPTY = new Study();
	
	public Study() {
		super();
	}

	public Study(String name ,String description, Date startDate) {
		super();
		this.name = name;
		this.description = description;
		this.startTime = startDate;
	}

	@NotNull
	@Size (min=5, max=250)
	private String name;
	@NotNull
	@Size (min=5, max=250)
	private String description;
	
	@DateTimeFormat(pattern="yyyy-MM-dd'T'hh:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date startTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd'T'hh:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToOne
	private Patient patient;
	
	@Transient
	private String patientId;
	
	@OneToOne
	private Room room;
	
	@Transient
	private String roomId;
	
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
		if(doctors==null) {
			doctors = new HashSet<Doctor>(); 
		}
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors= doctors;
	}

	public List<String> getDoctorIds() {
		if(doctorIds == null) {
			doctorIds = new ArrayList<String>();
		}
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

	public int getDoctorCount() {
		return doctorCount;
	}

	public void setDoctorCount(int doctorCount) {
		this.doctorCount = doctorCount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Override
	public int hashCode() {
		return this.getId() != null ? this.getId().hashCode() :
			(!StringUtils.isEmpty(name) ? this.name.hashCode() : super.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Study) {
			Study test = (Study)obj;
			return (this.getId() != null && test.getId() != null) 
					? Objects.equals(this.getId(), test.getId())
					: (!StringUtils.isEmpty(this.name) && !StringUtils.isEmpty(test.name) ?  
							Objects.equals(this.name, test.name): super.equals(obj));
		}
		
		return super.equals(obj);
	}
	
}
