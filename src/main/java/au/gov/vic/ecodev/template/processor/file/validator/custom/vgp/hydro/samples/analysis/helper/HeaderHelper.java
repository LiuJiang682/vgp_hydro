package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.analysis.helper;

import java.util.Arrays;
import java.util.List;

import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper.HeaderMatcher;

public class HeaderHelper {

	private static final List<String> HEADERS = Arrays.asList("SITE_ID", "IGSN", "Lab_Sample_Number", "Analysed_Date", "Parameter", "Result", "UOM", "Analysis_Method", "LOR");
	
	private final String header;
	
	public HeaderHelper(final String header) {
		this.header = header;
	}

	public boolean isOneOfHeaders() {
		return new HeaderMatcher(header, HEADERS).isOneOfHeaders();
	}

}
