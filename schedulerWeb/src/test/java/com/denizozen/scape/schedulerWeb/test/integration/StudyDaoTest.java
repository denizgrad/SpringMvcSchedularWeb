package com.denizozen.scape.schedulerWeb.test.integration;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.denizozen.scape.schedulerWeb.dao.StudyDao;
import com.denizozen.scape.schedulerWeb.init.BaseTestConfig;
import com.denizozen.scape.schedulerWeb.init.JdbcForTestConfig;
import com.denizozen.scape.schedulerWeb.model.Study;

/**
 * Tests are run in memory hsqldb database
 * @author deniz.ozen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
public class StudyDaoTest {

	@Autowired
	StudyDao dao;
	
	@Autowired
	JdbcForTestConfig nativeSQL;
	
	static String STUDY_NAME = "stu-name";
	static String STUDY_DESC = "stu-desc";
	static String STUDY_NAME_OTHER = "stu-name-other";
	static String STUDY_DESC_OTHER = "stu-desc-other";


	@Test
	@Transactional
	public void addStudyTest() {
		Study study = new Study(STUDY_NAME, STUDY_DESC, new Date());
		dao.addStudy(study);
		String studyName = nativeSQL.select("select s.name from studies s where s.id = ?", 
				String.class, 
				study.getId());
		Assert.assertEquals(studyName, STUDY_NAME);
		dao.deleteStudy(study.getId());
	}

	@Test
	@Transactional
	public void updateStudyTest() {
		Study study = new Study(STUDY_NAME, STUDY_DESC,new Date());
		dao.addStudy(study);
		int studyId = study.getId();
		
		String testName = "TESTNAME";
		String testDesc = "TESTDESC";
		Study studyToUpdate = new Study(testName, testDesc,new Date());
		studyToUpdate.setId(studyId);
		dao.updateStudy(studyToUpdate);
		
		String actualStudyName = nativeSQL.select("select s.name from studies s where s.id = ?", 
				String.class, 
				studyId);

		Assert.assertEquals(testName, actualStudyName);
		dao.deleteStudy(studyToUpdate.getId());
	}
	
	@Test
	@Transactional
	public void getStudyTest() {
		Study study = new Study(STUDY_NAME, STUDY_DESC, new Date());
		dao.addStudy(study);
		int studyId = study.getId();

		long count = nativeSQL.select("select count(1) from studies s where s.id = ?", 
				Long.class, 
				studyId);
		
		Assert.assertEquals(count, 1);
	}
	
	@Test
	@Transactional
	public void deleteStudy() {
		int testStudyId = 1;
		nativeSQL.getJdbcTemplate().execute(
				String.format("INSERT INTO studies(id, name, description) "
						+ "VALUES(%d, 'test', 'testDesc')", testStudyId));
		
		dao.deleteStudy(testStudyId);
		long testCount = nativeSQL.select("select count(1) from studies s where s.id = ?", 
				Long.class, 
				testStudyId);
		Assert.assertEquals(0L, testCount);
	}

	@Test
	@Transactional
	public void getStudies() {
		Study study = new Study(STUDY_NAME, STUDY_DESC, new Date());
		dao.addStudy(study);
		study = new Study(STUDY_NAME_OTHER, STUDY_DESC_OTHER, new Date());
		dao.addStudy(study);
		
		int testSize = dao.getStudies().size();
		long testCount = nativeSQL.select("select count(1) from studies", Long.class);
		Assert.assertEquals(testCount, testSize);
		Assert.assertEquals(2, testSize);
	}

}
