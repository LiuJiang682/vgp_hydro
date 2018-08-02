package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HeaderMatcherTest {

	private HeaderMatcher testInstance;
	
	@Test
	public void shouldReturnTrueWhenHeaderSiteIdProvided() {
		//Given
		givenTestInstance("SITE_ID");
		//When
		boolean flag = testInstance.isOneOfHeaders();
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnFalseWhenHeaderAbcProvided() {
		//Given
		givenTestInstance("ABC");
		//When
		boolean flag = testInstance.isOneOfHeaders();
		//Then
		assertThat(flag, is(false));
	}
	
	@Test
	public void shouldReturnInstance() {
		//Given
		givenTestInstance("SITE_ID");
		//When
		//Then
		assertThat(testInstance, is(notNullValue()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public  void shouldRaiseExceptionWhenHeaderIsNull() {
		//Given
		String header = null;
		List<String> headers = null;
		//When
		new HeaderMatcher(header, headers);
		fail("Program reached unexpected point!");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public  void shouldRaiseExceptionWhenHeadersAreNull() {
		//Given
		String header = "SITE_ID";
		List<String> headers = null;
		//When
		new HeaderMatcher(header, headers);
		fail("Program reached unexpected point!");
	}

	private void givenTestInstance(final String header) {
		List<String> headers = Arrays.asList("SITE_ID", "IGSN");
		testInstance = new HeaderMatcher(header, headers);
	}
}
