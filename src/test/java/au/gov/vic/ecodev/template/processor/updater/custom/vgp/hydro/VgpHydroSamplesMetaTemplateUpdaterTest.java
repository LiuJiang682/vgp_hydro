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

import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Entity;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.template.processor.custom.vgp.hydro.TestFixture;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroSamplesMetaTemplate;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroSamplesMetaDaoImpl;

public class VgpHydroSamplesMetaTemplateUpdaterTest {

	private VgpHydroSamplesMetaTemplateUpdater testInstance;
	private Template template;
	private VgpHydroSamplesMetaDaoImpl  mockDao;
	
	
	@Test
	public void shouldUpdateDatabaseWithNewRecord() throws TemplateProcessorException {
		//Given
		givenTestInstance();
		List<String> headers = Arrays.asList(TestFixture.getSamplesMetaHeaders());
		template.put("1", headers);
		template.put("2", Arrays.asList(TestFixture.getSamplesMetaData()));
		//When
		testInstance.update(1, template);
		//Then
		ArgumentCaptor<Entity> entityCaptor = ArgumentCaptor.forClass(Entity.class);
		verify(mockDao).updateOrSave(entityCaptor.capture());
		assertThat(entityCaptor.getValue(), is(notNullValue()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenTemplateHasNotHeader() throws TemplateProcessorException {
		//Given
		givenTestInstance();
		template.put("2", Arrays.asList(TestFixture.getSamplesMetaData()));
		template.put("3", Arrays.asList(TestFixture.getSamplesMetaData()));
		//When
		testInstance.update(0, template);
		fail("Program reached unexpected point!");
	}
	
	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenDaoListIsNull() throws TemplateProcessorException {
		//Given
		testInstance = new VgpHydroSamplesMetaTemplateUpdater();
		//When
		testInstance.update(0, null);
		fail("Program reached unexpected point!");
	}
	
	@Test
	public void shouldReturnVgpHydroDaoClass() {
		//Given
		testInstance = new VgpHydroSamplesMetaTemplateUpdater();
		//When
		List<Class<? extends Dao>> daos = testInstance.getDaoClasses();
		//Then
		assertThat(daos, is(notNullValue()));
		assertThat(daos.size(), is(equalTo(1)));
		assertThat(daos.get(0), is(equalTo(VgpHydroSamplesMetaDaoImpl.class)));
	}
	
	private void givenTestInstance() {
		mockDao = Mockito.mock(VgpHydroSamplesMetaDaoImpl.class);
		List<Dao> daos = new ArrayList<>();
		daos.add(mockDao);
		testInstance = new VgpHydroSamplesMetaTemplateUpdater();
		testInstance.setDaos(daos);
		template = new VgpHydroSamplesMetaTemplate();
	}
}
