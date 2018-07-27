package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;

public class MandatoryNumberDataValidatorTest {

	private MandatoryNumberDataValidator testInstance;
	
	@Test
	public void shouldReturnEmptyMessageWhenSampleTopIsProvided() {
		//Given
		givenTestInstance("Sample_Top");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(0)));
	}
	
	@Test
	public void shouldReturnMissingHeaderMessageWhenSampleIsProvided() {
		//Given
		givenTestInstance("Sample");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(1)));
		assertThat(messages.get(0), is(equalTo("ERROR: Line 0: Template vgpHydroSamplesMeta missing Sample column")));
	}
	
	@Test
	public void shouldReturnMissingDataMessageWhenCoreIdDataIsNotProvided() {
		//Given
		givenTestInstance("CORE ID");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(1)));
		assertThat(messages.get(0), is(equalTo("ERROR: Line 0: Template vgpHydroSamplesMeta column CORE ID must be a number, but got: ")));
	}
	
	@Test
	public void shouldReturnInvalidNumberMessageWhenLabCodeDataIsNotProvided() {
		//Given
		givenTestInstance("Lab_code");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(1)));
		assertThat(messages.get(0), is(equalTo("ERROR: Line 0: Template vgpHydroSamplesMeta column Lab_code must be a number, but got: GSV")));
	}
	
	@Test
	public void shouldReturnInstance() {
		//Given
		givenTestInstance("Sample_Top");
		//When
		//Then
		assertThat(testInstance, is(notNullValue()));
	}

	private void givenTestInstance(final String code) {
		testInstance = new MandatoryNumberDataValidator(TestFixture.getSamplesMetaData(), Strings.STRING_ZERO, 
				Arrays.asList(TestFixture.getSamplesMetaHeaders()), code, "vgpHydroSamplesMeta");
	}
	
}
