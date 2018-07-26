package au.gov.vic.ecodev.template.processor.custom.vgp.hydro.samples.meta;

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
import au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.samples.meta.VgpHydroSampleMetaFileParser;

@RunWith(PowerMockRunner.class)
@PrepareForTest(VgpHydroSamplesMetaProcessor.class)
public class VgpHydroSamplesMetaProcessorTest {

	private VgpHydroSamplesMetaProcessor testInstance;
	private VgpHydroSampleMetaFileParser mockFileParser;
	private TemplateProcessorContext mockContext;
	private File testDataFile;
	
	@Test
	public void shouldProcessFile() throws Exception {
		//Given
		givenTestInstance();
		testInstance.setFileList(givenTestFiles());
		testInstance.setTemplateProcessorContent(mockContext);
		PowerMockito.whenNew(VgpHydroSampleMetaFileParser.class).withArguments(eq(testDataFile), eq(mockContext))
			.thenReturn(mockFileParser);
		//When
		testInstance.processFile();
		//Then
		verify(mockFileParser).parse();
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenProcessFileFailed() throws Exception {
		//Given
		givenTestInstance();
		testInstance.setFileList(givenTestFiles());
		testInstance.setTemplateProcessorContent(mockContext);
		PowerMockito.whenNew(VgpHydroSampleMetaFileParser.class).withArguments(eq(testDataFile), eq(mockContext))
			.thenReturn(mockFileParser);
		PowerMockito.doThrow(new RuntimeException()).when(mockFileParser).parse();
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenFileIsNull() throws Exception {
		//Given
		givenTestInstance();
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenContextIsNull() throws Exception {
		//Given
		givenTestInstance();
		testInstance.setFileList(givenTestFiles());
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}

	private List<File> givenTestFiles() {
		List<File> files = new ArrayList<>();
		testDataFile = new File("src/test/resources/testData/Samp_20180726.txt");
		files.add(testDataFile);
		return files;
	}

	private void givenTestInstance() {
		testInstance = new VgpHydroSamplesMetaProcessor();
		mockFileParser = Mockito.mock(VgpHydroSampleMetaFileParser.class);
		mockContext = Mockito.mock(TemplateProcessorContext.class);
	}
}
