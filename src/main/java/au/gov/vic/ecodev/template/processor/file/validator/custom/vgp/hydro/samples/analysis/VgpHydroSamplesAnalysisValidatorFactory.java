package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.analysis;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.VgpHydroDefaultValidator;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.analysis.helper.HeaderHelper;

public class VgpHydroSamplesAnalysisValidatorFactory {

	public Validator getLineValidator(String line) {
		Validator validator = new VgpHydroDefaultValidator();
		if (StringUtils.isNotBlank(line)) {
			String[] headers = line.split(Strings.TAB);
			if (new HeaderHelper(headers[Numerals.ZERO]).isOneOfHeaders()) {
				validator = new VgpHydroSamplesAnalysisHeaderValidator();
			} else {
				validator = new VgpHydroSamplesAnalysisDataValidator();
			}
			validator.init(headers);
		}
		return validator;
	}

}
