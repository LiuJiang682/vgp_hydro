package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.VgpHydroDefaultValidator;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper.FirstNotEmptyStringHelper;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.helper.HeaderHelper;

public class VgpHydroSamplesMetaValidatorFactory {

	public Validator getLineValidator(final String line) throws Exception {
		Validator validator = new VgpHydroDefaultValidator();
		if (StringUtils.isNotBlank(line)) {
			String[] strs = line.split(Strings.TAB);
			String element = new FirstNotEmptyStringHelper(strs).getFirstNotEmptyString();
			if (new HeaderHelper(element).isOneOfHeaders()) {
				validator = new VgpHydroSamplesMetaHeaderValidator();
			} else {
				validator = new VgpHydroSamplesMetaDataValidator();
			}
			validator.init(strs);
		}
		return validator;
	}

}
