package com.denizozen.scape.schedulerWeb.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.denizozen.scape.schedulerWeb.constant.Status;
import com.denizozen.scape.schedulerWeb.model.Study;

/**
 * 
 * @author deniz.ozen
 *
 */
@Repository
public class StudyDaoImpl extends BaseDao implements StudyDao {

	@Override
	public void addStudy(Study study) {
		if (study.getStatus() == null)
			study.setStatus(Status.PLANNED);
		addModel(study);
	}

	@Override
	public void updateStudy(Study study) {
		Study stuToUpdate = getStudy(study.getId());
		stuToUpdate.setName(study.getName());
		stuToUpdate.setStartTime(study.getStartTime());
		stuToUpdate.setEndTime(study.getEndTime());
		stuToUpdate.setDescription(study.getDescription());
		stuToUpdate.setDoctors(study.getDoctors());
		stuToUpdate.setRoom(study.getRoom());
		stuToUpdate.setStatus(study.getStatus());
		stuToUpdate.setDoctorIds(study.getDoctorIds());
		stuToUpdate.setRoomId(study.getRoomId());
		stuToUpdate.setPatientId(study.getPatientId());
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

	/**
	 * no use yet
	 */
	@Override
	public void updateStatus(int studyId, Status status) {
		String sql = "update stu from Study stu " + "set status = :status " + " where stu.id = :id";
		getCurrentSession().createQuery(sql).setParameter("id", studyId).setParameter("status", status).executeUpdate();
	}

}
