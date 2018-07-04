package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro;

import java.util.List;

import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;

public class VgpHydroValidatorFactory {

	private final TemplateProcessorContext templateProcessorContext;
	private final List<String> mandatoryFields;
	
	public VgpHydroValidatorFactory(TemplateProcessorContext templateProcessorContext, List<String> mandatoryFields) {
		if (null == templateProcessorContext) {
			throw new IllegalArgumentException("Sl4ValidatorFactory:templateProcessorContext parameter cannot be null!");
		}
		this.templateProcessorContext = templateProcessorContext;
		this.mandatoryFields = mandatoryFields;
	}

	public Validator getLineValidator(final String line) throws Exception {
		return new VgpHydroDefaultValidator();
	}
}
