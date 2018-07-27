package au.gov.vic.ecodev.template.processor.custom.vgp.hydro;

import au.gov.vic.ecodev.template.constants.Constants.Strings;

public class TestFixture {

	public static final String VGP_HYDRO_LOC_META_HEADERS = "Site_ID\tUWI\tLocal_Name\tLocation_Desc\tState\tGrid/Zone\tEasting\tNorthing\tDatum\tLatitude\tLongitude\tKB\tElevation\tBore Diameter\tTD\tTVD\tDepth Datum";

	public static String[] getLocMetaHeaders() {
		return VGP_HYDRO_LOC_META_HEADERS.split(Strings.TAB);
	}

	public static final String VGP_HYDRO_LOC_META_DATA_TEST = "102621\t\t\t\t\tMGA 54\t636758.5\t5747711.5\t\t\t\t\t11.88\t100\t\t\t";
	
	public static String[] getLocMetaData() {
		return VGP_HYDRO_LOC_META_DATA_TEST.split(Strings.TAB);
	}

	public static final String VGP_HYDRO_SAMPLES_META_HEADER = "SITE_ID\tSample_ID\tCORE ID\tLab_Code\tType\tPreparation_Code\tSampled_Date\tIGSN\tSample_Top\tSample_bottom\tStanding_Water_Level\tPumping_Depth\tReference\tSample_Area_Description";
	public static final String VGP_HYDRO_SAMPLES_META_DATA = "110098\t123\t\tGSV\tWater\t\t5/01/2018\t678\t48\t66\t4.58\t54\t\t";

	public static String[] getSamplesMetaHeaders() {
		return VGP_HYDRO_SAMPLES_META_HEADER.split(Strings.TAB);
	}
	
	public static String[] getSamplesMetaData() {
		return VGP_HYDRO_SAMPLES_META_DATA.split(Strings.TAB);
	}
}
