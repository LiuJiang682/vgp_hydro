package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta.helper.HeaderHelper;

public class VgpHydroLocationMetaValidatorFactory {

	private final TemplateProcessorContext templateProcessorContext;
	
	public VgpHydroLocationMetaValidatorFactory(TemplateProcessorContext templateProcessorContext) {
		if (null == templateProcessorContext) {
			throw new IllegalArgumentException("Sl4ValidatorFactory:templateProcessorContext parameter cannot be null!");
		}
		this.templateProcessorContext = templateProcessorContext;
	}

	public Validator getLineValidator(final String line) throws Exception {
		Validator validator = new VgpHydroLocationMetaDefaultValidator();
		if (StringUtils.isNotBlank(line)) {
			String[] strs = line.split(Strings.TAB);
			if (new HeaderHelper(strs[Numerals.ZERO]).isOneOfHeaders()) {
				validator = new VgpHydroLocationMetaHeaderValidator();
			} else {
				validator = new VgpHydroLocationMetaDataValidator();
			}
		}
		return validator;
	}
}
