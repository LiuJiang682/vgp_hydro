package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.helper;

import java.util.Arrays;
import java.util.List;

public class HeaderHelper {

	private static final List<String> HEADERS = Arrays.asList("SITE_ID", "Sample_ID", "CORE ID", "Lab_Code", "Type", "Preparation_Code", "Sampled_Date", "IGSN", "Sample_Top", "Sample_bottom", "Standing_Water_Level", "Pumping_Depth", "Reference", "Sample_Area_Description");
	
	private final String header;
	
	public HeaderHelper(final String header) {
		this.header = header;
	}

	public boolean isOneOfHeaders() {
		return HEADERS.stream()
				.anyMatch(header::equalsIgnoreCase);
	}

}
