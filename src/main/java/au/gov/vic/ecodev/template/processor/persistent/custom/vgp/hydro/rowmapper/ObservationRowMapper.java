package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.Observation;
import au.gov.vic.ecodev.template.constants.Constants.Strings;

public class ObservationRowMapper implements RowMapper<Observation> {

	@Override
	public Observation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Observation observation = new Observation();
		observation.setId(rs.getLong(Strings.COLUMN_HEADER_ID));
		observation.setLoaderId(rs.getLong(Strings.COLUMN_HEADER_LOADER_ID));
		observation.setSiteId(rs.getLong(Strings.COLUMN_HEADER_SITE_ID));
		observation.setSampleId(rs.getLong(Strings.COLUMN_HEADER_SAMPLE_ID));
		observation.setFileName(rs.getString(Strings.COLUMN_HEADER_FILE_NAME));
		observation.setRowNumber(rs.getString(Strings.COLUMN_HEADER_ROW_NUMBER));
		observation.setIgsn(rs.getString(Strings.COLUMN_HEADER_IGSN));
		observation.setOccurTime(rs.getTimestamp("OCCUR_TIME"));
		observation.setParameter(rs.getString("PARAM"));
		observation.setDepthFrom(rs.getBigDecimal("DEPTH_FROM"));
		observation.setDepthTo(rs.getBigDecimal("DEPTH_TO"));
		observation.setResult(rs.getString("RESULT"));
		observation.setObserver(rs.getString("OBSERVER"));
		observation.setType(rs.getString("TYPE"));
		return observation;
	}

}
