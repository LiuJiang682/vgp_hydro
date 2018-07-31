package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

import java.util.List;
import java.util.OptionalInt;

import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.helper.HeaderHelper;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.SiteIdRepository;

public class SiteIdLocationValidator {

	private final String[] strs;
	private final String lineNumber;
	private final List<String> columnHeaders;
	private final String templateName;
	
	public SiteIdLocationValidator(final String[] strs, final String lineNumber, 
			final List<String> columnHeaders, final String templateName) {
		this.strs = strs;
		this.lineNumber = lineNumber;
		this.columnHeaders = columnHeaders;
		this.templateName = templateName;
	}

	public void validate(List<String> messages) {
		OptionalInt indexOpt = new HeaderHelper(VgpHydroSamplesMetaMandatoryHeaders
				.SITE_ID.name()).findHeaderIndex(columnHeaders);
		
		if (indexOpt.isPresent()) {
			int index = indexOpt.getAsInt();
			String string = null;
			if ((null != strs) 
					&& (index < strs.length)) {
				string = strs[index];
				if (!SiteIdRepository.INSTANCE.contains(string)) {
					String message = new StringBuilder(Strings.LOG_ERROR_HEADER)
							.append("Line ")
							.append(lineNumber)
							.append(": Template ")
							.append(templateName)
							.append(" column ")
							.append(VgpHydroSamplesMetaMandatoryHeaders.SITE_ID.name())
							.append(" is NO exist in Location Metadata table ")
							.append(string)
							.toString();
					messages.add(message);
				}
			}
		}
	}

}
