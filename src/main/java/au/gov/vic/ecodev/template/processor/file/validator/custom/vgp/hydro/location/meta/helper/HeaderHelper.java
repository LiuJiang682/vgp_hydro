package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta.helper;

import java.util.Arrays;
import java.util.List;

public class HeaderHelper {
	
	//TODO -- Should convert to enum???
	private static final List<String> HEADERS = Arrays.asList("Site_ID", "UWI", "Local_Name", "Location_Desc", "State", "Grid/Zone", "Easting", "Northing", "Datum", "Latitude", "Longitude", "KB", "Elevation", "Bore Diameter", "TD", "TVD", "Depth Datum");
	
	private final String header;

	public HeaderHelper(final String header) {
		this.header = header;
	}

	public boolean isOneOfHeaders() {
		return HEADERS.stream()
				.anyMatch(header::equalsIgnoreCase);
	}

}
