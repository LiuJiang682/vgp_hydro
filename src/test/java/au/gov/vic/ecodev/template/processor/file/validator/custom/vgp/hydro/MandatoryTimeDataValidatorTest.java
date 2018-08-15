package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;

public class MandatoryTimeDataValidatorTest {

	private MandatoryTimeDataValidator testInstance;
	private String[] strs;
	
	@Test
	public void shouldReturnEmptyMessageWhenCorrectTimeStringIsProvided() {
		//Given
		String timeFormat = "dd/MM/yyyy hh:mm";
		strs = TestFixture.getObservationsDatas();
		strs[Numerals.THREE] = "06/08/2018 18:00";
		givenTestInstance(timeFormat, "DATE/DATE_TIME");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(0)));
	}
	
	@Test
	public void shouldReturnInvalidTimeFormatMessageWhenInvalideTimeStringIsProvided() {
		//Given
		String timeFormat = "dd/MM/yyy hh:mm";
		strs = TestFixture.getObservationsDatas();
		strs[Numerals.THREE] = "abc";
		givenTestInstance(timeFormat, "DATE/DATE_TIME");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(1)));
		assertThat(messages.get(0), is(equalTo("ERROR: Line 0: Template template column DATE/DATE_TIME expected in dd/MM/yyy hh:mm format, but got: abc")));
	}
	
	@Test
	public void shouldReturnMissingSiteIdStringDataMessageWhenNoSiteIdIsProvided() {
		//Given
		String timeFormat = "dd/MM/yyy hh:mm";
		strs = TestFixture.getObservationsDatas();
		strs[Numerals.ZERO] = null;
		givenTestInstance(timeFormat, "SITE_ID");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(1)));
		assertThat(messages.get(0), is(equalTo("ERROR: Line 0: Template template column SITE_ID cannot be null or empty")));
	}
	
	@Test
	public void shouldReturnMissingHeaderMessageWhenHeaderIsNotProvided() {
		//Given
		String timeFormat = "dd/MM/yyy hh:mm";
		givenTestInstance(timeFormat, "SITE");
		List<String> messages = new ArrayList<>();
		//When
		testInstance.validate(messages);
		//Then
		assertThat(messages.size(), is(equalTo(1)));
		assertThat(messages.get(0), is(equalTo("ERROR: Line 0: Template template missing SITE column")));
	}
	
	@Test
	public void shouldReturnInstance() {
		//Given
		String timeFormat = "dd/MM/yyy hh:mm";
		givenTestInstance(timeFormat, "SITE_ID");
		//When
		//Then
		assertThat(testInstance, is(notNullValue()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenTimeFormatIsNull() {
		//Given
		String timeFormat = null;
		//When
		givenTestInstance(timeFormat, "SITE_ID");
		fail("Program reached unexpected point!");
	}

	private void givenTestInstance(final String timeFormat, final String code) {
		String lineNumber = "0";
		List<String> columnHeaders = Arrays.asList(TestFixture.getObservationHeaders());
		String templateName = "template";
		testInstance = new MandatoryTimeDataValidator(timeFormat, strs, lineNumber,
				columnHeaders, code, templateName);
	}
}
