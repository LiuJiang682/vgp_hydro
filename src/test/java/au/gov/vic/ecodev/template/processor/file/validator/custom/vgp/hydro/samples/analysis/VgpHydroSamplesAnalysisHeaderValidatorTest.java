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
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroLocMetaTemplate;

public class VgpHydroSamplesAnalysisHeaderValidatorTest {

	private VgpHydroSamplesAnalysisHeaderValidator testInstance;
	private Map<String, List<String>> templateParamMap;
	private Template dataBean;
	
	@Test
	public void shouldReturnEmptyMessageWhenCorrectHeadersProvided() {
		//Given
		givenTestInstance();
		testInstance.init(TestFixture.getSamplesAnalysisHeaders());
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(false));
		List<String> retrievedHeaders = templateParamMap.get(Strings.COLUMN_HEADERS);
		assertThat(retrievedHeaders, is(notNullValue()));
		assertThat(retrievedHeaders, is(equalTo(Arrays.asList(TestFixture.getSamplesAnalysisHeaders()))));
	}
	
	@Test
	public void shouldReturnMissingSiteIdMessageWhenHeadersNotIncludedSiteId() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesAnalysisHeaders();
		strs[0] = "Site_IDss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Analysis Data Header requires the SITE_ID column")));
	}
	
	@Test
	public void shouldReturnMissingParameterMessageWhenHeadersNotIncludedParameter() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesAnalysisHeaders();
		strs[4] = "Site_IDss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Analysis Data Header requires the Parameter column")));
	}
	
	@Test
	public void shouldReturnMissingResultMessageWhenHeadersNotIncludedResult() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesAnalysisHeaders();
		strs[5] = "Site_IDss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Analysis Data Header requires the Result column")));
	}
	
	@Test
	public void shouldReturnMissingUomMessageWhenHeadersNotIncludedUom() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesAnalysisHeaders();
		strs[6] = "Site_IDss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Analysis Data Header requires the UOM column")));
	}
	
	@Test
	public void shouldReturnIncorrectSizeMessageWhenHeadersLT4() {
		//Given
		givenTestInstance();
		String[] strs = {"abc", "123", "def"};
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("VGP Hydro Sample Analysis Header requires minimum 4 columns, only got 3")));
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
		assertThat(messageList.get(0), is(equalTo("VGP Hydro Sample Analysis Header requires minimum 4 columns, only got 0")));
	}
	
	@Test
	public void shouldSetTheStringList() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesAnalysisHeaders();
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
		testInstance = new VgpHydroSamplesAnalysisHeaderValidator();
		templateParamMap = new HashMap<>();
		dataBean = new VgpHydroLocMetaTemplate();
	}
}
