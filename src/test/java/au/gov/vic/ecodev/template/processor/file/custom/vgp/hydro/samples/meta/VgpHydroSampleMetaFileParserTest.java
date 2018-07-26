package au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.samples.meta;

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
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroLocMetaTemplate;

public class VgpHydroSampleMetaFileParserTest {

	private VgpHydroSampleMetaFileParser testInstance;
	private File testDataFile;
	private TemplateProcessorContext mockContext;

	@Test
	public void shouldBuildTemplateFile() throws Exception {
		// Given
		givenTestInstance();
		when(mockContext.saveDataBean(Matchers.any(Template.class))).thenReturn(true);
		// When
		testInstance.parse();
		// Then
		ArgumentCaptor<Template> dataBeanCaptor = ArgumentCaptor.forClass(Template.class);
		verify(mockContext).saveDataBean(dataBeanCaptor.capture());
		Template capturedDataBean = dataBeanCaptor.getValue();
		assertThat(capturedDataBean, is(instanceOf(VgpHydroLocMetaTemplate.class)));
	}

	@Test
	public void shouldReturnInstance() {
		// Given
		givenTestInstance();
		// When
		// Then
		assertThat(testInstance, is(notNullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenTestDataFileIsNull() {
		// Given
		testDataFile = null;
		mockContext = null;
		// When
		new VgpHydroSampleMetaFileParser(testDataFile, mockContext);
		fail("Program reached unexpected point!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenContextIsNull() {
		// Given
		testDataFile = new File("src/test/resources/testData/Samp_20180726.txt");
		mockContext = null;
		// When
		new VgpHydroSampleMetaFileParser(testDataFile, mockContext);
		fail("Program reached unexpected point!");
	}

	private void givenTestInstance() {
		testDataFile = new File("src/test/resources/testData/Samp_20180726.txt");
		mockContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance = new VgpHydroSampleMetaFileParser(testDataFile, mockContext);
	}
}
