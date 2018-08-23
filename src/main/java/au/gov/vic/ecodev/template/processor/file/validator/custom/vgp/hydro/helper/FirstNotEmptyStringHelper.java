package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper;

import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;

public class FirstNotEmptyStringHelper {

	private final String[] strs;
	
	public FirstNotEmptyStringHelper(final String[] strs) {
		this.strs = strs;
	}
	
	public String getFirstNotEmptyString() throws TemplateProcessorException {
		Optional<String> optional = Arrays.stream(strs)
				.filter(e -> StringUtils.isNotBlank(e))
				.findFirst();
		return optional.orElseThrow(() -> new TemplateProcessorException("No String in the array!"));
	}
}
