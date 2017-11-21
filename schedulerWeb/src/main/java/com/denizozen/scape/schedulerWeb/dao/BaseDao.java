package com.denizozen.scape.schedulerWeb.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.denizozen.scape.schedulerWeb.model.AModel;
/**
 * 
 * @author deniz.ozen
 *
 */
@Repository
public abstract class BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	protected void addModel(AModel model) {
		getCurrentSession().save(model);
		flush();
	}
	
	protected void updateModel(AModel model) {
		getCurrentSession().update(model);
		flush();
	}
	
	protected void deleteModel (Class<? extends AModel> clazz, int id) {
		AModel model = getCurrentSession().get(clazz, id);
		if (model != null) {
			getCurrentSession().delete(model);
		}
		flush();
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends AModel> List<T> getModels(Class<T> clazz) {
		return getCurrentSession().createQuery("from " + clazz.getSimpleName()).getResultList();
	}
	
	protected void flush () {
		getCurrentSession().flush();
	}
}