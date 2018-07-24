package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.mockito.Mockito;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;

public class VgpHydroLocationMetaValidatorFactoryTest {
	
	private TemplateProcessorContext mockTemplateProcessorContext;
	
	private VgpHydroLocationMetaValidatorFactory testInstance;

	@Test
	public void shouldReturnHeaderValidator() throws Exception {
		//Given
		givenTestInstance();
		//When
		Validator validator = testInstance.getLineValidator("Site_ID\tUWI\tLocal_Name\tLocation_Desc\tState\tGrid/Zone\tEasting\tNorthing\tDatum\tLatitude\tLongitude\tKB\tElevation\tBore Diameter\tTD\tTVD\tDepth Datum");
		//Then
		assertThat(validator, is(notNullValue()));
		assertThat(validator, is(instanceOf(VgpHydroLocationMetaHeaderValidator.class)));
	}
	
	@Test
	public void shouldReturnDataValidator() throws Exception {
		//Given
		givenTestInstance();
		//When
		Validator validator = testInstance.getLineValidator("102621\t\t\t\t\tMGA 54\t636758.5\t5747711.5\t\t\t\t\t11.88\t100\t\t\t");
		//Then
		assertThat(validator, is(notNullValue()));
		assertThat(validator, is(instanceOf(VgpHydroLocationMetaDataValidator.class)));
	}
	
	@Test
	public void shouldReturnDefaultValidator() throws Exception {
		//Given
		givenTestInstance();
		//When
		Validator validator = testInstance.getLineValidator("");
		//Then
		assertThat(validator, is(notNullValue()));
		assertThat(validator, is(instanceOf(VgpHydroLocationMetaDefaultValidator.class)));
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
		//When
		new VgpHydroLocationMetaValidatorFactory(templateProcessorContext);
		fail("Program reached unexpected point!");
	}

	private void givenTestInstance() {
		mockTemplateProcessorContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance = new VgpHydroLocationMetaValidatorFactory(mockTemplateProcessorContext);
	}
}
