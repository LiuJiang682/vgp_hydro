package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroDaoImpl;

public class VgpHydroTemplateUpdaterTest {

	@Test
	public void shouldReturnVgpHydroDaoClass() {
		//Given
		VgpHydroTemplateUpdater testInstance = new VgpHydroTemplateUpdater();
		//When
		List<Class<? extends Dao>> daos = testInstance.getDaoClasses();
		assertThat(daos, is(notNullValue()));
		assertThat(daos.size(), is(equalTo(1)));
		assertThat(daos.get(0), is(equalTo(VgpHydroDaoImpl.class)));
	}
}
