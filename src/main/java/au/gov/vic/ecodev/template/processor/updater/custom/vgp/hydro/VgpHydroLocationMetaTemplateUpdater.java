package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.gov.vic.ecodev.common.util.IDGenerator;
import au.gov.vic.ecodev.mrt.model.sl4.Site;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.mrt.template.processor.update.TemplateUpdater;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroEntity;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroTemplateValue;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroLocationMetaDaoImpl;

public class VgpHydroLocationMetaTemplateUpdater implements TemplateUpdater {
	
	private static final Logger LOGGER = Logger.getLogger(VgpHydroLocationMetaTemplateUpdater.class);
	
	private List<Dao> daos;

	@Override
	public void setDaos(List<Dao> daos) {
		LOGGER.info("Inside VgpHydroLocationMetaTemplateUpdater.setDaos");
		this.daos = daos;
	}

	@Override
	public void update(final long sessionId, final Template template) throws TemplateProcessorException {
		LOGGER.info("Inside VgpHydroLocationMetaTemplateUpdater.update");
		Dao dao = daos.get(0);
		
		List<String> keys = template.getKeys();
		int len = keys.size();
		List<String> headers = template.get("1");
		
		for (int index = Numerals.TWO; index < len; index++) {
			VgpHydroTemplateValue value = (VgpHydroTemplateValue) template.getTemplateValue(String.valueOf(index));
			List<String> values = value.getDatas();
			Site site = convertToSite(sessionId, headers, values);
			dao.updateOrSave(site);
		}
//		VgpHydroEntity entity = new VgpHydroEntity();
//		entity.setId(IDGenerator.getUID().longValue());
//		entity.setLoaderId(sessionId);
//		dao.updateOrSave(entity);
	}

	private Site convertToSite(final long sessionId, final List<String> keys, final List<String> values) {
		Site site = new Site();
		site.setLoaderId(sessionId);
		//TODO -- Use reflection from the key list instead of hardcoding the order.
		site.setSiteId(values.get(0));
//		site.setUwi(values.get(1));
//		site.setLocalName(values.get(2));
//		site.setLocalDescription(values.get(3));
//		site.setState(values.get(4));
		
		return site;
	}

	
	@Override
	public List<Class<? extends Dao>> getDaoClasses() {
		LOGGER.info("Inside VgpHydroLocationMetaTemplateUpdater.getDaoClasses");
		List<Class<? extends Dao>> daoClasses = new ArrayList<>();
		daoClasses.add(VgpHydroLocationMetaDaoImpl.class);
		return daoClasses;
	}

}
