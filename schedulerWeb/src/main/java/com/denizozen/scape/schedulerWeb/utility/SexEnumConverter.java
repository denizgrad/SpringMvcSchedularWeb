package com.denizozen.scape.schedulerWeb.utility;

import java.beans.PropertyEditorSupport;

import com.denizozen.scape.schedulerWeb.constant.Sex;

public class SexEnumConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Sex sex = Sex.getByShortValue((text));
        setValue(sex);
    }
}
