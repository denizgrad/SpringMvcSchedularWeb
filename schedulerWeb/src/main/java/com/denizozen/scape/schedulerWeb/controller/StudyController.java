package com.denizozen.scape.schedulerWeb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.denizozen.scape.schedulerWeb.constant.Status;
import com.denizozen.scape.schedulerWeb.model.Study;
import com.denizozen.scape.schedulerWeb.service.DoctorService;
import com.denizozen.scape.schedulerWeb.service.PatientService;
import com.denizozen.scape.schedulerWeb.service.RoomService;
import com.denizozen.scape.schedulerWeb.service.StudyService;
import com.denizozen.scape.schedulerWeb.utility.Action;

@Controller
@RequestMapping(value="/study")
public class StudyController extends AController{
	@Autowired
	private StudyService studyService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private DoctorService doctorService;
	
	@Override
	protected Action[] createHomeActions() {
		Action [] actions = new Action[2];
		actions[0] = new Action("/study/add", getMessage("add.study"));
		actions[1] = new Action("/study/list", getMessage("studies"));
		return actions;
	}
	
	@GetMapping ("/add")
	public String addStudyPage(ModelMap modelMap) {
		addPageAttributesOfNew(modelMap);
		addDoctorListToModel(modelMap);
		addRoomListToModel(modelMap);
		addPatientListToModel(modelMap);
		modelMap.addAttribute("study", Study.EMPTY);
		return STUDY_ADD_FORM;
	}
	

	@PostMapping (value="/add")
	public String addingStudy(@Valid @ModelAttribute Study study, BindingResult bindingResult,
			ModelMap modelMap) {
		addPageAttributesOfNew(modelMap);

		if (bindingResult.hasErrors()) {
			return STUDY_ADD_FORM;
		}
		study.getDoctorIds().forEach(n -> study.getDoctors().add(doctorService.getDoctor(Integer.parseInt(n))));
		study.setStatus(Status.PLANNED);
		studyService.addStudy(study);
		String message = getMessage("msg.successful.add", getMessage("study"));
		modelMap.addAttribute("message", message);
		modelMap.addAttribute("study", study);
		return STUDY_ADD_FORM;
	}
	
	@RequestMapping(value="/list")
	public String listOfStudies(ModelMap modelMap) {
		modelMap.addAttribute("studies", studyService.getStudies());
		return STUDY_LIST;
	}
	
	@GetMapping (value="/edit/{id}")
	public String editStudyPage(@PathVariable Integer id, ModelMap modelMap) {
		Study study = studyService.getStudy(id);
		study.getDoctors().forEach(n -> study.getDoctorIds().add(n.getId().toString()));
		modelMap.addAttribute("study", study);
		addPageAttributesOfEdit(modelMap);
		return STUDY_EDIT_FORM;
	}
	
	@PostMapping(value="/edit/{id}")
	public String editingStudy(@Valid @ModelAttribute Study study, BindingResult bindingResult, @PathVariable Integer id, 
			ModelMap modelMap) {
		if (bindingResult.hasErrors()) {
			addPageAttributesOfEdit(modelMap);
			modelMap.addAttribute("study", study);
			return STUDY_EDIT_FORM;
		}
		study.getDoctors().clear();
		study.getDoctorIds().forEach(n -> study.getDoctors().add(doctorService.getDoctor(Integer.parseInt(n))));
		studyService.updateStudy(study);
		String message = getMessage("msg.successful.edit", getMessage("study"));
		modelMap.addAttribute("message", message);
		addHomeActions(modelMap);
		return HOME;
	}
	
	@GetMapping(value="/delete/{id}")
	public String deleteStudy(@PathVariable Integer id, ModelMap modelMap) {
		studyService.deleteStudy(id);
		String message = getMessage("msg.successful.delete", getMessage("study"));
		modelMap.addAttribute("message", message);
		modelMap.addAttribute("success", true);
		addHomeActions(modelMap);
		return HOME;
	}
	
	/**
	 * Adds new ogr page title and description attributes into request
	 * @param modelMap
	 */
	private void addPageAttributesOfNew (ModelMap modelMap) {
		modelMap.addAttribute("pageTitle", getMessage("add.study"));
		modelMap.addAttribute("description", getMessage("add.study.desc"));
	}
	
	/**
	 * Adds edit ogr page title and description attributes into request
	 * @param modelMap
	 */
	private void addPageAttributesOfEdit (ModelMap modelMap) {
		modelMap.addAttribute("pageTitle", getMessage("edit.study"));
		modelMap.addAttribute("description", getMessage("edit.study.desc"));
	}


	private void addPatientListToModel(ModelMap modelMap) {
		modelMap.addAttribute("patientList", patientService.getPatients());
	}

	private void addRoomListToModel(ModelMap modelMap) {
		modelMap.addAttribute("roomList", roomService.getRooms());
	}

	private void addDoctorListToModel(ModelMap modelMap) {
		modelMap.addAttribute("doctorList", doctorService.getDoctors());
	}
	
}
