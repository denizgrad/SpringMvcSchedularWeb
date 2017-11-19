package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denizozen.scape.schedulerWeb.model.Doctor;
import com.denizozen.scape.schedulerWeb.model.Patient;
import com.denizozen.scape.schedulerWeb.model.Study;
@Service
@Transactional
public class PatientServiceImpl implements PatientService{

	@Override
	public void addPatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Study getPatient(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePatient(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Patient> getPatients() {
		// TODO Auto-generated method stub
		return null;
	}


}
