package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denizozen.scape.schedulerWeb.dao.DoctorDao;
import com.denizozen.scape.schedulerWeb.model.Doctor;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorDao doctorDao;

	@Override
	public Doctor getDoctor(int id) {
		return doctorDao.getDoctor(id);
	}

	@Override
	public List<Doctor> getDoctors() {
		return doctorDao.getDoctors();
	}

}
