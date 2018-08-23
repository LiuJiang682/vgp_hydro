package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;

public class FirstNotEmptyStringHelperTest {

	private FirstNotEmptyStringHelper testInstance;
	
	@Test
	public void shouldReturnFirstNotEmptyString() throws Exception {
		//Given
		givenTestInstance();
		//When
		String string = testInstance.getFirstNotEmptyString();
		//Then
		assertThat(string, is(notNullValue()));
		assertThat(string, is(equalTo("abc")));
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenEmptyArrayProvided() throws Exception {
		//Given
		String[] strs = {"", null, null};
		testInstance = new FirstNotEmptyStringHelper(strs);
		//When
		testInstance.getFirstNotEmptyString();
		fail("Program reached unexpected point!");
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
		String[] strs = {null, "abc", "123"};
		testInstance = new FirstNotEmptyStringHelper(strs);
	}
}
