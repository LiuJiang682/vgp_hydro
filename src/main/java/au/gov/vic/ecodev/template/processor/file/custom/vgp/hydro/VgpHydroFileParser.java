package au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.mrt.template.properties.TemplateProperties;
import au.gov.vic.ecodev.template.processor.context.properties.StringToListTemplatePropertiesParser;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.VgpHydroValidatorFactory;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroTemplate;
import au.gov.vic.ecodev.utils.file.finder.DirectoryTreeReverseTraversalZipFileFinder;
import au.gov.vic.ecodev.utils.file.helper.MessageHandler;

public class VgpHydroFileParser {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroFileParser.class);
	
	private static final String TEMPLATE_PROP_VGP_HYDRO_MANDATORY_VALIDATE = "VGPHYDRO:MANDATORY.VALIDATE.FIELDS";
	
	private static final String COMMA = ",";
	
	private final File file;
	private final TemplateProcessorContext templateProcessorContext;
	
	public VgpHydroFileParser(File file, TemplateProcessorContext templateProcessorContext) {
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
		final List<String> mandatoryFields = getMandatoryValidateFields();
		VgpHydroValidatorFactory vgpHydroValidatorFactory = new VgpHydroValidatorFactory(templateProcessorContext, 
				mandatoryFields);
		
		LineNumberReader lineNumberReader = getLineNumberReader();
		
		String line;
		while(null != (line = lineNumberReader.readLine())) {
			LOGGER.info(line);
			int lineNumber = lineNumberReader.getLineNumber();
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

	protected final List<String> getMandatoryValidateFields() throws TemplateProcessorException {
		TemplateProperties mandatoryFieldTemplateProperties = templateProcessorContext
				.getTemplateContextProperty(TEMPLATE_PROP_VGP_HYDRO_MANDATORY_VALIDATE);
		List<String> mandatoryFields = new StringToListTemplatePropertiesParser(mandatoryFieldTemplateProperties, 
				COMMA).parse();
		return mandatoryFields;
	}

	private LineNumberReader getLineNumberReader() throws Exception {
		return new LineNumberReader(new FileReader(file.getAbsolutePath()));
	}
}
