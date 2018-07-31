package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

public class SiteIdRepositoryTest {

	@AfterClass
	public static void tearDownClass() {
		Whitebox.setInternalState(SiteIdRepository.INSTANCE, "siteIds", new ArrayList<>());
	}
	
	@Test
	public void shouldAddEntryToRepository() {
		//Given
		SiteIdRepository testInstance = SiteIdRepository.INSTANCE; 
		//When
		testInstance.add("siteId");
		//Then
		List<String> siteIds = Whitebox.getInternalState(testInstance, "siteIds");
		assertThat(siteIds, is(notNullValue()));
		assertThat(siteIds.size(), is(equalTo(1)));
	}
	
	@Test
	public void shouldReturnInstance() {
		//Given
		SiteIdRepository testInstance = SiteIdRepository.INSTANCE; 
		//When
		//Then
		assertThat(testInstance, is(notNullValue()));
	}
}
