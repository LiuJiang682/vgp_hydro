package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import org.springframework.jdbc.core.JdbcTemplate;

import au.gov.vic.ecodev.mrt.template.processor.model.Entity;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;

public class CustomDao implements Dao {

	@Override
	public boolean updateOrSave(Entity entity) {
		System.out.println("Inside CustomDao.updateOrSave");
		return true;
	}

	@Override
	public Entity get(long id) {
		System.out.println("Inside CustomDao.get");
		return null;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		System.out.println("Inside CustomDao.setJdbcTemplate: " + jdbcTemplate);
	}

}
