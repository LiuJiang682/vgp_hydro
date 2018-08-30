package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
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

import au.gov.vic.ecodev.mrt.model.vgp.hydro.Observation;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;

public class ObservationBuilderTest {

	private ObservationBuilder testInstance;
	private long sessionId;
	
	@Test
	public void shouldReturnObservationObjectWhenBuildMethodCalled() throws ParseException {
		//Given
		givenTestInstance();
		//When
		Observation observation = testInstance.build();
		//Then
		assertThat(observation, is(notNullValue()));
		assertThat(observation.getLoaderId(), is(equalTo(sessionId)));
		assertThat(observation.getSiteId(), is(equalTo(325823l)));
		assertThat(observation.getIgsn(), is(equalTo("IGSN")));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = sdf.parse("25/12/2017 18:00");
		assertThat(observation.getOccurTime(), 
				is(equalTo(new Timestamp(date.getTime()))));
		assertThat(observation.getParameter(), is(equalTo("Formation Top")));
		assertThat(observation.getDepthFrom(), is(equalTo(new BigDecimal("1697"))));
		assertThat(observation.getDepthTo(), is(equalTo(new BigDecimal("1700"))));
		assertThat(observation.getResult(), is(equalTo("Eumerella Formation")));
		assertThat(observation.getObserver(), is(equalTo("Shannon Herley")));
		assertThat(observation.getType(), is(equalTo("Core")));
	}
	
	@Test
	public void shouldReturnInstanceWithEmptyDataWhenDataIsNull() {
		//Given
		long sessionId = System.currentTimeMillis();
		List<String> headers = Arrays.asList(TestFixture.getSamplesAnalysisHeaders());
		List<String> datas = null;
		//When
		testInstance = new ObservationBuilder(sessionId, headers, datas, null, 0);
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
		new ObservationBuilder(sessionId, header, datas, null, 0);
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
		sessionId = System.currentTimeMillis();
		List<String> headers = Arrays.asList(TestFixture.getObservationHeaders());
		List<String> datas = Arrays.asList(TestFixture.getObservationsDatas());
		testInstance = new ObservationBuilder(sessionId, headers, datas, "myTest.txt", 0);
	}
}
