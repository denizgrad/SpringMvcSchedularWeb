package com.denizozen.scape.schedulerWeb.test.integration;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.denizozen.scape.schedulerWeb.dao.PatientDao;
import com.denizozen.scape.schedulerWeb.init.BaseTestConfig;
import com.denizozen.scape.schedulerWeb.init.JdbcForTestConfig;
import com.denizozen.scape.schedulerWeb.model.Patient;

/**
 * Tests are run in memory hsqldb database
 * 
 * @author deniz.ozen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
public class PatientDaoTest {

	@Autowired
	PatientDao dao;
	
	@Autowired
	JdbcForTestConfig nativeSQL;
	
	static String PAT_NAME = "pat-name";
	static String PAT_NAME_OTHER = "pat-name-other";


	@Test
	@Transactional
	public void addPatientTest() {
		Patient patient = createPatient(PAT_NAME);
		dao.addPatient(patient);
		String patName = nativeSQL.select("select p.name from patients p where p.id = ?", String.class, patient.getId());
		Assert.assertEquals(patName, PAT_NAME);
	}

	@Test
	@Transactional
	public void updatePatientTest() {
		Patient patient = createPatient(PAT_NAME);
		dao.addPatient(patient);
		int id = patient.getId();
		
		String patName = "PATNAME";
		Patient orgToUpdate = createPatient(patName);
		orgToUpdate.setId(id);
		dao.updatePatient(orgToUpdate);
		
		String testOrgName = nativeSQL.select("select p.name from patients p where p.id = ?", 
				String.class, 
				id);

		Assert.assertEquals(patName, testOrgName);
	}

	@Test
	@Transactional
	public void getPatientTest() {
		Patient patient = createPatient(PAT_NAME);
		dao.addPatient(patient);
		Patient test = dao.getPatient(patient.getId());
		Assert.assertEquals(test.getName(), PAT_NAME);
	}

	@Test
	@Transactional
	public void deletePatient() {
		Patient patient = createPatient(PAT_NAME);
		dao.addPatient(patient);
		int id = patient.getId();
		dao.deletePatient(id);
		long testCount = nativeSQL.select("select count(1) from patients o where o.id = ?", 
				Long.class, 
				id);
		Assert.assertEquals(0L, testCount);
	}

	@Test
	@Transactional
	public void getPatients() {
		Patient patient = createPatient(PAT_NAME);
		dao.addPatient(patient);
		patient = createPatient(PAT_NAME_OTHER);
		dao.addPatient(patient);	
		
		int testSize = dao.getPatients().size();
		long testCount = nativeSQL.select("select count(1) from patients", 
				Long.class);
		Assert.assertEquals(testCount, testSize);
		Assert.assertEquals(2, testSize);
		
	}

	
	private Patient createPatient (String patName) {
		Patient patient = new Patient(patName);
		return patient;
	}

}
