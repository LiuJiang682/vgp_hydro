package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.mrt.template.processor.update.TemplateUpdater;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroSamplesMetaDaoImpl;

public class VgpHydroSamplesMetaTemplateUpdater implements TemplateUpdater {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroSamplesMetaTemplateUpdater.class);
	
	private List<Dao> daos;
	
	@Override
	public void setDaos(List<Dao> daos) {
		LOGGER.info("Inside VgpHydroSamplesMetaTemplateUpdater.setDaos");
		this.daos = daos;
	}

	@Override
	public void update(long sessionId, Template template) throws TemplateProcessorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Class<? extends Dao>> getDaoClasses() {
		LOGGER.info("Inside VgpHydroSamplesMetaTemplateUpdater.getDaoClasses");
		List<Class<? extends Dao>> daoClasses = new ArrayList<>();
		daoClasses.add(VgpHydroSamplesMetaDaoImpl.class);
		return daoClasses;
	}

}