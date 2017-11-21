package com.denizozen.scape.schedulerWeb.dao;

import java.util.List;
import java.util.Objects;

/**
 * @author deniz.ozen
 */
import com.denizozen.scape.schedulerWeb.model.Patient;

public class PatientDaoImpl  extends BaseDao implements PatientDao{

	@Override
	public void addPatient(Patient patient) {
		addModel(patient);
	}

	@Override
	public Patient getPatient(int id) {
		return getCurrentSession().get(Patient.class, id);
	}

	@Override
	public void deletePatient(int id) {
		deleteModel(Patient.class, id);
	}

	@Override
	public List<Patient> getPatients() {
		return getModels(Patient.class);
	}

	@Override
	public boolean hasAnyStudy(int patientId) {
		String sql = "select count(elements(pat.studies)) from Patient pat "
				+ "where pat.id = :id";
		Object count = getCurrentSession().createQuery(sql)
				.setParameter("id", patientId)
				.getSingleResult();
		return Integer.parseInt(Objects.toString(count)) > 0;
	}
}
