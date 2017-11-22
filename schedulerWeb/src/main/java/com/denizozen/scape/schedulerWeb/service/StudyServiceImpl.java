package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denizozen.scape.schedulerWeb.constant.Status;
import com.denizozen.scape.schedulerWeb.dao.StudyDao;
import com.denizozen.scape.schedulerWeb.model.Study;

@Service
@Transactional
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyDao studyDao;

	public void addStudy(Study study) {
		studyDao.addStudy(study);
	}

	public void updateStudy(Study study) {
		studyDao.updateStudy(study);
	}

	public Study getStudy(int id) {
		Study study = studyDao.getStudy(id);
		if (study != null) {
			Hibernate.initialize(study.getDoctors());
		}
		return study;
	}

	public void deleteStudy(int id) {
		studyDao.deleteStudy(id);
	}

	public List<Study> getStudies() {
		return studyDao.getStudies();
	}

	@Override
	public void updateStatus(int studyId, Status status) {
		studyDao.updateStatus(studyId , status);
	}
}
