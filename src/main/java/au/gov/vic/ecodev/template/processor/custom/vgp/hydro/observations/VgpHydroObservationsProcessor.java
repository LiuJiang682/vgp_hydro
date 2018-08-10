package au.gov.vic.ecodev.template.processor.custom.vgp.hydro.observations;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import au.gov.vic.ecodev.mrt.template.processor.TemplateProcessor;
import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;

public class VgpHydroObservationsProcessor implements TemplateProcessor {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroObservationsProcessor.class);
	
	private List<File> files;
	private TemplateProcessorContext context;
	
	@Override
	public void setFileList(List<File> files) {
		LOGGER.info("VgpHydroObservationsProcessor:setFileList");	
		this.files = files;
	}

	@Override
	public void setTemplateProcessorContent(TemplateProcessorContext context) {
		LOGGER.info("VgpHydroObservationsProcessor:setTemplateProcessorContent");
		this.context = context;
	}

	@Override
	public void processFile() throws TemplateProcessorException {
		// TODO Auto-generated method stub
		
	}

}
