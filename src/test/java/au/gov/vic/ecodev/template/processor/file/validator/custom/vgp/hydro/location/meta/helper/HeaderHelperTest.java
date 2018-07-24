package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta.helper;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HeaderHelperTest {
	
	private HeaderHelper testInstance;

	@Test
	public void shouldReturnTrueWhenSite_IDProvided() {
		//Given
		givenTestInstance();
		//When
		boolean isHeader = testInstance.isOneOfHeaders();
		//Then
		assertThat(isHeader, is(true));
	}
	
	@Test
	public void shouldReturnFalseWhenAbcProvided() {
		//Given
		testInstance = new HeaderHelper("abc");
		//When
		boolean isHeader = testInstance.isOneOfHeaders();
		//Then
		assertThat(isHeader, is(false));
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
		testInstance = new HeaderHelper("Site_ID");
	}
}
