package au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.samples.meta;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroSamplesMetaTemplate;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.SiteIdRepository;

public class VgpHydroSampleMetaFileParserTest {

	private VgpHydroSampleMetaFileParser testInstance;
	private File testDataFile;
	private TemplateProcessorContext mockContext;
	
	@BeforeClass
	public static void setUpSiteIdRepository() {
		SiteIdRepository siteIdRepository = SiteIdRepository.INSTANCE;
		siteIdRepository.add("141912");
		siteIdRepository.add("104929");
		siteIdRepository.add("108904");
		siteIdRepository.add("108932");
	}
	
	@AfterClass
	public static void tearDownSiteIdRepository() {
		Whitebox.setInternalState(SiteIdRepository.INSTANCE, "siteIds", new ArrayList<>());
	}

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
		assertThat(capturedDataBean, is(instanceOf(VgpHydroSamplesMetaTemplate.class)));
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
		testDataFile = new File("src/test/resources/testData/Samp_20180730.txt");
		mockContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance = new VgpHydroSampleMetaFileParser(testDataFile, mockContext);
	}
}
