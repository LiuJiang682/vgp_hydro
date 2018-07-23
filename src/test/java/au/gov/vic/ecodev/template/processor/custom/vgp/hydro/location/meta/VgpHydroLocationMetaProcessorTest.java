package au.gov.vic.ecodev.template.processor.custom.vgp.hydro.location.meta;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.location.meta.VgpHydroLocationMetaFileParser;

@RunWith(PowerMockRunner.class)
@PrepareForTest(VgpHydroLocationMetaProcessor.class)
public class VgpHydroLocationMetaProcessorTest {
	
	private TemplateProcessorContext mockTemplateProcessorContext;
	private File testDataFile;
	
	@Test
	public void shouldProcessFile() throws Exception {
		//Given
		VgpHydroLocationMetaProcessor testInstance = new VgpHydroLocationMetaProcessor();
		givenTestDataFile(testInstance);
		VgpHydroLocationMetaFileParser mockFileParser = Mockito.mock(VgpHydroLocationMetaFileParser.class);
		mockTemplateProcessorContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance.setTemplateProcessorContent(mockTemplateProcessorContext);
		PowerMockito.whenNew(VgpHydroLocationMetaFileParser.class).withArguments(eq(testDataFile), eq(mockTemplateProcessorContext))
			.thenReturn(mockFileParser);
		//When
		testInstance.processFile();
		//Then
		verify(mockFileParser).parse();
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenProcessFileWithParseFailed() throws Exception {
		//Given
		VgpHydroLocationMetaProcessor testInstance = new VgpHydroLocationMetaProcessor();
		givenTestDataFile(testInstance);
		VgpHydroLocationMetaFileParser mockFileParser = PowerMockito.mock(VgpHydroLocationMetaFileParser.class);
		PowerMockito.doThrow(new RuntimeException()).when(mockFileParser).parse();
		mockTemplateProcessorContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance.setTemplateProcessorContent(mockTemplateProcessorContext);
		PowerMockito.whenNew(VgpHydroLocationMetaFileParser.class).withArguments(eq(testDataFile), eq(mockTemplateProcessorContext))
			.thenReturn(mockFileParser);
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}

	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseTemplateProcessorExceptionWhenFileIsNull() throws TemplateProcessorException {
		//Given
		VgpHydroLocationMetaProcessor testInstance = new VgpHydroLocationMetaProcessor();
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseTemplateProcessorExceptionWhenContextIsNull() throws TemplateProcessorException {
		//Given
		VgpHydroLocationMetaProcessor testInstance = new VgpHydroLocationMetaProcessor();
		givenTestDataFile(testInstance);
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}

	private void givenTestDataFile(VgpHydroLocationMetaProcessor testInstance) {
		List<File> files = new ArrayList<>();
		testDataFile = new File("src/test/resources/hydro_data_03072018.txt");
		files.add(testDataFile);
		testInstance.setFileList(files);
	}
}
