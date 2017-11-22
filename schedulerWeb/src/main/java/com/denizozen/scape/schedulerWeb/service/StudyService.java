package com.denizozen.scape.schedulerWeb.service;

import java.util.List;

import javax.persistence.NoResultException;

import com.denizozen.scape.schedulerWeb.constant.Status;
import com.denizozen.scape.schedulerWeb.model.Study;

public interface StudyService {

	public void addStudy(Study study);

	public void updateStudy(Study study);

	/**
	 * 
	 * @param id
	 * @return
	 * @throws NoResultException
	 *             if study not exists with id
	 */
	public Study getStudy(int id);

	public void deleteStudy(int id);

	public void updateStatus(int studyId, Status status);
	
	public List<Study> getStudies();

}
