package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;

public class VgpHydroLocationMetaValidatorFactoryTest {
	
	private VgpHydroLocationMetaValidatorFactory testInstance;

	@Test
	public void shouldReturnHeaderValidator() throws Exception {
		//Given
		givenTestInstance();
		//When
		Validator validator = testInstance.getLineValidator(TestFixture.VGP_HYDRO_LOC_META_HEADERS);
		//Then
		assertThat(validator, is(notNullValue()));
		assertThat(validator, is(instanceOf(VgpHydroLocationMetaHeaderValidator.class)));
		String[] strs = Whitebox.getInternalState(validator, "strs");
		assertThat(strs, is(notNullValue()));
		assertThat(strs.length, is(equalTo(17)));
		assertThat(strs[Numerals.ZERO], is(equalTo("Site_ID")));
	}
	
	@Test
	public void shouldReturnDataValidator() throws Exception {
		//Given
		givenTestInstance();
		//When
		Validator validator = testInstance.getLineValidator(TestFixture.VGP_HYDRO_LOC_META_DATA_TEST);
		//Then
		assertThat(validator, is(notNullValue()));
		assertThat(validator, is(instanceOf(VgpHydroLocationMetaDataValidator.class)));
		String[] strs = Whitebox.getInternalState(validator, "strs");
		assertThat(strs, is(notNullValue()));
		assertThat(strs.length, is(equalTo(14)));
		assertThat(strs[Numerals.ZERO], is(equalTo("102621")));
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

	private void givenTestInstance() {
		testInstance = new VgpHydroLocationMetaValidatorFactory();
	}
}
