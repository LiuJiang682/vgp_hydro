package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.analysis;

public enum VgpHydroSamplesAnalysisMandatoryHeaders {

	SITE_ID("SITE_ID"),
	PARAM("Parameter"),
	UOM("UOM"),
	RESULT("Result");
	
	private VgpHydroSamplesAnalysisMandatoryHeaders(final String code) {
		this.code = code;
	}
	
	private String code;
	
	public String getCode() {
		return code;
	}
}
