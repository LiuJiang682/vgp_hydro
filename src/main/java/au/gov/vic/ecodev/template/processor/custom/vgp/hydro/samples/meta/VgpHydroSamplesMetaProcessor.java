package au.gov.vic.ecodev.template.processor.custom.vgp.hydro.samples.meta;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import au.gov.vic.ecodev.mrt.template.processor.TemplateProcessor;
import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.samples.meta.VgpHydroSampleMetaFileParser;

public class VgpHydroSamplesMetaProcessor implements TemplateProcessor {
	
	private static final Logger LOGGER = Logger.getLogger(VgpHydroSamplesMetaProcessor.class);
	
	private List<File> files;
	private TemplateProcessorContext context;

	@Override
	public void setFileList(List<File> files) {
		LOGGER.info("VgpHydroSamplesMetaProcessor:setFileList");
		this.files = files;
	}

	@Override
	public void setTemplateProcessorContent(TemplateProcessorContext context) {
		LOGGER.info("VgpHydroSamplesMetaProcessor:setTemplateProcessorContent");
		this.context = context;
	}

	@Override
	public void processFile() throws TemplateProcessorException {
		LOGGER.info("VgpHydroSamplesMetaProcessor:processFile");
		if (CollectionUtils.isEmpty(files)) {
			throw new TemplateProcessorException("No file to process!");
		}
		
		if (null == context) {
			throw new TemplateProcessorException("No context present!");
		}
		
		try {
			for (File file : files) {
				VgpHydroSampleMetaFileParser vgpHydroSampleMetaFileParser = new VgpHydroSampleMetaFileParser(file,
						context);
				vgpHydroSampleMetaFileParser.parse();
			}
		} catch (Exception e) {
			throw new TemplateProcessorException(e.getMessage(), e);
		}
	}

}
