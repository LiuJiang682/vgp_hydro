package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.helper;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;

public class DataValueHelperTest {

	private DataValueHelper testInstance;
	private List<String> headers;
	private List<String> datas;
	
	@Test
	public void shouldReturnNotFoundAsSampleAreDescriptionWhenGetDataValueAsLongCalledWithArrayIndexOutOfBoundary() {
		//Given
		givenTestInstanceWithData();
		//When
		long sampleId = testInstance.getDataValueAsLong(headers, datas, "Sample_Area_Description");
		//Then
		assertThat(sampleId, is(equalTo(-1l)));
	}
	
	@Test
	public void shouldReturnRefAsReferenceWhenGetDataValueAsLongCalled() {
		//Given
		givenTestInstanceWithData();
		//When
		String ref = testInstance.getDataValueAsString(headers, datas, "Reference");
		//Then
		assertThat(ref, is(equalTo("Ref")));
	}
	
	@Test
	public void shouldReturnNullAsSampleAreDescriptionWhenGetDataValueAsStringCalledWithArrayIndexOutOfBoundary() {
		//Given
		givenTestInstanceWithData();
		//When
		String desc = testInstance.getDataValueAsString(headers, datas, "Sample_Area_Description");
		//Then
		assertThat(desc, is(nullValue()));
	}
	
	@Test
	public void shouldReturnTimestampWhenGetDataValueAsTimestampCalled() {
		//Given
		givenTestInstanceWithData();
		//When
		Timestamp timestamp = testInstance.getDataValueAsTimestamp(headers, datas, "Sampled_Date");
		//Then
		assertThat(timestamp, is(notNullValue()));
		assertThat(timestamp.toString(), is(equalTo("2018-01-05 00:00:00.0")));
	}
	
	@Test
	public void shouldReturnNullWhenGetDataValueAsTimestampCalledWithPrepCode() {
		//Given
		givenTestInstanceWithData();
		//When
		Timestamp timestamp = testInstance.getDataValueAsTimestamp(headers, datas, "Preparation Code");
		//Then
		assertThat(timestamp, is(nullValue()));
	}
	
	@Test
	public void shouldReturnNullWhenGetDataValueAsTimestampCalledWithLabCode() {
		//Given
		givenTestInstanceWithData();
		//When
		Timestamp timestamp = testInstance.getDataValueAsTimestamp(headers, datas, "Lab Code");
		//Then
		assertThat(timestamp, is(nullValue()));
	}
	
	@Test
	public void shouldReturnNotFoundAsCoreIDWhenGetDataValueAsLongCalledWithCoreId() {
		//Given
		givenTestInstanceWithData();
		//When
		long sampleId = testInstance.getDataValueAsLong(headers, datas, "CORE ID");
		//Then
		assertThat(sampleId, is(equalTo(-1l)));
	}
	
	@Test
	public void shouldReturn123AsSampleIDWhenGetDataValueAsLongCalled() {
		//Given
		givenTestInstanceWithData();
		//When
		long sampleId = testInstance.getDataValueAsLong(headers, datas, "SAMPLE_ID");
		//Then
		assertThat(sampleId, is(equalTo(123l)));
	}
	
	@Test
	public void shouldReturnBigDecimalAsSampleToWhenGetDataValueAsBigDecimalCalled() {
		//Given
		givenTestInstanceWithData();
		//When
		BigDecimal sampleTop = testInstance.getDataValueAsBigDecimal(headers, datas, "SAMPLE_TOP");
		//Then
		assertThat(sampleTop, is(equalTo(new BigDecimal("48"))));
	}
	
	@Test
	public void shouldReturnBigDecimalAsSampleToWhenGetDataValueAsBigDecimalCalledWithDecimalPointData() {
		//Given
		givenTestInstanceWithData();
		datas.set(8, "123.45");
		//When
		BigDecimal sampleTop = testInstance.getDataValueAsBigDecimal(headers, datas, "SAMPLE_TOP");
		//Then
		assertThat(sampleTop, is(equalTo(new BigDecimal("123.45"))));
	}
	
	@Test
	public void shouldReturnNotFoundAsSampleToWhenGetDataValueAsBigDecimalCalledWithNullData() {
		//Given
		givenTestInstanceWithData();
		datas.set(8, null);
		//When
		BigDecimal sampleTop = testInstance.getDataValueAsBigDecimal(headers, datas, "SAMPLE_TOP");
		//Then
		assertThat(sampleTop, is(equalTo(new BigDecimal("-1"))));
	}
	
	private void givenTestInstanceWithData() {
		testInstance = new DataValueHelper();
		headers = Arrays.asList(TestFixture.getSamplesMetaHeaders());
		datas = Arrays.asList(TestFixture.getSamplesMetaData());
	}
}
