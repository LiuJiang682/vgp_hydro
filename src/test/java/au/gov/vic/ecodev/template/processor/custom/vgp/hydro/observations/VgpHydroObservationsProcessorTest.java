package au.gov.vic.ecodev.template.processor.custom.vgp.hydro.observations;

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
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.observations.VgpHydroObservationsFileParser;

@RunWith(PowerMockRunner.class)
@PrepareForTest(VgpHydroObservationsProcessor.class)
public class VgpHydroObservationsProcessorTest {

	private VgpHydroObservationsProcessor testInstance;
	private File testDataFile;
	private TemplateProcessorContext mockContext;
	private VgpHydroObservationsFileParser mockParser;
	
	@Test
	public void shouldProcessFile() throws Exception {
		//Given
		givenTestInstance();
		testInstance.setFileList(getTestFiles());
		mockContext = Mockito.mock(TemplateProcessorContext.class);
		testInstance.setTemplateProcessorContent(mockContext);
		mockParser = Mockito.mock(VgpHydroObservationsFileParser.class);
		PowerMockito.whenNew(VgpHydroObservationsFileParser.class)
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
		mockParser = Mockito.mock(VgpHydroObservationsFileParser.class);
		PowerMockito.whenNew(VgpHydroObservationsFileParser.class)
			.withArguments(eq(testDataFile), eq(mockContext)).thenReturn(mockParser);
		PowerMockito.doThrow(new RuntimeException()).when(mockParser).parse();
		//When
		testInstance.processFile();
		//Then
		verify(mockParser).parse();
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
		testDataFile = TestFixture.getObservationTestDataFile();
		files.add(testDataFile);
		return files;
	}

	private void givenTestInstance() {
		testInstance = new VgpHydroObservationsProcessor();
	}
}
