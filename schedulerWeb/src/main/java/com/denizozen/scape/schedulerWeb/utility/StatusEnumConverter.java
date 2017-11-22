package com.denizozen.scape.schedulerWeb.utility;

import java.beans.PropertyEditorSupport;

import com.denizozen.scape.schedulerWeb.constant.Status;

public class StatusEnumConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	Status sex = Status.getByShortValue((text));
        setValue(sex);
    }
}
