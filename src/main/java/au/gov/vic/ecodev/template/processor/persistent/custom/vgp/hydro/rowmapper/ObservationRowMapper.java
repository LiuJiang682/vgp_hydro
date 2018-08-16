package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.Observation;

public class ObservationRowMapper implements RowMapper<Observation> {

	@Override
	public Observation mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
