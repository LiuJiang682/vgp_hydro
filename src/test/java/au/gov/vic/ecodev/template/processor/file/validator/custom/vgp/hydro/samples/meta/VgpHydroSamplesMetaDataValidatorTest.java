package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

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

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroLocMetaTemplate;

public class VgpHydroSamplesMetaDataValidatorTest {

	private VgpHydroSamplesMetaDataValidator testInstance;
	private Map<String, List<String>> templateParamMap;
	private Template dataBean;
	
	@Test
	public void shouldReturnEmptyMessageWhenCorrectDataProvided() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaData();
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getSamplesMetaHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(false));
	}
	
	@Test
	public void shouldReturnMissingSiteIdMessageWhenStrsIsMissingSiteId() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaData();
		strs[Numerals.ZERO] = null;
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getSamplesMetaHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroSampMeta column Site_ID cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnMissingSampleIdMessageWhenStrsIsMissingSampleId() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaData();
		strs[Numerals.ONE] = null;
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getSamplesMetaHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroSampMeta column Sample_ID cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnMissingIgsnMessageWhenStrsIsMissingIgsn() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaData();
		strs[Numerals.SEVEN] = null;
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getSamplesMetaHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroSampMeta column IGSN cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnIncorrectSizeMessageWhenStrsIsLT8() {
		//Given
		givenTestInstance();
		String[] strs = {"abc", "def", "ghi", "jkl", "mno", "pqr", "stu"};
		testInstance.init(strs);
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Location meta data record requires minimum 8 columns, only got 7")));
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
		assertThat(messageList.get(0), is(equalTo("Location meta data record requires minimum 8 columns, only got 0")));
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
		testInstance = new VgpHydroSamplesMetaDataValidator();
		templateParamMap = new HashMap<>();
		dataBean = new VgpHydroLocMetaTemplate();
	}
}
