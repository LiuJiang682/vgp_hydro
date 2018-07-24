package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroTemplate;

public class VgpHydroLocationMetaHeaderValidatorTest {

	private VgpHydroLocationMetaHeaderValidator testInstance;
	private Map<String, List<String>> templateParamMap;
	private Template dataBean;
	
	@Test
	public void shouldReturnEmptyMessageWhenCorrectHeadersProvided() {
		//Given
		givenTestInstance();
		testInstance.init(TestFixture.getLocMetaHeaders());
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(false));
	}
	
	@Test
	public void shouldReturnMissingSiteIdMessageWhenHeadersNotIncludedSiteId() {
		//Given
		givenTestInstance();
		String[] strs = {"Site_IDss", "easting", "northing"};
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Header requires the Site_ID column")));
	}
	
	@Test
	public void shouldReturnMissingEastingMessageWhenHeadersNotIncludedEasting() {
		//Given
		givenTestInstance();
		String[] strs = {"Site_ID", "eastingss", "northing"};
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Header requires the Easting column")));
	}
	
	@Test
	public void shouldReturnMissingNorthingMessageWhenHeadersNotIncludedNorthing() {
		//Given
		givenTestInstance();
		String[] strs = {"Site_ID", "easting", "northingss"};
		testInstance.init(strs);
		
		//When
		Optional<List<String>> messages = testInstance.validate(templateParamMap, dataBean);
		//Then
		assertThat(messages.isPresent(), is(true));
		List<String> messageList = messages.get();
		assertThat(messageList.size(), is(equalTo(1)));
		assertThat(messageList.get(0), is(equalTo("Header requires the Northing column")));
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
		assertThat(messageList.get(0), is(equalTo("Header requires minimum 3 columns, only got 2")));
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
		assertThat(messageList.get(0), is(equalTo("Header requires minimum 3 columns, only got 0")));
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
		this.testInstance = new VgpHydroLocationMetaHeaderValidator();
		templateParamMap = new HashMap<>();
		dataBean = new VgpHydroTemplate();
	}
}
