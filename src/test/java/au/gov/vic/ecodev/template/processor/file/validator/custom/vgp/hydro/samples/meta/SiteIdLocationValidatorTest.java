package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.SiteIdRepository;

public class SiteIdLocationValidatorTest {

	private SiteIdLocationValidator testInstance;
	
	@Test
	public void shouldReturnCorrectMessagesWhenAccordlyToSiteIdExistingOrNot() {
		//Given
		givenTestInstance();
		List<String> messages = new ArrayList<>();
		SiteIdRepository siteIdRepository = SiteIdRepository.INSTANCE;
		siteIdRepository.add("110098");
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.isEmpty(), is(true));
	}
	
	@Test
	public void shouldReturnErrorMessagesWhenSiteIdNoExisting() {
		//Given
		Whitebox.setInternalState(SiteIdRepository.INSTANCE, "siteIds", new ArrayList<>());
		givenTestInstance();
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.isEmpty(), is(false));
		assertThat(messages.size(), is(equalTo(1)));
		assertThat(messages.get(0), is(equalTo("ERROR: Line 0: Template vgpHydroSamplesMeta column SITE_ID is NO exist in Location Metadata table 110098")));
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
		testInstance = new SiteIdLocationValidator(TestFixture.getSamplesMetaData(), Strings.STRING_ZERO, 
				Arrays.asList(TestFixture.getSamplesMetaHeaders()), "vgpHydroSamplesMeta");
	}
}
