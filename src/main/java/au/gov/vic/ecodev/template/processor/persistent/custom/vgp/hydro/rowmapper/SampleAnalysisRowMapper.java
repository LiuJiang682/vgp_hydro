package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleAnalysis;
import au.gov.vic.ecodev.template.constants.Constants.Strings;

public class SampleAnalysisRowMapper implements RowMapper<SampleAnalysis> {

	@Override
	public SampleAnalysis mapRow(ResultSet rs, int rowNum) throws SQLException {
		SampleAnalysis sampleAnalysis = new SampleAnalysis();
		sampleAnalysis.setId(rs.getLong(Strings.COLUMN_HEADER_ID));
		sampleAnalysis.setLoaderId(rs.getLong(Strings.COLUMN_HEADER_LOADER_ID));
		sampleAnalysis.setSampleId(rs.getLong(Strings.COLUMN_HEADER_SAMPLE_ID));
		sampleAnalysis.setIgsn(rs.getString(Strings.COLUMN_HEADER_IGSN));
		sampleAnalysis.setLabSampleNo(rs.getString("LAB_SAMP_NO"));
		sampleAnalysis.setAnalysisDate(rs.getTimestamp("ANAL_DATE"));
		sampleAnalysis.setParameter(rs.getString("PARAM"));
		sampleAnalysis.setUom(rs.getString("UOM"));
		sampleAnalysis.setResult(rs.getString("RESULT"));
		sampleAnalysis.setAnanlysisMethod(rs.getString("ANAL_METH"));
		sampleAnalysis.setLor(rs.getString("LOR"));
		return sampleAnalysis;
	}

}
