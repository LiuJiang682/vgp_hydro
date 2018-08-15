package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.observations.helper;

import java.util.Arrays;
import java.util.List;

import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper.HeaderMatcher;

public class HeaderHelper {

	private static final List<String> HEADERS = Arrays.asList("SITE_ID","Sample ID","IGSN","Date/Date_Time","Parameter","Depth From (m)","Result/Observation","Observer","Type");
	private final String header;
	
	public HeaderHelper(String header) {
		this.header = header;
	}

	public boolean isOneOfHeaders() {
		return new HeaderMatcher(header, HEADERS).isOneOfHeaders();
	}

}
