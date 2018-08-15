package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroLocMetaTemplate;

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
	public void shouldReturnIncorrectSizeMessageWhenStrsIsLT1() {
		//Given
		givenTestInstance();
		String[] strs = {};
		testInstance.init(strs);
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Location meta data record requires minimum 1 columns, only got 0")));
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
		assertThat(messageList.get(0), is(equalTo("Location meta data record requires minimum 1 columns, only got 0")));
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
	public void shouldReturnErrorMessageWhenEastingIsNotNumber() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getLocMetaData();
		strs[6] = "abc";
		testInstance.init(strs);
		List<String> messages = new ArrayList<>();
		//When
		testInstance.doOptionalNumberFieldValidation(messages, Arrays.asList(TestFixture.getLocMetaHeaders()));
		//Then
		assertThat(messages.size(), is(equalTo(1)));
		assertThat(messages.get(0), is(equalTo("Easting is expected a number value, but got: abc")));
	}
	
	@Test
	public void shouldReturnTrueWhenEastingIsTheHeader() {
		//Given
		givenTestInstance();
		//When
		boolean flag = testInstance.isNumberField("Easting");
		//Then
		assertThat(flag, is(true));
		assertThat(testInstance.isNumberField("Northing"), is(true));
		assertThat(testInstance.isNumberField("Latitude"), is(true));
		assertThat(testInstance.isNumberField("Longitude"), is(true));
		assertThat(testInstance.isNumberField("KB"), is(true));
		assertThat(testInstance.isNumberField("elevation"), is(true));
		assertThat(testInstance.isNumberField("Bore Diameter"), is(true));
		assertThat(testInstance.isNumberField("TD"), is(true));
		assertThat(testInstance.isNumberField("TVD"), is(true));
	}
	
	@Test
	public void shouldReturnFalseWhenSiteIdIsTheHeader() {
		//Given
		givenTestInstance();
		//When
		boolean flag = testInstance.isNumberField("Site_ID");
		//Then
		assertThat(flag, is(false));
	}
	
	@Test
	public void shouldReturnTrueWhenValueIsNull() {
		//Given
		givenTestInstance();
		//When
		boolean flag = testInstance.isNumeralValueOrNull(null);
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnTrueWhenValueIsNumber() {
		//Given
		givenTestInstance();
		//When
		boolean flag = testInstance.isNumeralValueOrNull("123");
		//Then
		assertThat(flag, is(true));
		assertThat(testInstance.isNumeralValueOrNull("123.456"), is(true));
		assertThat(testInstance.isNumeralValueOrNull("123,456.789"), is(true));
	}
	
	@Test
	public void shouldReturnTrueWhenHeaderIsSiteID() {
		//Given
		givenTestInstance();
		//When
		boolean flag = testInstance.isMandatoryField("Site_ID");
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnFalseWhenHeaderIsEasting() {
		//Given
		givenTestInstance();
		//When
		boolean flag = testInstance.isMandatoryField("Easting");
		//Then
		assertThat(flag, is(false));
	}

	private void givenTestInstance() {
		testInstance = new VgpHydroLocationMetaDataValidator();
		templateParamMap = new HashMap<>();
		dataBean = new VgpHydroLocMetaTemplate();
	}
}
