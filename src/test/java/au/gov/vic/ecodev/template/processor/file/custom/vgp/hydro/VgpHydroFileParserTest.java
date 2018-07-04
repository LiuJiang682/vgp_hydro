package au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.processor.context.properties.StringTemplateProperties;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroTemplate;

public class VgpHydroFileParserTest {

	private TemplateProcessorContext mockTemplateProcessorContext;
	private File testDataFile;
	
	private VgpHydroFileParser testInstance;
	
	@Test
	public void shouldBuildTemplateFile() throws Exception {
		//Given
		givenTestInstance();
		StringTemplateProperties mockTemplateProperties = Mockito.mock(StringTemplateProperties.class);
		when(mockTemplateProperties.getValue()).thenReturn("H0202");
		when(mockTemplateProcessorContext.getTemplateContextProperty(eq("VGPHYDRO:MANDATORY.VALIDATE.FIELDS")))
			.thenReturn(mockTemplateProperties);
		when(mockTemplateProcessorContext.saveDataBean(Matchers.any(Template.class))).thenReturn(true);
		//When
		testInstance.parse();
		//Then
		ArgumentCaptor<Template> dataBeanCaptor = ArgumentCaptor.forClass(Template.class);
		verify(mockTemplateProcessorContext).saveDataBean(dataBeanCaptor.capture());
		Template capturedDataBean = dataBeanCaptor.getValue();
		assertThat(capturedDataBean, is(instanceOf(VgpHydroTemplate.class)));
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
		File testDataFile = null;
		TemplateProcessorContext templateProcessorContext = null;
		//When
		new VgpHydroFileParser(testDataFile, templateProcessorContext);
		fail("Program reached unexpected point!");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenContextIsNull() {
		//Given
		File file = new File("src/test/resources/testData/hydro_data_03072018.txt");
		TemplateProcessorContext templateProcessorContext = null;
		//When
		new VgpHydroFileParser(file, templateProcessorContext);
		fail("Program reached unexpected point!");
	}

	private void givenTestInstance() {
		testDataFile = new File("src/test/resources/testData/hydro_data_03072018.txt");
		mockTemplateProcessorContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance = new VgpHydroFileParser(testDataFile, mockTemplateProcessorContext);
	}
}
