package com.denizozen.scape.schedulerWeb.test.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.denizozen.scape.schedulerWeb.dao.StudyDao;
import com.denizozen.scape.schedulerWeb.model.Study;
import com.denizozen.scape.schedulerWeb.service.StudyServiceImpl;

/**
 * 
 * @author deniz.ozen
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class StudyServiceTest {

	@Mock
	StudyDao dao;

	@InjectMocks
	StudyServiceImpl studyService;
	
	int STU_ID_1 = 1;
	int STU_ID_2 = 2;
	
	@Before
    public void setUp() {
		MockitoAnnotations.initMocks(this);
		Study team = new Study("study-name", "study-desc", new Date());
		team.setId(STU_ID_1);
		when(studyService.getStudy(STU_ID_1)).thenReturn(team);
		when(studyService.getStudies()).thenReturn(createStudies(4));
		
    }
	
	@Test
	public void deleteStudyThatHasNoMember() {
		studyService.deleteStudy(STU_ID_1);
		verify(dao).deleteStudy(STU_ID_1);
	}
	
	private List<Study> createStudies (int count) {
		List<Study> result = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			Study team = new Study("team-name-" + i, "team-desc-" + i ,new Date());
			team.setId(i);
			result.add(team);
		}
		return result;
	}
}
