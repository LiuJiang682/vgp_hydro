package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleAnalysis;
import au.gov.vic.ecodev.mrt.template.processor.model.Entity;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.rowmapper.SampleAnalysisRowMapper;

public class VgpHydroSamplesAnalysisDaoImpl implements VgpHydroSamplesAnalysisDao {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroSamplesAnalysisDaoImpl.class);

	private static final String INSERT_SQL = "INSERT INTO SAMP_ANALYSIS values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SELECT_SQL = "SELECT * FROM SAMP_ANALYSIS WHERE id = ?";
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean updateOrSave(Entity entity) {
		SampleAnalysis sampleAnalysis = (SampleAnalysis)entity;
		int row = jdbcTemplate.update(INSERT_SQL, 
				new Object[] {sampleAnalysis.getId(), sampleAnalysis.getLoaderId(),
						sampleAnalysis.getSampleId(), sampleAnalysis.getFileName(),
						sampleAnalysis.getRowNumber(), sampleAnalysis.getIgsn(), 
						sampleAnalysis.getLabSampleNo(), sampleAnalysis.getAnalysisDate(), 
						sampleAnalysis.getParameter(), sampleAnalysis.getUom(), 
						sampleAnalysis.getResult(), sampleAnalysis.getAnanlysisMethod(), 
						sampleAnalysis.getLor()});
		return Numerals.ONE ==  row;
	}

	@Override
	public Entity get(long id) {
		SampleAnalysis sampleAnalysis = null;
		try {
			sampleAnalysis = jdbcTemplate.queryForObject(SELECT_SQL, new Object[] {id}, 
					new SampleAnalysisRowMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.warn("No sampleMeta found for ID: " + id, e);
		}
		return sampleAnalysis;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		LOGGER.info("Inside VgpHydroSamplesAnalysisDaoImpl.setJdbcTemplate");
		this.jdbcTemplate = jdbcTemplate;
	}

}
