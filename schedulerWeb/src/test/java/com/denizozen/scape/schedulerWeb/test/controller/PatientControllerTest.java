package com.denizozen.scape.schedulerWeb.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.denizozen.scape.schedulerWeb.controller.AController;
import com.denizozen.scape.schedulerWeb.init.BaseTestConfig;
import com.denizozen.scape.schedulerWeb.model.Patient;
import com.denizozen.scape.schedulerWeb.service.PatientService;

/**
 * 
 * @author deniz.ozen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
@Rollback (value=false)
public class PatientControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private PatientService patientService;
	

	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testAddGet() throws Exception {
		mockMvc.perform(get("/patient/add"))
			.andExpect(status().isOk())
			.andExpect(view().name(AController.PATIENT_ADD_FORM))
            .andExpect(forwardedUrl("/WEB-INF/pages/add-patient-form.jsp"));
	}
	

	@Test
	public void testAddPostSuccess() throws Exception {
		mockMvc.perform(post("/patient/add")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("name", "pat-name"))
				.andExpect(view().name(AController.PATIENT_LIST))
				.andExpect(model().attribute("patient", Patient.EMPTY));
		
		List<Patient> list = patientService.getPatients();
		Patient testPatient = new Patient("pat-name") ;
		Assert.assertTrue(list.contains(testPatient));
		clearPatients(list);
	}
	
	@Test
	public void testAddPostFail() throws Exception {
		mockMvc.perform(post("/patient/add")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("name", ""))
				.andExpect(view().name(AController.PATIENT_ADD_FORM))
				.andExpect(model().hasErrors());
	}
	
	
	@Test
	public void testDeleteSuccess() throws Exception {
		Patient patient = addPatientToDb("pat-name");
		int patId = patient.getId();
		
		mockMvc.perform(get("/patient/delete/" + patId))
			.andExpect(view().name(AController.HOME))
			.andExpect(model().attribute("success", true));
		
		Assert.assertNull(patientService.getPatient(patId));
	}
	
//	@Test
//	public void testDeleteFail() throws Exception {
//		Patient patient = new Patient("pat-name");
//		patientService.addPatient(patient);
//		Study study = new Study("study-name");
//		study.setPatient(patient);
//		studyService.addStudy(study);
//		
//		int patId = patient.getId();
//		//save...
//	
//		mockMvc.perform(get("/patient/delete/" + patId))
//			.andExpect(view().name(AController.HOME))
//			.andExpect(model().attribute("success", false));
//		
//		Assert.assertNotNull(patientService.getPatient(patId));
//	}

	private Patient addPatientToDb (String patName) {
		Patient patient = new Patient(patName);
		patientService.addPatient(patient);
		return patient;
	}
	
	private void clearPatients(List<Patient> list) {
		list.forEach(n -> patientService.deletePatient(n.getId()));
	}
	
}
