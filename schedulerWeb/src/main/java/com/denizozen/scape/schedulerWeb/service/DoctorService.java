package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import javax.persistence.NoResultException;

import com.denizozen.scape.schedulerWeb.model.Doctor;

public interface DoctorService {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws NoResultException
	 *             if member not exists with id
	 */
	public Doctor getDoctor(int id);

	public List<Doctor> getDoctors();
}