package com.denizozen.scape.schedulerWeb.test.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matchers;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.denizozen.scape.schedulerWeb.controller.AController;
import com.denizozen.scape.schedulerWeb.init.BaseTestConfig;
import com.denizozen.scape.schedulerWeb.model.Doctor;
import com.denizozen.scape.schedulerWeb.model.Patient;
import com.denizozen.scape.schedulerWeb.model.Room;
import com.denizozen.scape.schedulerWeb.model.Study;
import com.denizozen.scape.schedulerWeb.service.DoctorService;
import com.denizozen.scape.schedulerWeb.service.PatientService;
import com.denizozen.scape.schedulerWeb.service.RoomService;
import com.denizozen.scape.schedulerWeb.service.StudyService;


/**
 * 
 * @author deniz.ozen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
@Rollback (value=true)
public class StudyControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private StudyService studyService;

	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testAddGet() throws Exception {
		mockMvc.perform(get("/study/add"))
			.andExpect(status().isOk())
			.andExpect(view().name(AController.STUDY_ADD_FORM))
            .andExpect(forwardedUrl("/WEB-INF/pages/add-study-form.jsp"));
	}
	
	@Test
	public void testEditGet() throws Exception {
		Study study = addStudyToDb("stu-name");
		
		//Update study with two doctors
		Set<Doctor> doctors = new HashSet<>();
		Doctor doctor1 = new Doctor("doctor1");
		Doctor doctor2 = new Doctor("doctor1");
		doctorService.addDoctor(doctor1);
		doctorService.addDoctor(doctor2);
		Room room = new Room("Room1");
		roomService.addRoom(room);
		Patient patient = new Patient("Patient1");
		patientService.addPatient(patient);
		
		doctors.add(doctor1);
		doctors.add(doctor2);
		study.setDoctors(doctors);
		study.setRoom(room);
		study.setPatient(patient);
		
		studyService.updateStudy(study);
		
		
		int studyId = study.getId();
		
		
		//test studys' doctors added into model with size 2
		mockMvc.perform(get("/study/edit/" + studyId))
			.andExpect(status().isOk())
			.andExpect(view().name(AController.STUDY_EDIT_FORM))
            .andExpect(forwardedUrl("/WEB-INF/pages/edit-study-form.jsp"))
            .andExpect(model().attribute("study", study))
            .andExpect(model().attribute("study", hasProperty("doctors", hasSize(2))));
	}
	
	@Test
	public void testAddPostSuccess() throws Exception {
		String name = "stuName";
		mockMvc.perform(post("/study/add")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED).params(getFormNoError(name)))
				.andExpect(view().name(AController.STUDY_LIST))
				//1 -> room , 2 -> patient
				.andExpect(model().attribute("study", Matchers.hasProperty("id", Matchers.equalTo(3))));
		
		List<Study> list = studyService.getStudies();
		Study testStudy = new Study();
		testStudy.setName(name);
		Assert.assertTrue(list.contains(testStudy));
	}
	
	@Test
	public void testAddPostFail() throws Exception {
		MultiValueMap<String, String> parts = new LinkedMultiValueMap<>();
		parts.add("name", "stu");
		parts.add("description", "stu");
		mockMvc.perform(post("/study/add")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.params(parts))
				.andExpect(view().name(AController.STUDY_ADD_FORM))
				.andExpect(model().hasErrors());
	}
	
	@Test
	public void testEditSuccess() throws Exception {
		Study study = addStudyToDb("stu-name");
		int studyId = study.getId();
		
		
		String updateName = "test to updatename";
		
		mockMvc.perform(post("/study/edit/" + studyId)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.params(getFormNoError(updateName)))
					.andExpect(view().name(AController.HOME));
		
		Study actual = studyService.getStudy(studyId);
		Assert.assertEquals(updateName, actual.getName());
	}
	
	@Test
	public void testDeleteSuccess() throws Exception {
		Study study = addStudyToDb("stu-name");
		int stuId = study.getId();
		
		mockMvc.perform(get("/study/delete/" + stuId))
			.andExpect(view().name(AController.HOME))
			.andExpect(model().attribute("success", true));
		
		Assert.assertNull(studyService.getStudy(stuId));
	}
	/**/
	private Study addStudyToDb (String studyName) {
		Study study = new Study(studyName,"stu-desc", new Date());
		studyService.addStudy(study);
		return study;
	}
	private MultiValueMap< String, String> getFormNoError(String name){
		Room room = new Room("Room1");
		roomService.addRoom(room);
		Patient patient = new Patient("Patient1");
		patientService.addPatient(patient);
		
		MultiValueMap<String, String> parts = new LinkedMultiValueMap<>();
		parts.add("name", name);
		parts.add("description", "stuDesc");
		parts.add("startTime", "2009-12-15T12:12");
		parts.add("roomId", room.getId().toString());
		parts.add("patientId", patient.getId().toString());
		
		return parts;
	}

}
