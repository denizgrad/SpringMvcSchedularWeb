package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import javax.persistence.NoResultException;

import com.denizozen.scape.schedulerWeb.model.Doctor;
import com.denizozen.scape.schedulerWeb.model.Study;

public class StudyServiceImpl implements StudyService{
	public void addStudy(Study study) {
	}
	public void updateStudy(Study study){
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws NoResultException if member not exists with id 
	 */
	public Study getStudy(int id){
		return null;
	}
	public void deleteStudy(int id){
	}
	public List<Study> getStudies(){
		return null;
	}
}