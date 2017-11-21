package com.denizozen.scape.schedulerWeb.dao;

import java.util.List;

import com.denizozen.scape.schedulerWeb.model.Doctor;

public class DoctorDaoImpl extends BaseDao implements DoctorDao{
	@Override
	public void addDoctor(Doctor doctor) {
		addModel(doctor);
	}

	@Override
	public Doctor getDoctor(int id) {
		return getCurrentSession().get(Doctor.class, id);
	}

	@Override
	public void deleteDoctor(int id) {
		deleteModel(Doctor.class, id);
	}

	@Override
	public List<Doctor> getDoctors() {
		return getModels(Doctor.class);
	}

}
