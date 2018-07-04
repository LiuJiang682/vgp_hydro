package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.gov.vic.ecodev.common.util.IDGenerator;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.mrt.template.processor.update.TemplateUpdater;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroEntity;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroDaoImpl;

public class VgpHydroTemplateUpdater implements TemplateUpdater {
	
	private static final Logger LOGGER = Logger.getLogger(VgpHydroTemplateUpdater.class);
	
	private List<Dao> daos;

	@Override
	public void setDaos(List<Dao> daos) {
		LOGGER.info("Inside CustomTemplateUpdater.setDaos");
		this.daos = daos;
	}

	@Override
	public void update(long sessionId, Template template) throws TemplateProcessorException {
		LOGGER.info("Inside CustomTemplateUpdater.update");
		Dao dao = daos.get(0);
		VgpHydroEntity entity = new VgpHydroEntity();
		entity.setId(IDGenerator.getUID().longValue());
		entity.setLoaderId(sessionId);
		dao.updateOrSave(entity);
	}

	@Override
	public List<Class<? extends Dao>> getDaoClasses() {
		LOGGER.info("Inside CustomTemplateUpdater.getDaoClasses");
		List<Class<? extends Dao>> daoClasses = new ArrayList<>();
		daoClasses.add(VgpHydroDaoImpl.class);
		return daoClasses;
	}

}
