package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleMeta;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.mrt.template.processor.update.TemplateUpdater;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroSamplesMetaDaoImpl;
import au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder.SampleMetaBuilder;

public class VgpHydroSamplesMetaTemplateUpdater implements TemplateUpdater {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroSamplesMetaTemplateUpdater.class);
	
	private List<Dao> daos;
	
	@Override
	public void setDaos(List<Dao> daos) {
		LOGGER.info("Inside VgpHydroSamplesMetaTemplateUpdater.setDaos");
		this.daos = daos;
	}

	@Override
	public void update(final long sessionId, final Template template) 
			throws TemplateProcessorException {
		LOGGER.info("Inside VgpHydroSamplesMetaTemplateUpdater.update");
		
		if (CollectionUtils.isEmpty(daos)) {
			throw new TemplateProcessorException("Dao list cannot be null or empty!");
		}
		
		List<String> headers = template.get(String.valueOf(Numerals.ONE));
		int len = template.getKeys().size();
		Dao dao = daos.get(Numerals.ZERO);
		for(int index = Numerals.TWO; index <= len; index++) {
			List<String> datas = template.get(String.valueOf(index));
			SampleMeta sampleMeta = new SampleMetaBuilder(sessionId, headers, datas).build();
			dao.updateOrSave(sampleMeta);
		}
	}
	
	@Override
	public List<Class<? extends Dao>> getDaoClasses() {
		LOGGER.info("Inside VgpHydroSamplesMetaTemplateUpdater.getDaoClasses");
		List<Class<? extends Dao>> daoClasses = new ArrayList<>();
		daoClasses.add(VgpHydroSamplesMetaDaoImpl.class);
		return daoClasses;
	}

}
