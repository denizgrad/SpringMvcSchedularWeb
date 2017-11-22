package com.denizozen.scape.schedulerWeb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

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

import com.denizozen.scape.schedulerWeb.constant.Sex;
import com.denizozen.scape.schedulerWeb.constant.Status;
import com.denizozen.scape.schedulerWeb.model.Patient;
import com.denizozen.scape.schedulerWeb.service.PatientService;
import com.denizozen.scape.schedulerWeb.utility.Action;
import com.denizozen.scape.schedulerWeb.utility.PatientHasStudyException;
import com.denizozen.scape.schedulerWeb.utility.SexEnumConverter;
import com.denizozen.scape.schedulerWeb.utility.StatusEnumConverter;

@Controller
@RequestMapping(value="/patient")
public class PatientController extends AController{
	@Autowired
	private PatientService patientService;
	
	@Override
	protected Action[] createHomeActions() {
		Action[] actions = new Action [2];
		actions[0] = new Action("/patient/add", getMessage("add.patient"));
		actions[1] = new Action("/patient/list", getMessage("list.patient"));
		return actions;
	}
	
	@GetMapping ("/add")
	public String addPatientPage(ModelMap modelMap) {
		addPageAttributesOfNew(modelMap);
		modelMap.addAttribute("patient", Patient.EMPTY);
		addSexListToModel(modelMap);
		return PATIENT_ADD_FORM;
	}
	

	@PostMapping (value="/add")
	public String addingPatient(@Valid @ModelAttribute Patient patient, BindingResult bindingResult,
			ModelMap modelMap) {
		addPageAttributesOfNew(modelMap);

		if (bindingResult.hasErrors()) {
			addSexListToModel(modelMap);
			return PATIENT_ADD_FORM;
		}
		patientService.addPatient(patient);
		String message = getMessage("msg.successful.add", getMessage("study"));
		modelMap.addAttribute("message", message);
		modelMap.addAttribute("patients", patientService.getPatients());
		addSexListToModel(modelMap);
		return PATIENT_LIST;
	}
	
	@RequestMapping(value="/list")
	public String listOfPatients(ModelMap modelMap) {
		modelMap.addAttribute("patients", patientService.getPatients());
		return PATIENT_LIST;
	}
	
	@GetMapping(value="/delete/{id}")
	public String deletePatient(@PathVariable Integer id, ModelMap modelMap) {
		addHomeActions(modelMap);
		String message;
		boolean success;
		try {
			patientService.deletePatient(id);
			message = getMessage("msg.successful.delete", getMessage("patient"));
			success = true;
		} catch (PatientHasStudyException e) {
			message = getMessage (e, e.getPatientId());
			success = false;
		}
		modelMap.addAttribute("message", message);
		modelMap.addAttribute("success", success);
		return HOME;
	}
	private void addPageAttributesOfNew (ModelMap modelMap) {
		modelMap.addAttribute("pageTitle", getMessage("add.patient"));
		modelMap.addAttribute("description", getMessage("add.patient.desc"));
	}
	
	private void addSexListToModel(ModelMap modelMap) {
		modelMap.addAttribute("sexList", Sex.values());
	}
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat2.setLenient(true);
	    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat2, true));
	    webDataBinder.registerCustomEditor(Sex.class, new SexEnumConverter());
	}


}
