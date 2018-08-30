package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleMeta;
import au.gov.vic.ecodev.mrt.template.processor.model.Entity;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.rowmapper.SampleMetaRowMapper;

public class VgpHydroSamplesMetaDaoImpl implements VgpHydroSamplesMetaDao {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroSamplesMetaDaoImpl.class);
	
	private static final String INSERT_SQL = "INSERT INTO SAMP_METADATA values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SELECT_SQL = "SELECT * FROM SAMP_METADATA WHERE ID = ?";

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean updateOrSave(Entity entity) {
		SampleMeta sampleMeta = (SampleMeta) entity;
		int row = jdbcTemplate.update(INSERT_SQL, 
				new Object[] {sampleMeta.getId(), sampleMeta.getLoaderId(), 
						sampleMeta.getSiteId(), sampleMeta.getSampleId(), 
						sampleMeta.getFileName(), sampleMeta.getRowNumber(),
						sampleMeta.getCoreId(), sampleMeta.getLabCode(),
						sampleMeta.getType(), sampleMeta.getPrepCode(),
						sampleMeta.getSampleDate(), sampleMeta.getIgsn(), 
						sampleMeta.getSampleTop(), sampleMeta.getSampleBottom(),
						sampleMeta.getStandardWaterLevel(), sampleMeta.getPumpingDepth(),
						sampleMeta.getReference(), sampleMeta.getSampleAreaDesc()});
		return Numerals.ONE == row;
	}

	@Override
	public Entity get(long id) {
		SampleMeta sampleMeta = null;
		try {
			sampleMeta = jdbcTemplate.queryForObject(SELECT_SQL, new Object[] {id}, new SampleMetaRowMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.warn("No sampleMeta found for ID: " + id, e);
		}
		return sampleMeta;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		LOGGER.info("Inside VgpHydroSamplesMetaDaoImpl.setJdbcTemplate: " + jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}

}
