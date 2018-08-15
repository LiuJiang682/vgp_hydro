package au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.observations;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.samples.analysis.VgpHydroSamplesAnaylsisFileParser;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroObservationsTemplate;

public class VgpHydroObservationsFileParserTest {

	private VgpHydroObservationsFileParser testInstance;
	private File testDataFile;
	private TemplateProcessorContext mockContext;
	
	@Test
	public void shouldBuildTemplateFile() throws Exception {
		//Given
		givenTestInstance();
		when(mockContext.saveDataBean(Matchers.any(Template.class))).thenReturn(true);
		//When
		testInstance.parse();
		//Then
		ArgumentCaptor<Template> dataBeanCaptor = ArgumentCaptor.forClass(Template.class);
		verify(mockContext).saveDataBean(dataBeanCaptor.capture());
		Template capturedDataBean = dataBeanCaptor.getValue();
		assertThat(capturedDataBean, is(instanceOf(VgpHydroObservationsTemplate.class)));
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
	public void shouldRaiseExceptionWhenDataFileIsNull() {
		//Given
		File file = null;
		TemplateProcessorContext context = null;
		//When
		new VgpHydroObservationsFileParser(file, context);
		fail("Program reached unexpected point!");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenContextIsNull() {
		//Given
		File file = TestFixture.getObservationTestDataFile();
		TemplateProcessorContext context = null;
		//When
		new VgpHydroSamplesAnaylsisFileParser(file, context);
		fail("Program reached unexpected point!");
	}

	private void givenTestInstance() {
		testDataFile =  TestFixture.getObservationTestDataFile();
		mockContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance = new VgpHydroObservationsFileParser(testDataFile, mockContext);
	}
}
