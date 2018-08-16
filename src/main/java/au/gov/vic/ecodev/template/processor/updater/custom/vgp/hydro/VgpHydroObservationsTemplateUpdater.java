package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.Observation;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.mrt.template.processor.update.TemplateUpdater;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroObservationsDaoImpl;
import au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder.ObservationBuilder;

public class VgpHydroObservationsTemplateUpdater implements TemplateUpdater {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroObservationsTemplateUpdater.class);
	
	private List<Dao> daos;
	
	@Override
	public void setDaos(List<Dao> daos) {
		LOGGER.info("Inside VgpHydroObservationsTemplateUpdater.setDaos");
		this.daos = daos;
	}

	@Override
	public void update(long sessionId, Template template) throws TemplateProcessorException {
		LOGGER.info("Inside VgpHydroObservationsTemplateUpdater.update");
		
		if (CollectionUtils.isEmpty(daos)) {
			throw new TemplateProcessorException("VgpHydroObservationsTemplateUpdater -- daos list cannot be null or empty!");
		}
		
		List<String> headers = template.get(String.valueOf(Numerals.ONE));
		int len = template.getKeys().size();
		Dao dao = daos.get(Numerals.ZERO);
		for(int index = Numerals.TWO; index <= len; index++) {
			List<String> datas = template.get(String.valueOf(index));
			Observation observation = new ObservationBuilder(sessionId, headers, datas)
					.build();
			dao.updateOrSave(observation);
		}
	}

	@Override
	public List<Class<? extends Dao>> getDaoClasses() {
		LOGGER.info("Inside VgpHydroObservationsTemplateUpdater.getDaoClasses");
		List<Class<? extends Dao>> daoClasses = new ArrayList<>();
		daoClasses.add(VgpHydroObservationsDaoImpl.class);
		return daoClasses;
	}

}
