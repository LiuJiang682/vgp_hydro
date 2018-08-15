package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.observations;

public enum VgpHydroObservationsMandatoryHeaders {

	SITE_ID("SITE_ID"),
	IGSN("IGSN"),
	SAMPLE_TIME("DATE/DATE_TIME");
	
	private String code;
	
	private VgpHydroObservationsMandatoryHeaders(final String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
