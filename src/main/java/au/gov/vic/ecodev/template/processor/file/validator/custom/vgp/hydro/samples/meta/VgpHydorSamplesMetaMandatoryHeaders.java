package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

public enum VgpHydorSamplesMetaMandatoryHeaders {

	 SITE_ID("Site_ID"),
	 SAMPLE_ID("Sample_ID"),
	 IGSN("IGSN"),
	 SAMPLE_TOP("Sample_Top"),
	 SAMPLE_BOTTOM("Sample_Bottom"),
	 STANDING_WATER_LEVEL("Standing_Water_Level"),
	 PUMPING_DEPTH("Pumping_Depth"),
	 REFERENCE("Reference");
	
	private VgpHydorSamplesMetaMandatoryHeaders(final String displayLabel) {
		this.displayLabel = displayLabel;
	}
	
	private String displayLabel;
	
	public String getDisplayLabel() {
		return displayLabel;
	}
}
