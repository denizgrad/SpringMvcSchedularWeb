package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denizozen.scape.schedulerWeb.model.Doctor;
import com.denizozen.scape.schedulerWeb.model.Study;
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService{

	@Override
	public Doctor getDoctor(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getDoctors() {
		// TODO Auto-generated method stub
		return null;
	}

}
