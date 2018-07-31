package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;

public class SampleMetaBuilderTest {

	private SampleMetaBuilder testInstance;
	private List<String> headers;
	private List<String> datas;
	private long sessionId;
	
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
