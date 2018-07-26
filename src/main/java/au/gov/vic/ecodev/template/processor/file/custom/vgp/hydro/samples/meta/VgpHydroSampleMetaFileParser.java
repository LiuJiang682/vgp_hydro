package au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.samples.meta;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.location.meta.VgpHydroLocationMetaFileParser;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.VgpHydroSamplesMetaValidatorFactory;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroSamplesMetaTemplate;
import au.gov.vic.ecodev.utils.file.finder.DirectoryTreeReverseTraversalZipFileFinder;

public class VgpHydroSampleMetaFileParser {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroLocationMetaFileParser.class);
	
	private final File file;
	private final TemplateProcessorContext templateProcessorContext;
	
	public VgpHydroSampleMetaFileParser(File file, TemplateProcessorContext templateProcessorContext) {
		if (null == file) {
			throw new IllegalArgumentException("VgpHydroSampleMetaFileParser:file parameter cannot be null!");
		}
		this.file = file;
		if (null == templateProcessorContext) {
			throw new IllegalArgumentException("VgpHydroSampleMetaFileParser:templateProcessorContext parameter cannot be null!");
		}
		this.templateProcessorContext = templateProcessorContext;
	}

	public void parse() {
		Map<String, List<String>> templateParamMap = new HashMap<>();
		Template dataBean = new VgpHydroSamplesMetaTemplate();
		String zipFile = new DirectoryTreeReverseTraversalZipFileFinder(file.getParent()).findZipFile();
		
		VgpHydroSamplesMetaValidatorFactory vgpHydroSamplesMetaValidatorFactory = new VgpHydroSamplesMetaValidatorFactory();

	}

}
