package com.denizozen.scape.schedulerWeb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.denizozen.scape.schedulerWeb.model.Patient;
import com.denizozen.scape.schedulerWeb.service.PatientService;
import com.denizozen.scape.schedulerWeb.utility.Action;

@Controller
@RequestMapping(value="/patient")
public class PatientController extends AController{
	@Autowired
	private PatientService patientService;
	
	@Override
	protected Action[] createHomeActions() {
		Action [] actions = new Action[1];
		actions[0] = new Action("/patient/add", getMessage("add.member"));
		return actions;
	}
	
	@GetMapping ("/add")
	public String addStudyPage(ModelMap modelMap) {
		addPageAttributesOfNew(modelMap);
		modelMap.addAttribute("patient", Patient.EMPTY);
		return PATIENT_ADD_FORM;
	}
	

	@PostMapping (value="/add")
	public String addingStudy(@Valid @ModelAttribute Patient patient, BindingResult bindingResult,
			ModelMap modelMap) {
		addPageAttributesOfNew(modelMap);

		if (bindingResult.hasErrors()) {
			return PATIENT_ADD_FORM;
		}
		patientService.addPatient(patient);
		String message = getMessage("msg.successful.add", getMessage("study"));
		modelMap.addAttribute("message", message);
		modelMap.addAttribute("patient", patient);
		return PATIENT_ADD_FORM;
	}
	
	@RequestMapping(value="/list")
	public String listOfPatients(ModelMap modelMap) {
		modelMap.addAttribute("patients", patientService.getPatients());
		return STUDY_LIST;
	}
	private void addPageAttributesOfNew (ModelMap modelMap) {
		modelMap.addAttribute("pageTitle", getMessage("add.patient"));
		modelMap.addAttribute("description", getMessage("add.patient.desc"));
	}

}
