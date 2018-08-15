package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.observations;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroObservationsTemplate;

public class VgpHydroObservationsHeaderValidatorTest {

	private VgpHydroObservationsHeaderValidator testInstance;
	private Map<String, List<String>> templateParamMap;
	private Template dataBean;
	
	@Test
	public void shouldReturnMissingParameterMessageWhenHeadersSiteIdNotIncluded() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getObservationHeaders();
		strs[0] = "Site_IDss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Observations Header requires the SITE_ID column")));
	}
	
	@Test
	public void shouldReturnMissingParameterMessageWhenHeadersIgsnNotIncluded() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getObservationHeaders();
		strs[2] = "IGSNss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Observations Header requires the IGSN column")));
	}
	
	@Test
	public void shouldReturnMissingParameterMessageWhenHeadersDateNotIncluded() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getObservationHeaders();
		strs[3] = "Date";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Observations Header requires the DATE/DATE_TIME column")));
	}
	
	@Test
	public void shouldReturnIncorrectSizeMessageWhenHeadersLT3() {
		//Given
		givenTestInstance();
		String[] strs = {"abc", "123"};
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("VGP Hydro Observations Header requires minimum 3 columns, only got 2")));
	}
	
	@Test
	public void shouldReturnIncorrectSizeMessageWhenHeadersIsNull() {
		//Given
		givenTestInstance();
		testInstance.init(null);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("VGP Hydro Observations Header requires minimum 3 columns, only got 0")));
	}
	
	@Test
	public void shouldSetTheStringArray() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getObservationHeaders();
		//When
		testInstance.init(strs);
		//Then
		String[] retrieved = Whitebox.getInternalState(testInstance, "strs");
		assertThat(retrieved, is(equalTo(strs)));
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
		testInstance = new VgpHydroObservationsHeaderValidator();
		templateParamMap = new HashMap<>();
		dataBean = new VgpHydroObservationsTemplate();
	}
}
