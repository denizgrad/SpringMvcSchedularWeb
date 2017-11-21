package com.denizozen.scape.schedulerWeb.constant;
/**
 * 
 * @author deniz.ozen
 *
 */
public enum Status {
	
	PLANNED("Planned","1"), IN_PROGRESS("In Progress","2"), FINISHED("Finished","3");
	
	private final String fullName;
    private final String shortName;

    private Status(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }
    
    public static Status getByShortValue(String shortName) {
    	Status ret = null;
    	for (Status s : Status.values()) {
			if(s.getShortName().equals(shortName)) {
				ret = s;
			}
		}
    	return ret;
    }
}
