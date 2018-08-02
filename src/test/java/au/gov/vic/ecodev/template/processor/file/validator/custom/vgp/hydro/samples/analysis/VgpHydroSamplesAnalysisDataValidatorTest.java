package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.analysis;

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
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroLocMetaTemplate;

public class VgpHydroSamplesAnalysisDataValidatorTest {

	private VgpHydroSamplesAnalysisDataValidator testInstance;
	private String[] strs;
	private Map<String, List<String>> templateParamMap;
	private Template dataBean;
	
	@Test
	public void shouldReturnEmptyMessageWhenCorrectDataProvided() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesAnalysisDatas();
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getSamplesAnalysisHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(false));
	}
	
	@Test
	public void shouldReturnMissingSiteIdMessageWhenStrsIsMissingSiteId() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesAnalysisDatas();
		strs[Numerals.ZERO] = null;
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getSamplesAnalysisHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroSampAnal column SITE_ID cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnMissingParameterMessageWhenStrsIsMissingParameter() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesAnalysisDatas();
		strs[Numerals.FOUR] = null;
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getSamplesAnalysisHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroSampAnal column Parameter cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnMissingResultMessageWhenStrsIsMissingResult() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesAnalysisDatas();
		strs[5] = null;
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getSamplesAnalysisHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroSampAnal column Result cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnMissingUomMessageWhenStrsIsMissingUom() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesAnalysisDatas();
		strs[6] = null;
		testInstance.init(strs);
		templateParamMap.put(Strings.COLUMN_HEADERS, Arrays.asList(TestFixture.getSamplesAnalysisHeaders()));
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("ERROR: Line 0: Template vgphydroSampAnal column UOM cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnIncorrectSizeMessageWhenStrsIsLT4() {
		//Given
		givenTestInstance();
		String[] strs = {"abc", "def", "ghi"};
		testInstance.init(strs);
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Samples analysis data record requires minimum 4 columns, only got 3")));
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
		assertThat(messageList.get(0), is(equalTo("Samples analysis data record requires minimum 4 columns, only got 0")));
	}
	
	@Test
	public void shouldSetTheStringArray() {
		//Given
		givenTestInstance();
		strs = TestFixture.getSamplesAnalysisDatas();
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
		testInstance = new VgpHydroSamplesAnalysisDataValidator();
		templateParamMap = new HashMap<>();
		dataBean = new VgpHydroLocMetaTemplate();
	}
}
