package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;

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
			
		}
		return validator;
	}
}
