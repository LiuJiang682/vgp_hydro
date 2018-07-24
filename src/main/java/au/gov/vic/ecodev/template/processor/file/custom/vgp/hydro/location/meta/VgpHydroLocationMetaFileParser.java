package au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro.location.meta;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta.VgpHydroLocationMetaValidatorFactory;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroTemplate;
import au.gov.vic.ecodev.utils.file.finder.DirectoryTreeReverseTraversalZipFileFinder;
import au.gov.vic.ecodev.utils.file.helper.MessageHandler;

public class VgpHydroLocationMetaFileParser {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroLocationMetaFileParser.class);
	
	private final File file;
	private final TemplateProcessorContext templateProcessorContext;
	
	public VgpHydroLocationMetaFileParser(File file, TemplateProcessorContext templateProcessorContext) {
		if (null == file) {
			throw new IllegalArgumentException("VgpHydroFileParser:file parameter cannot be null!");
		}
		this.file = file;
		if (null == templateProcessorContext) {
			throw new IllegalArgumentException("VgpHydroFileParser:templateProcessorContext parameter cannot be null!");
		}
		this.templateProcessorContext = templateProcessorContext;
	}

	public void parse() throws Exception {
		Map<String, List<String>> templateParamMap = new HashMap<>();
		Template dataBean = new VgpHydroTemplate();
		String zipFile = new DirectoryTreeReverseTraversalZipFileFinder(file.getParent()).findZipFile();
//		final List<String> mandatoryFields = getMandatoryValidateFields();
		VgpHydroLocationMetaValidatorFactory vgpHydroValidatorFactory = new VgpHydroLocationMetaValidatorFactory(templateProcessorContext);
		
		LineNumberReader lineNumberReader = getLineNumberReader();
		
		String line;
		while(null != (line = lineNumberReader.readLine())) {
			LOGGER.info(line);
			int lineNumber = lineNumberReader.getLineNumber();
			templateParamMap.put(Strings.CURRENT_LINE, Arrays.asList(String.valueOf(lineNumber)));
			Validator validator = vgpHydroValidatorFactory.getLineValidator(line);
			Optional<List<String>> errorMessage = validator.validate(templateParamMap, 
					dataBean);
			if (errorMessage.isPresent()) {
				dataBean = new MessageHandler(errorMessage.get(), templateProcessorContext, dataBean, 
						file, lineNumber).doMessagesHandling();
			}
		}
		
		if (null != dataBean) {
			if (templateProcessorContext.saveDataBean(dataBean)) {
				templateProcessorContext.addPassedFiles(zipFile);
			} else {
				throw new TemplateProcessorException("Unable to save the data bean!");
			}
		}
	}

	private LineNumberReader getLineNumberReader() throws Exception {
		return new LineNumberReader(new FileReader(file.getAbsolutePath()));
	}
}
