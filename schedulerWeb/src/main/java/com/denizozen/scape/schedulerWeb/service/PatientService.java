package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import javax.persistence.NoResultException;

import com.denizozen.scape.schedulerWeb.model.Patient;
import com.denizozen.scape.schedulerWeb.model.Study;

public interface PatientService {

	public void addPatient(Patient patient);

	/**
	 * 
	 * @param id
	 * @return
	 * @throws NoResultException
	 *             if patient not exists with id
	 */
	public Patient getPatient(int id);

	public void deletePatient(int id);

	public List<Patient> getPatients();

}
