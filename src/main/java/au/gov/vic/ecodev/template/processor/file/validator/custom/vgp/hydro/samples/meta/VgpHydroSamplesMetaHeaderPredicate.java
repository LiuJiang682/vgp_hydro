package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import au.gov.vic.ecodev.template.constants.Constants.Strings;

public class VgpHydroSamplesMetaHeaderPredicate {

	private static final String REGEX_IGNORE_CASE = "(?i)";
	private static final String UOM_SUFFIX = " (m)";
	private static final String REGEX_MULTI_SPACE = " +";
	private static final String REGEX_UOM_SUFFIX = " \\(m\\)";
	private static final String REGEX_MULTI_DELIM = "[ |-]{1}";
	
	public final static Predicate<String> isMatched(final String label) {
		return str -> {
			str = str.trim().replaceAll(REGEX_MULTI_SPACE, Strings.SPACE);
			if (str.equalsIgnoreCase(label)) {
				return true;
			} else if(str.equalsIgnoreCase(label + UOM_SUFFIX)) {
				return true;
			} else if (label.contains(Strings.UNDER_LINE)) {
				String variation = REGEX_IGNORE_CASE + label.replaceAll(Strings.UNDER_LINE, REGEX_MULTI_DELIM);
				return Pattern.matches(variation, str) || Pattern.matches(variation + REGEX_UOM_SUFFIX, str);
			} else {
				return false;
			}
		};
	}

}
