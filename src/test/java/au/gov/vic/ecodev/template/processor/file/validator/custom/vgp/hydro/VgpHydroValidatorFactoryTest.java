package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;

public class VgpHydroValidatorFactoryTest {
	
	private TemplateProcessorContext mockTemplateProcessorContext;
	private List<String> mandatoryFields;
	
	private VgpHydroValidatorFactory testInstance;

	@Test
	public void shouldReturnAValidator() throws Exception {
		//Given
		givenTestInstance();
		//When
		Validator validator = testInstance.getLineValidator("");
		//Then
		assertThat(validator, is(notNullValue()));
	}
	
	@Test
	public void shouldReturnInstance() {
		//Given
		givenTestInstance();
		//When
		//Then
		assertThat(testInstance, is(notNullValue()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenTemplateProcessorContextIsNull() {
		//Given
		TemplateProcessorContext templateProcessorContext = null;
		List<String> fields = null;
		//When
		new VgpHydroValidatorFactory(templateProcessorContext, fields);
		fail("Program reached unexpected point!");
	}

	private void givenTestInstance() {
		mockTemplateProcessorContext = Mockito.mock(TemplateProcessorContext.class);
		mandatoryFields = new ArrayList<>();
		testInstance = new VgpHydroValidatorFactory(mockTemplateProcessorContext, mandatoryFields);
	}
}
