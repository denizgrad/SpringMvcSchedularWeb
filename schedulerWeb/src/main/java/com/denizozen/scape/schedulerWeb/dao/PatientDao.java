package com.denizozen.scape.schedulerWeb.dao;

import java.util.List;

import com.denizozen.scape.schedulerWeb.model.Patient;
/**
 * 
 * @author deniz.ozen
 *
 */

public interface PatientDao {

	void addPatient(Patient patient);
	Patient getPatient(int id);
	void deletePatient(int id);
	List<Patient> getPatients();
	boolean hasAnyStudy(int patientId);
}
