package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import au.gov.vic.ecodev.mrt.common.db.Constants.Numeral;
import au.gov.vic.ecodev.mrt.model.vgp.hydro.Observation;
import au.gov.vic.ecodev.mrt.template.processor.model.Entity;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.rowmapper.ObservationRowMapper;

public class VgpHydroObservationsDaoImpl implements VgpHydroObservationsDao {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroObservationsDaoImpl.class);
	
	private static final String INSERT_SQL = "INSERT INTO OBSERVATIONS values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SELECT_SQL = "SELECT * FROM OBSERVATIONS WHERE id = ?";
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean updateOrSave(Entity entity) {
		Observation observation = (Observation) entity;
		int row = jdbcTemplate.update(INSERT_SQL,
				new Object[] { observation.getId(), observation.getLoaderId(),
						observation.getSiteId(), observation.getSampleId(),
						observation.getIgsn(), observation.getOccurTime(),
						observation.getParameter(), observation.getDepthFrom(),
						observation.getDepthTo(), observation.getResult(),
						observation.getObserver(), observation.getType()
				});
		return Numerals.ONE == row;
	}

	@Override
	public Entity get(long id) {
		Observation observation = null;
		try {
			observation = jdbcTemplate.queryForObject(SELECT_SQL, new Object[] {id},
					new ObservationRowMapper());
		}catch (EmptyResultDataAccessException e) {
			LOGGER.warn("No observation found for ID: " + id, e);
		}
		return observation;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		LOGGER.info("Inside VgpHydroObservationsDaoImpl.setJdbcTemplate " + jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}

}
