package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;

public class VgpHydroLocationMetaDefaultValidator implements Validator {

	@Override
	public void init(String[] strs) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<List<String>> validate(Map<String, List<String>> templateParamMap, Template dataBean) {
		return Optional.empty();
	}

}
