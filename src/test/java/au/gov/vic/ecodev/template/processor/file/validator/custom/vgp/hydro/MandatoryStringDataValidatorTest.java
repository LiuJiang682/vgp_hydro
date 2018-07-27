package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;

public class MandatoryStringDataValidatorTest {

	private MandatoryStringDataValidator testInstance;
	
	@Test
	public void shouldReturnEmptyMessageWhenSiteIDIsProvided() {
		//Given
		givenTestInstance("Site_ID");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(0)));
	}
	
	@Test
	public void shouldReturnEmptyMessageWhenSiteIDUCIsProvided() {
		//Given
		givenTestInstance("SITE_ID");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(0)));
	}
	
	@Test
	public void shouldReturnMissingHeaderMessageWhenSiteIsProvided() {
		//Given
		givenTestInstance("SITE");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(1)));
		assertThat(messages.get(0), is(equalTo("ERROR: Line 0: Template vgpHydroLocMeta missing SITE column")));
	}
	
	@Test
	public void shouldReturnMissingDepthDatumHeaderMessageWhenNoDepthDatumIsProvided() {
		//Given
		givenTestInstance("Depth Datum");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(1)));
		assertThat(messages.get(0), is(equalTo("ERROR: Line 0: Template vgpHydroLocMeta column Depth Datum cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnInstance() {
		//Given
		givenTestInstance("Site_ID");
		//When
		//Then
		assertThat(testInstance, is(notNullValue()));
	}

	private void givenTestInstance(final String code) {
		String[] strs = {"abc", "123", "def"};
		List<String> headers = Arrays.asList(TestFixture.getLocMetaHeaders());
		testInstance = new MandatoryStringDataValidator(strs, "0", headers, code, "vgpHydroLocMeta");
	}
}
