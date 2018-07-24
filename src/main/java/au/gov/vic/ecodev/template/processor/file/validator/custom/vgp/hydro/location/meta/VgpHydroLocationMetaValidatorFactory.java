package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta.helper.HeaderHelper;

public class VgpHydroLocationMetaValidatorFactory {

	public Validator getLineValidator(final String line) throws Exception {
		Validator validator = new VgpHydroLocationMetaDefaultValidator();
		if (StringUtils.isNotBlank(line)) {
			String[] strs = line.split(Strings.TAB);
			if (new HeaderHelper(strs[Numerals.ZERO]).isOneOfHeaders()) {
				validator = new VgpHydroLocationMetaHeaderValidator();
			} else {
				validator = new VgpHydroLocationMetaDataValidator();
			}
			validator.init(strs);
		}
		return validator;
	}
}
