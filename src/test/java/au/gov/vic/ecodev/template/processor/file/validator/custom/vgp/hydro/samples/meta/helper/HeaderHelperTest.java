package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.helper;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

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
