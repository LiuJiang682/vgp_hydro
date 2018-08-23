package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.VgpHydroDefaultValidator;

public class VgpHydroSamplesMetaValidatorFactoryTest {

	private VgpHydroSamplesMetaValidatorFactory testInstance;
	
	@Test
	public void shouldReturnHeaderValidator() throws Exception {
		//Given
		givenTestInstance();
		//When
		Validator validator = testInstance.getLineValidator(TestFixture.VGP_HYDRO_SAMPLES_META_HEADER);
		//Then
		assertThat(validator, is(notNullValue()));
		assertThat(validator, is(instanceOf(VgpHydroSamplesMetaHeaderValidator.class)));
	}
	
	@Test
	public void shouldReturnDataValidator() throws Exception {
		//Given
		givenTestInstance();
		//When
		Validator validator = testInstance.getLineValidator(TestFixture.VGP_HYDRO_SAMPLES_META_DATA);
		//Then
		assertThat(validator, is(notNullValue()));
		assertThat(validator, is(instanceOf(VgpHydroSamplesMetaDataValidator.class)));
	}
	
	@Test
	public void shouldReturnDefaultValidator() throws Exception {
		//Given
		givenTestInstance();
		//When
		Validator validator = testInstance.getLineValidator(null);
		//Then
		assertThat(validator, is(notNullValue()));
		assertThat(validator, is(instanceOf(VgpHydroDefaultValidator.class)));
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
		testInstance = new VgpHydroSamplesMetaValidatorFactory();
	}
}
