package au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.samples.analysis;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroSamplesAnalysisTemplate;

public class VgpHydroSamplesAnalysisFileParserTest {

	private VgpHydroSamplesAnaylsisFileParser testInstance;
	private File testDataFile;
	private TemplateProcessorContext mockContext;
	
	@Test
	public void shouldBuildTemplateFile() {
		//Given
		givenTestInstance();
		//When
		testInstance.parse();
		//Then
		ArgumentCaptor<Template> dataBeanCaptor = ArgumentCaptor.forClass(Template.class);
		verify(mockContext).saveDataBean(dataBeanCaptor.capture());
		Template capturedDataBean = dataBeanCaptor.getValue();
		assertThat(capturedDataBean, is(instanceOf(VgpHydroSamplesAnalysisTemplate.class)));
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
		new VgpHydroSamplesAnaylsisFileParser(file, context);
		fail("Program reached unexpected point!");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenContextIsNull() {
		//Given
		File file = new File("src/test/resources/testData/Anal_20180802.txt");
		TemplateProcessorContext context = null;
		//When
		new VgpHydroSamplesAnaylsisFileParser(file, context);
		fail("Program reached unexpected point!");
	}

	private void givenTestInstance() {
		testDataFile = new File("src/test/resources/testData/Anal_20180802.txt");
		mockContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance = new VgpHydroSamplesAnaylsisFileParser(testDataFile, mockContext);
	}
}
