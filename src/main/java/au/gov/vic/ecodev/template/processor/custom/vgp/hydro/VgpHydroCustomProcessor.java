package au.gov.vic.ecodev.template.processor.custom.vgp.hydro;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import au.gov.vic.ecodev.mrt.template.processor.TemplateProcessor;
import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;

public class VgpHydroCustomProcessor implements TemplateProcessor {
	
	private static final Logger LOGGER = Logger.getLogger(VgpHydroCustomProcessor.class);
	
	private TemplateProcessorContext templateProcessorContext;
	private List<File> files;

	@Override
	public void processFile() {
		LOGGER.info("VgpHydroCustomProcessor.processFile");
		
	}

	@Override
	public void setFileList(List<File> files) {
		LOGGER.info("VgpHydroCustomProcessor.setFileList");
		this.files = files;
	}

	@Override
	public void setTemplateProcessorContent(TemplateProcessorContext context) {
		LOGGER.info("VgpHydroCustomProcessor.setTemplateProcessorContent");
		this.templateProcessorContext = context;
	}

}