package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.helper;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

import org.junit.Test;

import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;

public class HeaderHelperTest {

	private HeaderHelper testInstance;
	
	@Test
	public void shouldReturnTrueWhenSiteIdIsProvided() {
		//Given
		givenTestInstance();
		//When
		boolean flag = testInstance.isOneOfHeaders();
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnIndexOfIgsn() {
		//Given
		List<String> headers = Arrays.asList(TestFixture.getSamplesMetaHeaders());
		String fieldName = "IGSN";
		//When
		OptionalInt indexOpt = new HeaderHelper(fieldName).findHeaderIndex(headers);
		//Then
		assertThat(indexOpt.isPresent(), is(true));
		assertThat(indexOpt.getAsInt(), is(equalTo(7)));
	}
	
	@Test
	public void shouldReturnFalseWhenAbcIsProvided() {
		//Given
		testInstance = new HeaderHelper("abc");
		//When
		boolean flag = testInstance.isOneOfHeaders();
		//Then
		assertThat(flag, is(false));
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
		testInstance = new HeaderHelper("SITE_ID");
	}
}
