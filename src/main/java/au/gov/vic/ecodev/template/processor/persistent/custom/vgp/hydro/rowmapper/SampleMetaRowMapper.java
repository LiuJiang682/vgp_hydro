package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleMeta;

public class SampleMetaRowMapper implements RowMapper<SampleMeta> {

	private static final String COLUMN_HEADER_SAMPLE_AREA_DESC = "SAMP_AREA_DESC";
	private static final String COLUMN_HEADER_REFERENCE = "REFERENCE";
	private static final String COLUMN_HEADER_PUMPING_DEPTH = "PUMPING_DEPTH";
	private static final String COLUMN_HEADER_STAND_WATER_LEVEL = "STAND_WATER_LVL";
	private static final String COLUMN_HEADER_SAMP_BOTTOM = "SAMP_BOTTOM";
	private static final String COLUMN_HEADER_SAMP_TOP = "SAMP_TOP";
	private static final String COLUMN_HEADER_IGSN = "IGSN";
	private static final String COLUMN_HEADER_SAMP_DATE = "SAMP_DATE";
	private static final String COLUMN_HEADER_PREP_CODE = "PREP_CODE";
	private static final String COLUMN_HEADER_TYPE = "TYPE";
	private static final String COLUMN_HEADER_LAB_CODE = "LAB_CODE";
	private static final String COLUMN_HEADER_CORE_ID = "CORE_ID";
	private static final String COLUMN_HEADER_SAMPLE_ID = "SAMPLE_ID";
	private static final String COLUMN_HEADER_SITE_ID = "SITE_ID";
	private static final String COLUMN_HEADER_LOADER_ID = "LOADER_ID";
	private static final String COLUMN_HEADER_ID = "ID";

	@Override
	public SampleMeta mapRow(ResultSet rs, int rowNum) throws SQLException {
		SampleMeta sampleMeta = new SampleMeta();
		sampleMeta.setId(rs.getLong(COLUMN_HEADER_ID));
		sampleMeta.setLoaderId(rs.getLong(COLUMN_HEADER_LOADER_ID));
		sampleMeta.setSiteId(rs.getLong(COLUMN_HEADER_SITE_ID));
		sampleMeta.setSampleId(rs.getLong(COLUMN_HEADER_SAMPLE_ID));
		sampleMeta.setCoreId(rs.getLong(COLUMN_HEADER_CORE_ID));
		sampleMeta.setLabCode(rs.getString(COLUMN_HEADER_LAB_CODE));
		sampleMeta.setType(rs.getString(COLUMN_HEADER_TYPE));
		sampleMeta.setPrepCode(rs.getString(COLUMN_HEADER_PREP_CODE));
		sampleMeta.setSampleDate(rs.getTimestamp(COLUMN_HEADER_SAMP_DATE));
		sampleMeta.setIgsn(rs.getString(COLUMN_HEADER_IGSN));
		sampleMeta.setSampleTop(rs.getBigDecimal(COLUMN_HEADER_SAMP_TOP));
		sampleMeta.setSampleBottom(rs.getBigDecimal(COLUMN_HEADER_SAMP_BOTTOM));
		sampleMeta.setStandardWaterLevel(rs.getBigDecimal(COLUMN_HEADER_STAND_WATER_LEVEL));
		sampleMeta.setPumpingDepth(rs.getBigDecimal(COLUMN_HEADER_PUMPING_DEPTH));
		sampleMeta.setReference(rs.getString(COLUMN_HEADER_REFERENCE));
		sampleMeta.setSampleAreaDesc(rs.getString(COLUMN_HEADER_SAMPLE_AREA_DESC));
		return sampleMeta;
	}

}
