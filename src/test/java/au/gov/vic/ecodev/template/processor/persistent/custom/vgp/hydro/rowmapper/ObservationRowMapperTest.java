package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.rowmapper;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;
import org.mockito.Mockito;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.Observation;
import au.gov.vic.ecodev.template.constants.Constants.Strings;

public class ObservationRowMapperTest {

	@Test
	public void shouldReturnObservationRecord() throws SQLException {
		//Given
		ResultSet mockResultSet = Mockito.mock(ResultSet.class);
		when(mockResultSet.getLong(eq(Strings.COLUMN_HEADER_ID))).thenReturn(1l);
		when(mockResultSet.getLong(eq(Strings.COLUMN_HEADER_LOADER_ID))).thenReturn(100l);
		when(mockResultSet.getLong(eq(Strings.COLUMN_HEADER_SITE_ID))).thenReturn(123l);
		when(mockResultSet.getLong(eq(Strings.COLUMN_HEADER_SAMPLE_ID))).thenReturn(456l);
		when(mockResultSet.getString(eq(Strings.COLUMN_HEADER_IGSN))).thenReturn("IGSN");
		Timestamp occurTime = new Timestamp(System.currentTimeMillis());
		when(mockResultSet.getTimestamp(eq("OCCUR_TIME"))).thenReturn(occurTime);
		when(mockResultSet.getString(eq("PARAM"))).thenReturn("PARAM");
		when(mockResultSet.getBigDecimal(eq("DEPTH_FROM"))).thenReturn(BigDecimal.ONE);
		when(mockResultSet.getBigDecimal("DEPTH_TO")).thenReturn(BigDecimal.TEN);
		when(mockResultSet.getString(eq("RESULT"))).thenReturn("RESULT");
		when(mockResultSet.getString(eq("OBSERVER"))).thenReturn("OBSERVER");
		when(mockResultSet.getString(eq("TYPE"))).thenReturn("TYPE");
		ObservationRowMapper observationRowMapper = new ObservationRowMapper();
		//When
		Observation observation = observationRowMapper.mapRow(mockResultSet, 0);
		//Then
		assertThat(observation, is(notNullValue()));
		assertThat(observation.getId(), is(equalTo(1l)));
		assertThat(observation.getLoaderId(), is(equalTo(100l)));
		assertThat(observation.getSiteId(), is(equalTo(123l)));
		assertThat(observation.getSampleId(), is(equalTo(456l)));
		assertThat(observation.getIgsn(), is(equalTo("IGSN")));
		assertThat(observation.getOccurTime(), is(equalTo(occurTime)));
		assertThat(observation.getParameter(), is(equalTo("PARAM")));
		assertThat(observation.getDepthFrom(), is(equalTo(BigDecimal.ONE)));
		assertThat(observation.getDepthTo(), is(equalTo(BigDecimal.TEN)));
		assertThat(observation.getResult(), is(equalTo("RESULT")));
		assertThat(observation.getObserver(), is(equalTo("OBSERVER")));
		assertThat(observation.getType(), is(equalTo("TYPE")));
	}
}
