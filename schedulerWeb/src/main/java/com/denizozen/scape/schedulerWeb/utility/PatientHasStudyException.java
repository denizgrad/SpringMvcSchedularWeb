package com.denizozen.scape.schedulerWeb.utility;


/**
 * 
 * @author deniz.ozen
 *
 */
public class PatientHasStudyException extends BaseUncheckedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer patientId;

	public PatientHasStudyException(Integer organizationId) {
		super();
		this.patientId	 = organizationId;
	}

	/**
	 * @return the patientId
	 */
	public Integer getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	@Override
	public String getLabelCode() {
		return "exp.patient.has.study";
	}

}
