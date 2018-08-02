package au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.samples.analysis;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.analysis.VgpHydroSamplesAnalysisValidatorFactory;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroSamplesAnalysisTemplate;
import au.gov.vic.ecodev.utils.file.finder.DirectoryTreeReverseTraversalZipFileFinder;

public class VgpHydroSamplesAnaylsisFileParser {

	private final File file;
	private final TemplateProcessorContext templateProcessorContext;
	
	public VgpHydroSamplesAnaylsisFileParser(final File file, 
			final TemplateProcessorContext templateProcessorContext) {
		if (null == file) {
			throw new IllegalArgumentException("VgpHydroSamplesAnaylsisFileParser:file parameter cannot be null!");
		}
		this.file = file;
		if (null == templateProcessorContext) {
			throw new IllegalArgumentException("VgpHydroSamplesAnaylsisFileParser:templateProcessorContext parameter cannot be null!");
		}
		this.templateProcessorContext = templateProcessorContext;
	}
	
	public void parse() {
		Map<String, List<String>> templateParamMap = new HashMap<>();
		Template dataBean = new VgpHydroSamplesAnalysisTemplate();
		String zipFile = new DirectoryTreeReverseTraversalZipFileFinder(file.getParent()).findZipFile();
		
		VgpHydroSamplesAnalysisValidatorFactory vgpHydroSamplesAnalysisValidatorFactory = 
				new VgpHydroSamplesAnalysisValidatorFactory();
	}

}
