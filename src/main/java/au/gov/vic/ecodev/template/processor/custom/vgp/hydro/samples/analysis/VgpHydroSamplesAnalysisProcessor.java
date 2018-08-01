package au.gov.vic.ecodev.template.processor.custom.vgp.hydro.samples.analysis;

import java.io.File;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import au.gov.vic.ecodev.mrt.template.processor.TemplateProcessor;
import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.samples.analysis.VgpHydroSamplesAnaylsisFileParser;

public class VgpHydroSamplesAnalysisProcessor implements TemplateProcessor {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroSamplesAnalysisProcessor.class);
	
	private List<File> files;
	private TemplateProcessorContext context;
	
	@Override
	public void setFileList(List<File> files) {
		LOGGER.info("VgpHydroSamplesAnalysisProcessor:setFileList");
		this.files = files;
	}

	@Override
	public void setTemplateProcessorContent(TemplateProcessorContext context) {
		LOGGER.info("VgpHydroSamplesAnalysisProcessor:setTemplateProcessorContent");
		this.context = context;
	}

	@Override
	public void processFile() throws TemplateProcessorException {
		LOGGER.info("VgpHydroSamplesAnalysisProcessor:processFile");
		
		if (CollectionUtils.isEmpty(files)) {
			throw new TemplateProcessorException("No file to process!");
		}
		
		if (null == context) {
			throw new TemplateProcessorException("No context present!");
		}
		
		try {
			for (File file : files) {
				VgpHydroSamplesAnaylsisFileParser vgpHydroSamplesAnaylsisFileParser = 
						new VgpHydroSamplesAnaylsisFileParser(file, context);
				vgpHydroSamplesAnaylsisFileParser.parse();
			}
		} catch (Exception e) {
			throw new TemplateProcessorException(e.getMessage(), e);
		}
	}

}
