package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleMeta;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;

public class SampleMetaBuilderTest {

	private SampleMetaBuilder testInstance;
	private List<String> headers;
	private List<String> datas;
	private long sessionId;
	
	@Test
	public void shouldReturnSampleMetaObjectWhenBuildMethodCalled() throws ParseException {
		//Given
		givenTestInstance();
		//When
		SampleMeta sampleMeta = testInstance.build();
		//Then
		assertThat(sampleMeta, is(notNullValue()));
		assertThat(sampleMeta.getSiteId(), is(equalTo(110098l)));
		assertThat(sampleMeta.getSampleId(), is(equalTo(123l)));
		assertThat(sampleMeta.getIgsn(), is(equalTo("678")));
		assertThat(sampleMeta.getCoreId(), is(equalTo(-1l)));
		assertThat(sampleMeta.getLabCode(), is(equalTo("GSV")));
		assertThat(sampleMeta.getType(), is(equalTo("Water")));
		assertThat(sampleMeta.getPrepCode(), is(nullValue()));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("5/01/2018");
		assertThat(sampleMeta.getSampleDate(), is(equalTo(new Timestamp(date.getTime()))));
		assertThat(sampleMeta.getSampleTop(), is(equalTo(new BigDecimal("48"))));
		assertThat(sampleMeta.getSampleBottom(), is(equalTo(new BigDecimal("66"))));
		assertThat(sampleMeta.getStandardWaterLevel(), is(equalTo(new BigDecimal("4.58"))));
		assertThat(sampleMeta.getPumpingDepth(), is(equalTo(new BigDecimal("54"))));
		assertThat(sampleMeta.getReference(), is(equalTo("Ref")));
		assertThat(sampleMeta.getSampleAreaDesc(), is(nullValue()));
	}
	
	@Test
	public void shouldReturnInstance() {
		//Given
		givenTestInstance();
		//When
		//Then
		assertThat(testInstance, is(notNullValue()));
	}
	
	@Test
	public void shouldReturnInstanceWithEmptyDataWhenDatasIsNull() {
		//Given
		sessionId = System.currentTimeMillis();
		headers = Arrays.asList(TestFixture.getSamplesMetaHeaders());
		datas = null;
		//When
		testInstance = new SampleMetaBuilder(sessionId, headers, datas);
		//Then
		assertThat(testInstance, is(notNullValue()));
		List<String> datas = Whitebox.getInternalState(testInstance, "datas");
		assertThat(datas, is(notNullValue()));
		assertThat(datas.isEmpty(), is(true));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenHeaderIsNull() {
		//Given
		sessionId = System.currentTimeMillis();
		headers = null;
		datas = null;
		//When
		new SampleMetaBuilder(sessionId, headers, datas);
		fail("Program reached unexpected point!");
	}

	private void givenTestInstance() {
		sessionId = System.currentTimeMillis();
		headers = Arrays.asList(TestFixture.getSamplesMetaHeaders());
		datas = Arrays.asList(TestFixture.getSamplesMetaData());
		testInstance = new SampleMetaBuilder(sessionId, headers, datas);
	}
}
