package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;

public class VgpHydroSamplesMetaDefaultValidator implements Validator {

	private String[] strs;
	
	@Override
	public void init(String[] strs) {
		this.strs = strs;
	}

	@Override
	public Optional<List<String>> validate(Map<String, List<String>> templateParamMap, Template dataBean) {
		// TODO Auto-generated method stub
		return null;
	}

}
