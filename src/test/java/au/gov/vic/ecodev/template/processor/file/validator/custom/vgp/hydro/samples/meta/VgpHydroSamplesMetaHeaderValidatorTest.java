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
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroLocMetaTemplate;

public class VgpHydroSamplesMetaHeaderValidatorTest {

	private VgpHydroSamplesMetaHeaderValidator testInstance;
	private Map<String, List<String>> templateParamMap;
	private Template dataBean;
	
	@Test
	public void shouldReturnEmptyMessageWhenCorrectHeadersProvided() {
		//Given
		givenTestInstance();
		testInstance.init(TestFixture.getSamplesMetaHeaders());
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(false));
		List<String> retrievedHeaders = templateParamMap.get(Strings.COLUMN_HEADERS);
		assertThat(retrievedHeaders, is(notNullValue()));
		assertThat(retrievedHeaders, is(equalTo(Arrays.asList(TestFixture.getSamplesMetaHeaders()))));
	}
	
	@Test
	public void shouldReturnMissingSiteIdMessageWhenHeadersNotIncludedSiteId() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaHeaders();
		strs[0] = "Site_IDss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Meta Data Header requires the Site_ID column")));
	}
	
	@Test
	public void shouldReturnMissingSampleIdMessageWhenHeadersNotIncludedSampleId() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaHeaders();
		strs[1] = "Sample_IDss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Meta Data Header requires the Sample_ID column")));
	}
	
	@Test
	public void shouldReturnMissingIgsnMessageWhenHeadersNotIncludedIgsn() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaHeaders();
		strs[7] = "IGSNss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Meta Data Header requires the IGSN column")));
	}
	
	@Test
	public void shouldReturnMissingSampleToMessageWhenHeadersNotIncludedSampleTop() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaHeaders();
		strs[8] = "Sample_Topss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Meta Data Header requires the Sample_Top column")));
	}
	
	@Test
	public void shouldReturnMissingSampleBottomMessageWhenHeadersNotIncludedSampleBottom() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaHeaders();
		strs[9] = "Sample_Bottomss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Meta Data Header requires the Sample_Bottom column")));
	}
	
	@Test
	public void shouldReturnMissingStandardWaterLevelMessageWhenHeadersNotIncludedStandingWaterLevel() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaHeaders();
		strs[10] = "Standard_Water_Levelss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Meta Data Header requires the Standing_Water_Level column")));
	}
	
	@Test
	public void shouldReturnMissingPumpingDepthMessageWhenHeadersNotIncludedPumpingDepth() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaHeaders();
		strs[11] = "Pumping_Depthss";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Meta Data Header requires the Pumping_Depth column")));
	}
	
	@Test
	public void shouldReturnMissingReferenceMessageWhenHeadersNotIncludedReference() {
		//Given
		givenTestInstance();
		String[] strs = TestFixture.getSamplesMetaHeaders();
		strs[12] = "Referencess";
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Sample Meta Data Header requires the Reference column")));
	}
	
	@Test
	public void shouldReturnIncorrectSizeMessageWhenHeadersLT8() {
		//Given
		givenTestInstance();
		String[] strs = {"abc", "123", "456", "3", "2", "1", "1"};
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Header requires minimum 8 columns, only got 7")));
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
		assertThat(messageList.get(0), is(equalTo("Header requires minimum 8 columns, only got 0")));
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
		testInstance = new VgpHydroSamplesMetaHeaderValidator();
		templateParamMap = new HashMap<>();
		dataBean = new VgpHydroLocMetaTemplate();
	}
}
