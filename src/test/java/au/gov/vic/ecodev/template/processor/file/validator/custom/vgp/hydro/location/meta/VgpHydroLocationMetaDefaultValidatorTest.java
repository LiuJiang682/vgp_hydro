package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;

public class VgpHydroLocationMetaDefaultValidatorTest {

	private  VgpHydroLocationMetaDefaultValidator testInstance;
	private Template mockDataBean;
	
	@Test
	public void shouldReturnEmptyMessageWhenStringArrayIsNull() {
		// Given
		givenTestInstance();
		// When
		Optional<List<String>> messages = testInstance.validate(null, mockDataBean);
		// Then
		assertThat(messages.isPresent(), is(false));
		verify(mockDataBean, times(0)).put(Matchers.anyString(), Matchers.anyListOf(String.class));
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
		this.testInstance = new VgpHydroLocationMetaDefaultValidator();
		mockDataBean = Mockito.mock(Template.class);
	}
}
