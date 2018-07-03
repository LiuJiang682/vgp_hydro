package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import java.util.List;

import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.mrt.template.processor.update.TemplateUpdater;

public class CustomTemplateUpdater implements TemplateUpdater {

	@Override
	public void setDaos(List<Dao> doas) {
		System.out.println("Inside CustomTemplateUpdater.setDaos");
	}

	@Override
	public void update(long sessionId, Template template) throws TemplateProcessorException {
		System.out.println("Inside CustomTemplateUpdater.update");
	}

	@Override
	public List<Class<? extends Dao>> getDaoClasses() {
		System.out.println("Inside CustomTemplateUpdater.getDaoClasses");
		return null;
	}

}
