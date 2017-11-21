package com.denizozen.scape.schedulerWeb.dao;

import java.util.List;

import com.denizozen.scape.schedulerWeb.model.Study;
/**
 * 
 * @author deniz.ozen
 *
 */
public class StudyDaoImpl extends BaseDao implements StudyDao{

	@Override
	public void addStudy(Study study) {
		addModel(study);
	}

	@Override
	public void updateStudy(Study study) {
		Study stuToUpdate = getStudy(study.getId());
		stuToUpdate.setName(study.getName());
		stuToUpdate.setDoctors(study.getDoctors());
		stuToUpdate.setRoom(study.getRoom());
		stuToUpdate.setStatus(study.getStatus());
		updateModel(stuToUpdate);
	}

	@Override
	public Study getStudy(int id) {
		return getCurrentSession().get(Study.class, id);
	}

	@Override
	public void deleteStudy(int id) {
		deleteModel(Study.class, id);
	}

	@Override
	public List<Study> getStudies() {
		return getModels(Study.class);
	}

}
