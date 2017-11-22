package com.denizozen.scape.schedulerWeb.test.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.denizozen.scape.schedulerWeb.dao.PatientDao;
import com.denizozen.scape.schedulerWeb.model.Patient;
import com.denizozen.scape.schedulerWeb.service.PatientServiceImpl;
import com.denizozen.scape.schedulerWeb.utility.PatientHasStudyException;

/**
 * 
 * @author deniz.ozen
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

	@Mock
	PatientDao dao;

	@InjectMocks
	PatientServiceImpl patientService;
	
	int PAT_ID_HAS_STUDY = 2;
	
	@Before
    public void setUp() {
		MockitoAnnotations.initMocks(this);
		Patient patient = new Patient("pat-name");
		when(patientService.getPatient(1)).thenReturn(patient);
		when(patientService.getPatients()).thenReturn(createPatients(3));
		
		when(dao.hasAnyStudy(PAT_ID_HAS_STUDY)).thenReturn(true);
    }

	@Test
	public void testGetPatient () {
		Patient patient = new Patient("pat-name");
		Assert.assertEquals(patient, patientService.getPatient(1));
		
		patient.setName("abc");
		Assert.assertNotEquals(patient, patientService.getPatient(1));
		
		//verify dao.getPatient was called twice
		verify(dao, times(2)).getPatient(1);

	}
	
	@Test
	public void getPatientsTest() {
		Assert.assertEquals(3, patientService.getPatients().size());
		verify(dao).getPatients();
	}
	
	@Test (expected=PatientHasStudyException.class)
	public void deletePatientThatHasTeam() {
		patientService.deletePatient(PAT_ID_HAS_STUDY);
		verify(dao).hasAnyStudy(PAT_ID_HAS_STUDY);
	}
	
	@Test
	public void deletePatientThatHasNoTeam() {
		patientService.deletePatient(1);
		verify(dao).hasAnyStudy(1);
		verify(dao).deletePatient(1);
	}

	private List<Patient> createPatients  (int count) {
		List<Patient> result = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			Patient org = new Patient("name-" + i);
			org.setId(i);
			result.add(org);
		}
		return result;
	}
}
