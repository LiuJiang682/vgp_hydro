package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import au.gov.vic.ecodev.common.util.IDGenerator;
import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleMeta;
import au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.helper.DataValueHelper;

public class SampleMetaBuilder {

	private final long sessionId;
	private final List<String> headers;
	private final List<String> datas;
	
	public SampleMetaBuilder(final long sessionId, final List<String> headers, 
			final List<String> datas) {
		this.sessionId = sessionId;
		if (CollectionUtils.isEmpty(headers)) {
			throw new IllegalArgumentException("VgpHydroSamplesMeta Headers cannot be null or empty!");
		}
		this.headers = headers;
		if (null == datas) {
			this.datas = new ArrayList<>();
		} else {
			this.datas = datas;
		}
	}
	
	public SampleMeta build() {
		DataValueHelper dataValueHelper = new DataValueHelper();
		SampleMeta sampleMeta = new SampleMeta();
		sampleMeta.setId(IDGenerator.getUID().longValue());
		sampleMeta.setLoaderId(sessionId);
		sampleMeta.setSiteId(dataValueHelper
				.getDataValueAsLong(headers, datas, "SITE_ID"));
		sampleMeta.setSampleId(dataValueHelper
				.getDataValueAsLong(headers, datas, "Sample_ID"));
		sampleMeta.setCoreId(dataValueHelper
				.getDataValueAsLong(headers, datas, "Core_ID"));
		sampleMeta.setLabCode(dataValueHelper
				.getDataValueAsString(headers, datas, "Lab_Code"));
		sampleMeta.setType(dataValueHelper
				.getDataValueAsString(headers, datas, "Type"));
		sampleMeta.setPrepCode(dataValueHelper
				.getDataValueAsString(headers, datas, "Proparation Code"));
		sampleMeta.setSampleDate(dataValueHelper
				.getDataValueAsTimestamp(headers, datas, "Sampled Date"));
		sampleMeta.setIgsn(dataValueHelper.getDataValueAsString(headers, datas, "IGSN"));
		sampleMeta.setSampleTop(dataValueHelper
				.getDataValueAsBigDecimal(headers, datas, "Sample_Top"));
		sampleMeta.setSampleBottom(dataValueHelper
				.getDataValueAsBigDecimal(headers, datas, "Sample_Bottom"));
		sampleMeta.setStandardWaterLevel(dataValueHelper
				.getDataValueAsBigDecimal(headers, datas, "Standing Water Level"));
		sampleMeta.setPumpingDepth(dataValueHelper
				.getDataValueAsBigDecimal(headers, datas, "Pumping Depth"));
		sampleMeta.setReference(dataValueHelper
				.getDataValueAsString(headers, datas, "Reference"));
		sampleMeta.setSampleAreaDesc(dataValueHelper
				.getDataValueAsString(headers, datas, "Sample_Are_Description"));
		return sampleMeta;
	}
}
