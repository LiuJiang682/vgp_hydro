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
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroSamplesAnalysisTemplate;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroSamplesAnalysisDaoImpl;

public class VgpHydroSamplesAnalysisTemplateUpdaterTest {

	private VgpHydroSamplesAnalysisTemplateUpdater testInstance;
	private Template template;
	private VgpHydroSamplesAnalysisDaoImpl mockDao;
	
	@Test
	public void shouldUpdateDatabaseWithNewRecord() throws TemplateProcessorException {
		//Given
		givenTestInstance();
		List<String> headers = Arrays.asList(TestFixture.getSamplesAnalysisHeaders());
		template.put("1", headers);
		template.put("2", Arrays.asList(TestFixture.getSamplesMetaData()));
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
		testInstance = new VgpHydroSamplesAnalysisTemplateUpdater();
		//When
		testInstance.update(0, null);
		fail("Program reached unexpected point!");
	}
	
	@Test
	public void shouldSetDaos() {
		//Given
		testInstance = new VgpHydroSamplesAnalysisTemplateUpdater();
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
		testInstance = new VgpHydroSamplesAnalysisTemplateUpdater();
		//When
		List<Class<? extends Dao>> daos = testInstance.getDaoClasses();
		//Then
		assertThat(daos, is(notNullValue()));
		assertThat(daos.size(), is(equalTo(1)));
		assertThat(daos.get(0), is(equalTo(VgpHydroSamplesAnalysisDaoImpl.class)));
	}
	
	private void givenTestInstance() {
		mockDao = Mockito.mock(VgpHydroSamplesAnalysisDaoImpl.class);
		List<Dao> daos = new ArrayList<>();
		daos.add(mockDao);
		testInstance = new VgpHydroSamplesAnalysisTemplateUpdater();
		testInstance.setDaos(daos);
		template = new VgpHydroSamplesAnalysisTemplate();
	}
}
