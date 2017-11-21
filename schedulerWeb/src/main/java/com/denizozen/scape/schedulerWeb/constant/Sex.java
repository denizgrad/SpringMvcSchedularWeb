package com.denizozen.scape.schedulerWeb.constant;

public enum Sex {

	MALE("Male", "M"),
	FEMALE("Female", "F");
	
	private final String fullName;
    private final String shortName;

    private Sex(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }
    
    public static Sex getByShortValue(String shortName) {
    	Sex ret = null;
    	for (Sex s : Sex.values()) {
			if(s.getShortName().equals(shortName)) {
				ret = s;
			}
		}
    	return ret;
    }

}
