package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroTemplateValue;

public class VgpHydroLocationMetaDefaultValidatorTest {

	private  VgpHydroLocationMetaDefaultValidator testInstance;
	private Template mockDataBean;
	private Map<String, List<String>> params;
	
	@Test
	public void shouldReturnEmptyMessageWhenStringArrayIsPopulated() {
		//Given
		givenTestInstance();
		String[] strs = {"abc", "123"};
		testInstance.init(strs);
		params.put(Strings.CURRENT_LINE, Arrays.asList("6"));
		//When
		Optional<List<String>> messages = testInstance.validate(params, mockDataBean);
		//Then
		assertThat(messages.isPresent(), is(false));
		ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<VgpHydroTemplateValue> valueCaptor = ArgumentCaptor.forClass(VgpHydroTemplateValue.class);
		verify(mockDataBean).put(keyCaptor.capture(), valueCaptor.capture());
		assertThat(keyCaptor.getValue(), is(equalTo("6")));
		VgpHydroTemplateValue values = valueCaptor.getValue();
		assertThat(values, is(notNullValue()));
		List<String> datas = values.getDatas();
		assertThat(datas.isEmpty(), is(false));
		assertThat(datas.size(), is(equalTo(2)));
		assertThat(datas.get(0), is(equalTo("abc")));
		assertThat(datas.get(1), is(equalTo("123")));
	}
	
	@Test
	public void shouldReturnEmptyMessageWhenStringArrayIsEmpty() {
		//Given
		givenTestInstance();
		String[] strs = {};
		testInstance.init(strs);
		//When
		Optional<List<String>> messages = testInstance.validate(params, mockDataBean);
		//Then
		assertThat(messages.isPresent(), is(false));
	}
	
	@Test
	public void shouldReturnEmptyMessageWhenStringArrayIsNull() {
		// Given
		givenTestInstance();
		// When
		Optional<List<String>> messages = testInstance.validate(params, mockDataBean);
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
		params = new HashMap<>();
	}
}
