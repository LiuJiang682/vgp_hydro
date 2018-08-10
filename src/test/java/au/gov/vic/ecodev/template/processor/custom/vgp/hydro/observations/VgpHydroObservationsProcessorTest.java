package au.gov.vic.ecodev.template.processor.custom.vgp.hydro.observations;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;

public class VgpHydroObservationsProcessorTest {

	private VgpHydroObservationsProcessor testInstance;
	
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

	private void givenTestInstance() {
		testInstance = new VgpHydroObservationsProcessor();
	}
}
