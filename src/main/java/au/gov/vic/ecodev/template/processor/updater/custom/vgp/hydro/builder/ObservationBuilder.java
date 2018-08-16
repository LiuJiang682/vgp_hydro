package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import au.gov.vic.ecodev.common.util.IDGenerator;
import au.gov.vic.ecodev.mrt.model.vgp.hydro.Observation;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.observations.VgpHydroObservationsMandatoryHeaders;
import au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.helper.DataValueHelper;

public class ObservationBuilder {

	private final long sessionId;
	private final List<String> headers;
	private final List<String> datas;
	
	public ObservationBuilder(long sessionId, List<String> headers, List<String> datas) {
		this.sessionId = sessionId;
		if (CollectionUtils.isEmpty(headers)) {
			throw new IllegalArgumentException("SampleAnalysisBuilder:headers parameter cannot be null!");
		}
		this.headers = headers;
		if (null == datas) {
			this.datas = new ArrayList<>();
		} else {
			this.datas = datas;
		}
	}

	public Observation build() {
		DataValueHelper dataValueHelper = new DataValueHelper();
		Observation observation = new Observation();
		observation.setId(IDGenerator.getUIDAsAbsLongValue());
		observation.setLoaderId(sessionId);
		observation.setSiteId(dataValueHelper.getDataValueAsLong(headers, datas, 
				VgpHydroObservationsMandatoryHeaders.SITE_ID.getCode()));
		observation.setSampleId(dataValueHelper.getDataValueAsLong(headers, datas, "Sample_ID"));
		observation.setIgsn(dataValueHelper.getDataValueAsString(headers, datas, 
				VgpHydroObservationsMandatoryHeaders.IGSN.getCode()));
		observation.setOccurTime(dataValueHelper.getDataValueAsTimestampToHour(headers, datas, 
				VgpHydroObservationsMandatoryHeaders.SAMPLE_TIME.getCode()));
		observation.setParameter(dataValueHelper.getDataValueAsString(headers, datas, 
				"Parameter"));
		observation.setDepthFrom(dataValueHelper.getDataValueAsBigDecimal(headers, datas, 
				"Depth From (m)"));
		observation.setDepthTo(dataValueHelper.getDataValueAsBigDecimal(headers, datas, 
				"Depth To (m)"));
		observation.setResult(dataValueHelper.getDataValueAsString(headers, datas, 
				"Result/Observation"));
		observation.setObserver(dataValueHelper.getDataValueAsString(headers, datas, 
				"Observer"));
		observation.setType(dataValueHelper.getDataValueAsString(headers, datas, "Type"));
		return observation;
	}

}
