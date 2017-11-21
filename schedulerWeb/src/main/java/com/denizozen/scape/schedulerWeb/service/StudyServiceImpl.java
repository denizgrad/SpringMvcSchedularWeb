package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denizozen.scape.schedulerWeb.dao.StudyDao;
import com.denizozen.scape.schedulerWeb.model.Doctor;
import com.denizozen.scape.schedulerWeb.model.Study;
@Service
@Transactional
public class StudyServiceImpl implements StudyService{
	
	@Autowired
	StudyDao studyDao;
	public void addStudy(Study study) {
		studyDao.addStudy(study);
	}
	public void updateStudy(Study study){
		studyDao.updateStudy(study);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws NoResultException if member not exists with id 
	 */
	public Study getStudy(int id){
		return studyDao.getStudy(id);
	}
	public void deleteStudy(int id){
		studyDao.deleteStudy(id);
	}
	public List<Study> getStudies(){
		return studyDao.getStudies();
	}
}
