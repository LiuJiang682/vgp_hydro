package au.gov.vic.ecodev.template.processor.custom.vgp.hydro.location.meta;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import au.gov.vic.ecodev.mrt.template.processor.TemplateProcessor;
import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.location.meta.VgpHydroLocationMetaFileParser;

public class VgpHydroLocationMetaProcessor implements TemplateProcessor {
	
	private static final Logger LOGGER = Logger.getLogger(VgpHydroLocationMetaProcessor.class);
	
	private TemplateProcessorContext templateProcessorContext;
	private List<File> files;

	@Override
	public void processFile() throws TemplateProcessorException {
		LOGGER.info("VgpHydroLocationMetaProcessor.processFile");
		if (CollectionUtils.isEmpty(files)) {
			throw new TemplateProcessorException("No file to process!");
		}

		if (null == templateProcessorContext) {
			throw new TemplateProcessorException("No context present!");
		}
		
		try {
			for (File file : files) {
				VgpHydroLocationMetaFileParser vgpHydroLocationMetaFileParser = new VgpHydroLocationMetaFileParser(file,
						templateProcessorContext);
				vgpHydroLocationMetaFileParser.parse();
			}
		} catch (Exception e) {
			throw new TemplateProcessorException(e.getMessage(), e);
		}
	}

	@Override
	public void setFileList(List<File> files) {
		LOGGER.info("VgpHydroLocationMetaProcessor.setFileList");
		this.files = files;
	}

	@Override
	public void setTemplateProcessorContent(TemplateProcessorContext context) {
		LOGGER.info("VgpHydroLocationMetaProcessor.setTemplateProcessorContent");
		this.templateProcessorContext = context;
	}

}