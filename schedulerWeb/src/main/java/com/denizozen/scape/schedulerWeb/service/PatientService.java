package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import javax.persistence.NoResultException;

import com.denizozen.scape.schedulerWeb.model.Patient;
import com.denizozen.scape.schedulerWeb.model.Study;

public interface PatientService {

	public void addPatient(Patient patient);

	public void updatePatient(Patient patient);

	/**
	 * 
	 * @param id
	 * @return
	 * @throws NoResultException
	 *             if member not exists with id
	 */
	public Study getPatient(int id);

	public void deletePatient(int id);

	public List<Patient> getPatients();

}