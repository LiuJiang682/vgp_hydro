package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroSamplesMetaDaoImpl;

public class VgpHydroSamplesMetaTemplateUpdaterTest {

	@Test(expected=TemplateProcessorException.class)
	public void shouldRaiseExceptionWhenDaoListIsNull() throws TemplateProcessorException {
		//Given
		VgpHydroSamplesMetaTemplateUpdater testInstance = new VgpHydroSamplesMetaTemplateUpdater();
		//When
		testInstance.update(0, null);
		fail("Program reached unexpected point!");
	}
	
	@Test
	public void shouldReturnVgpHydroDaoClass() {
		//Given
		VgpHydroSamplesMetaTemplateUpdater testInstance = new VgpHydroSamplesMetaTemplateUpdater();
		//When
		List<Class<? extends Dao>> daos = testInstance.getDaoClasses();
		//Then
		assertThat(daos, is(notNullValue()));
		assertThat(daos.size(), is(equalTo(1)));
		assertThat(daos.get(0), is(equalTo(VgpHydroSamplesMetaDaoImpl.class)));
	}
}
