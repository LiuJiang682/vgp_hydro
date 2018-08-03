package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleAnalysis;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;

public class SampleAnalysisBuilderTest {

	private SampleAnalysisBuilder testInstance;
	
	@Test
	public void shouldReturnSampleAnalysisObjectWhenBuildMethodCalled() throws ParseException {
		//Given
		givenTestInstance();
		//When
		SampleAnalysis sampleAnalysis = testInstance.build();
		//Then
		assertThat(sampleAnalysis, is(notNullValue()));
		assertThat(sampleAnalysis.getParameter(), is(equalTo("DWT")));
		assertThat(sampleAnalysis.getResult(), is(equalTo("97.82")));
		assertThat(sampleAnalysis.getUom(), is(equalTo("m")));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = sdf.parse("7/08/2017 14:34");
		assertThat(sampleAnalysis.getAnalysisDate(), 
				is(equalTo(new Timestamp(date.getTime()))));
	}
	
	@Test
	public void shouldReturnInstanceWithEmptyDataWhenDataIsNull() {
		//Given
		long sessionId = System.currentTimeMillis();
		List<String> headers = Arrays.asList(TestFixture.getSamplesAnalysisHeaders());
		List<String> datas = null;
		//When
		testInstance = new SampleAnalysisBuilder(sessionId, headers, datas);
		//Then
		assertThat(testInstance, is(notNullValue()));
		List<String> retrieved = Whitebox.getInternalState(testInstance, "datas");
		assertThat(retrieved, is(notNullValue()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenHeadersIsNull() {
		//Given
		long sessionId = System.currentTimeMillis();
		List<String> header = null;
		List<String> datas = null;
		//When
		new SampleAnalysisBuilder(sessionId, header, datas);
		fail("Program reached unexpected point!");
	}
	
	@Test
	public void shouldReturnInstance() {
		//Given
		givenTestInstance();
		//When
		//Then
		assertThat(testInstance, is(notNullValue()));
	}

	private void givenTestInstance() {
		long sessionId = System.currentTimeMillis();
		List<String> headers = Arrays.asList(TestFixture.getSamplesAnalysisHeaders());
		List<String> datas = Arrays.asList(TestFixture.getSamplesAnalysisDatas());
		testInstance = new SampleAnalysisBuilder(sessionId, headers, datas);
	}
}
