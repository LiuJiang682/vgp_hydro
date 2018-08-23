package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.rowmapper;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.junit.Test;
import org.mockito.Mockito;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleMeta;
import au.gov.vic.ecodev.template.constants.Constants.Strings;

public class SampleMetaRowMapperTest {

	@Test
	public void shouldReturnSampleMetaRecord() throws Exception {
		//Given
		ResultSet mockResultSet = Mockito.mock(ResultSet.class);
		when(mockResultSet.getLong(eq(Strings.COLUMN_HEADER_ID))).thenReturn(6l);
		when(mockResultSet.getLong(eq(Strings.COLUMN_HEADER_LOADER_ID))).thenReturn(23L);
		when(mockResultSet.getLong(eq(Strings.COLUMN_HEADER_SITE_ID))).thenReturn(1l);
		when(mockResultSet.getLong(eq(Strings.COLUMN_HEADER_SAMPLE_ID))).thenReturn(8l);
		when(mockResultSet.getLong(eq("CORE_ID"))).thenReturn(7L);
		when(mockResultSet.getString(eq("LAB_CODE"))).thenReturn("l");
		when(mockResultSet.getString(eq("TYPE"))).thenReturn("t1");
		when(mockResultSet.getString(eq("PREP_CODE"))).thenReturn("p1");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		when(mockResultSet.getTimestamp(eq("SAMP_DATE"))).thenReturn(date);
		when(mockResultSet.getString("IGSN")).thenReturn("IGSN");
		when(mockResultSet.getBigDecimal(eq("SAMP_TOP"))).thenReturn(BigDecimal.ONE);
		when(mockResultSet.getBigDecimal(eq("SAMP_BOTTOM"))).thenReturn(BigDecimal.TEN);
		when(mockResultSet.getBigDecimal(eq("STAND_WATER_LVL"))).thenReturn(new BigDecimal("6"));
		when(mockResultSet.getBigDecimal(eq("PUMPING_DEPTH"))).thenReturn(new BigDecimal("8"));
		when(mockResultSet.getString(eq("REFERENCE"))).thenReturn("Ref1");
		when(mockResultSet.getString(eq("SAMP_AREA_DESC"))).thenReturn("sample_are_desc");
		SampleMetaRowMapper testInstance = new SampleMetaRowMapper();
		//When
		SampleMeta sampleMeta = testInstance.mapRow(mockResultSet, 0);
		//Then
		assertThat(sampleMeta, is(notNullValue()));
		assertThat(sampleMeta.getId(), is(equalTo(6l)));
		assertThat(sampleMeta.getLoaderId(), is(equalTo(23l)));
		assertThat(sampleMeta.getSiteId(), is(equalTo(1l)));
		assertThat(sampleMeta.getSampleId(), is(equalTo(8l)));
		assertThat(sampleMeta.getCoreId(), is(equalTo(7l)));
		assertThat(sampleMeta.getLabCode(), is(equalTo("l")));
		assertThat(sampleMeta.getType(), is(equalTo("t1")));
		assertThat(sampleMeta.getPrepCode(), is(equalTo("p1")));
		assertThat(sampleMeta.getSampleDate(), is(equalTo(date)));
		assertThat(sampleMeta.getIgsn(), is(equalTo("IGSN")));
		assertThat(sampleMeta.getSampleTop(), is(equalTo(BigDecimal.ONE)));
		assertThat(sampleMeta.getSampleBottom(), is(equalTo(BigDecimal.TEN)));
		assertThat(sampleMeta.getStandardWaterLevel(), is(equalTo(new BigDecimal("6"))));
		assertThat(sampleMeta.getPumpingDepth(), is(equalTo(new BigDecimal("8"))));
		assertThat(sampleMeta.getReference(), is(equalTo("Ref1")));
		assertThat(sampleMeta.getSampleAreaDesc(), is(equalTo("sample_are_desc")));
	}
}
