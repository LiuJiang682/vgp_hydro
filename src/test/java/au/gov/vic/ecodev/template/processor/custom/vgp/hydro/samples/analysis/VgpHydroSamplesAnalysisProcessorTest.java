package au.gov.vic.ecodev.template.processor.custom.vgp.hydro.samples.analysis;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
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
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.samples.analysis.VgpHydroSamplesAnaylsisFileParser;

@RunWith(PowerMockRunner.class)
@PrepareForTest(VgpHydroSamplesAnalysisProcessor.class)
public class VgpHydroSamplesAnalysisProcessorTest {

	private VgpHydroSamplesAnalysisProcessor testInstance;
	private File testDataFile;
	private VgpHydroSamplesAnaylsisFileParser mockParser;
	private TemplateProcessorContext mockContext;
	
	@Test
	public void shouldProcessFile() throws Exception {
		//Given
		givenTestInstance();
		testInstance.setFileList(getTestFiles());
		mockContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance.setTemplateProcessorContent(mockContext);
		mockParser = Mockito.mock(VgpHydroSamplesAnaylsisFileParser.class);
		PowerMockito.whenNew(VgpHydroSamplesAnaylsisFileParser.class)
			.withArguments(eq(testDataFile), eq(mockContext)).thenReturn(mockParser);
		//When
		testInstance.processFile();
		//Then
		verify(mockParser).parse();
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenProcessFileFailed() throws Exception {
		//Given
		givenTestInstance();
		testInstance.setFileList(getTestFiles());
		mockContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance.setTemplateProcessorContent(mockContext);
		mockParser = Mockito.mock(VgpHydroSamplesAnaylsisFileParser.class);
		PowerMockito.whenNew(VgpHydroSamplesAnaylsisFileParser.class)
			.withArguments(eq(testDataFile), eq(mockContext)).thenReturn(mockParser);
		PowerMockito.doThrow(new RuntimeException()).when(mockParser).parse();
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}

	@Test
	public void shouldSetTemplateProcessorContext() {
		//Given
		givenTestInstance();
		TemplateProcessorContext mockContext = Mockito.mock(TemplateProcessorContext.class);
		//When
		testInstance.setTemplateProcessorContent(mockContext);
		//Then
		TemplateProcessorContext retrievedTemplateProcessorContext = Whitebox.getInternalState(testInstance, "context");
		assertThat(retrievedTemplateProcessorContext, is(equalTo(mockContext)));
	}
	
	@Test
	public void shouldSetTheFileList() {
		//Given
		givenTestInstance();
		List<File> files = new ArrayList<>();
		//When
		testInstance.setFileList(files);
		//Then
		List<File> retrievedFiles = Whitebox.getInternalState(testInstance, "files");
		assertThat(retrievedFiles, is(equalTo(files)));
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenFileListIsNull() throws TemplateProcessorException {
		//Given
		givenTestInstance();
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenContextIsNull() throws TemplateProcessorException {
		//Given
		givenTestInstance();
		testInstance.setFileList(getTestFiles());
		//When
		testInstance.processFile();
		fail("Program reached unexpected point!");
	}

	private List<File> getTestFiles() {
		List<File> files = new ArrayList<>();
		testDataFile = new File("src/test/resources/testData/Samp_20180726.txt");
		files.add(testDataFile);
		return files;
	}
	
	private void givenTestInstance() {
		testInstance = new VgpHydroSamplesAnalysisProcessor();
	}
}
