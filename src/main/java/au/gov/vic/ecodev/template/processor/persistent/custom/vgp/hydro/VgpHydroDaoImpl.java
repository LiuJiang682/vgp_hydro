package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import au.gov.vic.ecodev.mrt.template.processor.model.Entity;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroEntity;

public class VgpHydroDaoImpl implements VgpHydroDao {
	
	private static final Logger LOGGER = Logger.getLogger(VgpHydroDaoImpl.class);
	
	private static final String INSERT_SQL = "INSERT INTO VGP_HYDRO_TEMP(ID, LOADER_ID) VALUES (?, ?) ";
	
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean updateOrSave(Entity entity) {
		LOGGER.info("Inside CustomDao.updateOrSave");
		VgpHydroEntity vgpHydroEntity = (VgpHydroEntity)entity;
		int row = jdbcTemplate.update(INSERT_SQL, new Object[] {vgpHydroEntity.getId(), vgpHydroEntity.getLoaderId()});
		return 1 == row;
	}

	@Override
	public Entity get(long id) {
		LOGGER.info("Inside CustomDao.get");
		return null;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		LOGGER.info("Inside CustomDao.setJdbcTemplate: " + jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
}
