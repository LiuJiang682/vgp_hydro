package au.gov.vic.ecodev.template.processor.custom.vgp.hydro;

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
import au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.VgpHydroFileParser;

@RunWith(PowerMockRunner.class)
@PrepareForTest(VgpHydroCustomProcessor.class)
public class VgpHydroCutomProcessorTest {
	
	private TemplateProcessorContext mockTemplateProcessorContext;
	private File testDataFile;
	
	@Test
	public void shouldProcessFile() throws Exception {
		//Given
		VgpHydroCustomProcessor testInstance = new VgpHydroCustomProcessor();
		givenTestDataFile(testInstance);
		VgpHydroFileParser mockFileParser = Mockito.mock(VgpHydroFileParser.class);
		mockTemplateProcessorContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance.setTemplateProcessorContent(mockTemplateProcessorContext);
		PowerMockito.whenNew(VgpHydroFileParser.class).withArguments(eq(testDataFile), eq(mockTemplateProcessorContext))
			.thenReturn(mockFileParser);
		//When
		testInstance.processFile();
		//Then
		verify(mockFileParser).parse();
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenProcessFileWithParseFailed() throws Exception {
		//Given
		VgpHydroCustomProcessor testInstance = new VgpHydroCustomProcessor();
		givenTestDataFile(testInstance);
		VgpHydroFileParser mockFileParser = PowerMockito.mock(VgpHydroFileParser.class);
		PowerMockito.doThrow(new RuntimeException()).when(mockFileParser).parse();
		mockTemplateProcessorContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance.setTemplateProcessorContent(mockTemplateProcessorContext);
		PowerMockito.whenNew(VgpHydroFileParser.class).withArguments(eq(testDataFile), eq(mockTemplateProcessorContext))
			.thenReturn(mockFileParser);
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}

	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseTemplateProcessorExceptionWhenFileIsNull() throws TemplateProcessorException {
		//Given
		VgpHydroCustomProcessor testInstance = new VgpHydroCustomProcessor();
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseTemplateProcessorExceptionWhenContextIsNull() throws TemplateProcessorException {
		//Given
		VgpHydroCustomProcessor testInstance = new VgpHydroCustomProcessor();
		givenTestDataFile(testInstance);
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}

	private void givenTestDataFile(VgpHydroCustomProcessor testInstance) {
		List<File> files = new ArrayList<>();
		testDataFile = new File("src/test/resources/hydro_data_03072018.txt");
		files.add(testDataFile);
		testInstance.setFileList(files);
	}
}
