package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleAnalysis;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.mrt.template.processor.update.TemplateUpdater;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroSamplesAnalysisDaoImpl;
import au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder.SampleAnalysisBuilder;
import au.gov.vic.ecodev.utils.file.helper.FileNameExtractionHelper;

public class VgpHydroSamplesAnalysisTemplateUpdater implements TemplateUpdater {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroSamplesAnalysisTemplateUpdater.class);
	
	private List<Dao> daos;
	
	@Override
	public void setDaos(List<Dao> daos) {
		LOGGER.info("Inside VgpHydroSamplesAnalysisTemplateUpdater:setDaos");
		this.daos = daos;
	}

	@Override
	public void update(long sessionId, Template template) throws TemplateProcessorException {
		LOGGER.info("Inside VgpHydroSamplesAnalysisTemplateUpdater:update");
		
		if (CollectionUtils.isEmpty(daos)) {
			throw new TemplateProcessorException("Dao list cannot be null or empty!");
		}
		
		String fileName = new FileNameExtractionHelper(template, Strings.CURRENT_FILE_NAME)
				.doFileNameExtraction();
		
		List<String> headers = template.get(String.valueOf(Numerals.ONE));
		int len = template.getKeys().size();
		Dao dao = daos.get(Numerals.ZERO);
		for(int index = Numerals.TWO; index < len; index++) {
			List<String> datas = template.get(String.valueOf(index));
			SampleAnalysis sampleMeta = new SampleAnalysisBuilder(sessionId, headers, datas, 
					fileName, index).build();
			dao.updateOrSave(sampleMeta);
		}
	}

	@Override
	public List<Class<? extends Dao>> getDaoClasses() {
		LOGGER.info("Inside VgpHydroSamplesAnalysisTemplateUpdater.getDaoClasses");
		List<Class<? extends Dao>> daoClasses = new ArrayList<>();
		daoClasses.add(VgpHydroSamplesAnalysisDaoImpl.class);
		return daoClasses;
	}

}
