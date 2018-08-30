package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Entity;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroObservationsTemplate;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroObservationsDaoImpl;

public class VgpHydroObservationsTemplateUpdaterTest {

	private VgpHydroObservationsTemplateUpdater testInstance;
	
	@Test
	public void shouldUpdateDatabaseWithNewRecord() throws TemplateProcessorException {
		//Given
		VgpHydroObservationsDaoImpl mockDao = Mockito.mock(VgpHydroObservationsDaoImpl.class);
		List<Dao> daos = new ArrayList<>();
		daos.add(mockDao);
		testInstance = new VgpHydroObservationsTemplateUpdater();
		testInstance.setDaos(daos);
		VgpHydroObservationsTemplate template = new VgpHydroObservationsTemplate();
		List<String> headers = Arrays.asList(TestFixture.getObservationHeaders());
		template.put("1", headers);
		template.put("2", Arrays.asList(TestFixture.getObservationsDatas()));
		template.put(Strings.CURRENT_FILE_NAME, Arrays.asList("myTest.txt"));
		//When
		testInstance.update(1, template);
		//Then
		ArgumentCaptor<Entity> entityCaptor = ArgumentCaptor.forClass(Entity.class);
		verify(mockDao).updateOrSave(entityCaptor.capture());
		assertThat(entityCaptor.getValue(), is(notNullValue()));
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenDaoListIsNull() throws TemplateProcessorException {
		//Given
		testInstance = new VgpHydroObservationsTemplateUpdater();
		//When
		testInstance.update(0, null);
		fail("Program reached unexpected point!");
	}
	
	@Test
	public void shouldSetDaos() {
		//Given
		testInstance = new VgpHydroObservationsTemplateUpdater();
		List<Dao> daos = new ArrayList<>();
		daos.add(Mockito.mock(Dao.class));
		//When
		testInstance.setDaos(daos);
		//Then
		List<Dao> retrieved = Whitebox.getInternalState(testInstance, "daos");
		assertThat(retrieved, is(equalTo(daos)));
	}
	
	@Test
	public void shouldReturnVgpHydroDaoClass() {
		//Given
		testInstance = new VgpHydroObservationsTemplateUpdater();
		//When
		List<Class<? extends Dao>> daos = testInstance.getDaoClasses();
		//Then
		assertThat(daos, is(notNullValue()));
		assertThat(daos.size(), is(equalTo(1)));
	}
}
