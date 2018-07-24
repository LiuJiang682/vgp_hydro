package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroTemplate;

public class VgpHydroLocationMetaDataValidatorTest {

	private VgpHydroLocationMetaDataValidator testInstance;
	private Map<String, List<String>> templateParamMap;
	private Template dataBean;
	
	@Test
	public void shouldReturnEmptyMessageWhenCorrectDataProvided() {
		//Given
		givenTestInstance();
		testInstance.init(TestFixture.getLocMetaData());
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getLocMetaHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(false));
	}
	
	@Test
	public void shouldReturnMissingSiteIdMessageWhenStrsIsMissingSiteId() {
		//Given
		givenTestInstance();
		String[] strs = {"", "abc", "123"};
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getLocMetaHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroLocMeta column Site_ID cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnMissingColumnHeaderMessageWhenColumnHeadersIsNotProvided() {
		//Given
		givenTestInstance();
		testInstance.init(TestFixture.getLocMetaData());
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("No column header has been passing down")));
	}
	
	@Test
	public void shouldReturnIncorrectSizeMessageWhenStrsIsLT3() {
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
		assertThat(messageList.get(0), is(equalTo("Location meta data record requires minimum 3 columns, only got 2")));
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
		assertThat(messageList.get(0), is(equalTo("Location meta data record requires minimum 3 columns, only got 0")));
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
		testInstance = new VgpHydroLocationMetaDataValidator();
		templateParamMap = new HashMap<>();
		dataBean = new VgpHydroTemplate();
	}
}
