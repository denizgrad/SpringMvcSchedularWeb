package com.denizozen.scape.schedulerWeb.dao;

import java.util.List;

import com.denizozen.scape.schedulerWeb.constant.Status;
import com.denizozen.scape.schedulerWeb.model.Study;

public interface StudyDao {
	void addStudy(Study study);
	void updateStudy(Study study);
	void updateStatus(int studyId, Status status);
	Study getStudy(int id);
	void deleteStudy(int id);
	List<Study> getStudies();
}
