package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.helper.HeaderHelper;

public class VgpHydroSamplesMetaValidatorFactory {

	public Validator getLineValidator(final String line) {
		Validator validator = new VgpHydroSamplesMetaDefaultValidator();
		if (StringUtils.isNotBlank(line)) {
			String[] strs = line.split(Strings.TAB);
			if (new HeaderHelper(strs[Numerals.ZERO]).isOneOfHeaders()) {
				validator = new VgpHydroSamplesMetaHeaderValidator();
			} else {
				validator = new VgpHydroSamplesMetaDataValidator();
			}
		}
		return validator;
	}

}
