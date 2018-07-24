package au.gov.vic.ecodev.template.processor.custom.vgp.hydro;

import au.gov.vic.ecodev.template.constants.Constants.Strings;

public class TestFixture {

	public static final String VGP_HYDRO_LOC_META_HEADERS = "Site_ID\tUWI\tLocal_Name\tLocation_Desc\tState\tGrid/Zone\tEasting\tNorthing\tDatum\tLatitude\tLongitude\tKB\tElevation\tBore Diameter\tTD\tTVD\tDepth Datum";

	public static String[] getLocMetaHeaders() {
		return VGP_HYDRO_LOC_META_HEADERS.split(Strings.TAB);
	}
}
