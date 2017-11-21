package com.denizozen.scape.schedulerWeb.dao;

import java.util.List;

import com.denizozen.scape.schedulerWeb.model.Doctor;

public interface DoctorDao {

	void addDoctor(Doctor doctor);

	Doctor getDoctor(int id);

	void deleteDoctor(int id);

	List<Doctor> getDoctors();

}
