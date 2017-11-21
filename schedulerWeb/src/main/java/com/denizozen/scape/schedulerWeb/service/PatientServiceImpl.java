package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denizozen.scape.schedulerWeb.dao.PatientDao;
import com.denizozen.scape.schedulerWeb.model.Patient;
import com.denizozen.scape.schedulerWeb.model.Room;
import com.denizozen.scape.schedulerWeb.utility.PatientHasStudyException;
@Service
@Transactional
public class PatientServiceImpl implements PatientService{

	@Autowired
	PatientDao patientDao;
	
	@Override
	public void addPatient(Patient patient) {
		patientDao.addPatient(patient);
	}

	@Override
	public Patient getPatient(int id) {
		return patientDao.getPatient(id);
	}

	@Override
	public void deletePatient(int id) {
		if (patientDao.hasAnyStudy (id)) {
			throw new PatientHasStudyException(id);
		}
		patientDao.deletePatient(id);
	}

	@Override
	public List<Patient> getPatients() {
		return patientDao.getPatients();
	}


}
