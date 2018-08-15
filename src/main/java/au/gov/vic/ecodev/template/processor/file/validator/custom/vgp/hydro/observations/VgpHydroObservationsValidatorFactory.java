package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.observations;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.VgpHydroDefaultValidator;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.observations.helper.HeaderHelper;


public class VgpHydroObservationsValidatorFactory {

	public Validator getLineValidator(String line) {
		Validator validator = new VgpHydroDefaultValidator();
		if (StringUtils.isNotBlank(line)) {
			String[] headers = line.split(Strings.TAB);
			if (new HeaderHelper(headers[Numerals.ZERO]).isOneOfHeaders()) {
				validator = new VgpHydroObservationsHeaderValidator();
			} else {
				validator = new VgpHydroObservationsDataValidator();
			}
			validator.init(headers);
		}
		return validator;
	}

}
