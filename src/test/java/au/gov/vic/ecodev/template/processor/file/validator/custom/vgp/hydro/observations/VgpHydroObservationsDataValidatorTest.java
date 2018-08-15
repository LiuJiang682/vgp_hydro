package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.observations;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroObservationsTemplate;

public class VgpHydroObservationsDataValidatorTest {

	private VgpHydroObservationsDataValidator testInstance;
	private String[] strs;
	private Map<String, List<String>> templateParamMap;
	private Template dataBean;
	
	@Test
	public void shouldReturnEmptyMessageWhenCorrectDataIsProvided() {
		//Given
		givenTestInstance();
		testInstance.init(TestFixture.getObservationsDatas());
		templateParamMap.put(Strings.COLUMN_HEADERS, 
				Arrays.asList(TestFixture.getObservationHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(false));
	}
	
	@Test
	public void shouldReturnMissingSiteIdMessageWhenNoSiteIdProvided() {
		//Given
		givenTestInstance();
		strs = TestFixture.getObservationsDatas();
		strs[Numerals.ZERO] = null;
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, 
				Arrays.asList(TestFixture.getObservationHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroObservation column SITE_ID cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnMissingIGSNMessageWhenNoIgsnProvided() {
		//Given
		givenTestInstance();
		strs = TestFixture.getObservationsDatas();
		strs[Numerals.TWO] = null;
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, 
				Arrays.asList(TestFixture.getObservationHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroObservation column IGSN cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnMissingDateMessageWhenNoDateProvided() {
		//Given
		givenTestInstance();
		strs = TestFixture.getObservationsDatas();
		strs[Numerals.THREE] = null;
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, 
				Arrays.asList(TestFixture.getObservationHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroObservation column DATE/DATE_TIME cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnIncorrectSizeMessageWhenStrsIsLT3() {
		//Given
		givenTestInstance();
		String[] strs = {"abc",  "ghi"};
		testInstance.init(strs);
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Observations data record requires minimum 3 columns, only got 2")));
	}
	
	@Test
	public void shouldReturnIncorrectSizeMessageWhenStrsIsNull() {
		//Given
		givenTestInstance();
		testInstance.init(null);
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Observations data record requires minimum 3 columns, only got 0")));
	}
	
	@Test
	public void shouldSetTheStringArray() {
		//Given
		givenTestInstance();
		strs = TestFixture.getObservationsDatas();
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
		testInstance = new VgpHydroObservationsDataValidator();
		templateParamMap = new HashMap<>();
		dataBean = new VgpHydroObservationsTemplate();
	}
}
