package au.gov.vic.ecodev.template.file.custom.vgp.hydro;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import au.gov.vic.ecodev.utils.file.DirectoryFilesScanner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FileNameTemplateFileSelector.class})
public class FileNameTemplateFileSelectorTest {

	private FileNameTemplateFileSelector testInstance;
	
	@Test
	public void shouldReturnLocationMetaFileWhenCorrectFileNameProvided() throws Exception {
		//Given
		givenTestInstance();
		List<String> dataTemplate = Arrays.asList("LOC");
		testInstance.setSelectionFileDirectory("src/test/resources/testData/");
		//When
		Optional<List<String>> templateFileName = testInstance.getTemplateFileInDirectory(dataTemplate);
		//Then
		assertThat(templateFileName.isPresent(), is(true));
		List<String> fileNames = templateFileName.get();
		assertThat(CollectionUtils.isNotEmpty(fileNames), is(true));
		assertThat(fileNames.size(), is(equalTo(2)));
	}
	
	@Test
	public void shouldReturnEmptyOptionWhenEmptyDirectoryProvided() throws Exception {
		//Given
		givenTestInstance();
		List<String> dataTemplate = Arrays.asList("LOC");
		testInstance.setSelectionFileDirectory("src/test/resources/testData/");
		DirectoryFilesScanner mockScanner = PowerMockito.mock(DirectoryFilesScanner.class);
		when(mockScanner.scan()).thenReturn(new ArrayList<>());
		PowerMockito.whenNew(DirectoryFilesScanner.class).withArguments(eq("src/test/resources/testData/"))
			.thenReturn(mockScanner);
		//When
		Optional<List<String>> templateFileName = testInstance.getTemplateFileInDirectory(dataTemplate);
		//Then
		assertThat(templateFileName.isPresent(), is(false));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenDirectoryIsNull() throws Exception {
		//Given
		givenTestInstance();
		List<String> dataTemplate = Arrays.asList("vgphydro");
		//When
		testInstance.getTemplateFileInDirectory(dataTemplate);
		fail("Program reached unexpected point!");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenDataTemplateListIsNull() throws Exception {
		//Given
		givenTestInstance();
		//When
		testInstance.getTemplateFileInDirectory(null);
		fail("Program reached unexpected point!");
	}
	
	@Test
	public void shouldReturnInstance() {
		//Given
		givenTestInstance();
		//When
		//Then
		assertThat(testInstance, is(notNullValue()));
	}

	private void givenTestInstance() {
		this.testInstance = new FileNameTemplateFileSelector();
	}
}
