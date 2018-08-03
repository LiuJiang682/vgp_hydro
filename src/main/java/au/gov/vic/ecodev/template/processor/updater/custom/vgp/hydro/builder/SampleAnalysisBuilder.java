package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import au.gov.vic.ecodev.common.util.IDGenerator;
import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleAnalysis;
import au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.helper.DataValueHelper;

public class SampleAnalysisBuilder {

	private final long sessionId;
	private final List<String> headers;
	private final List<String> datas;
	
	public SampleAnalysisBuilder(long sessionId, List<String> headers, List<String> datas) {
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

	public SampleAnalysis build() {
		DataValueHelper dataValueHelper = new DataValueHelper();
		SampleAnalysis sampleAnalysis = new SampleAnalysis();
		sampleAnalysis.setId(IDGenerator.getUID().longValue());
		sampleAnalysis.setLoaderId(sessionId);
		sampleAnalysis.setSampleId(dataValueHelper.getDataValueAsLong(headers, datas, "Sample_ID"));
		sampleAnalysis.setIgsn(dataValueHelper.getDataValueAsString(headers, datas, "IGSN"));
		sampleAnalysis.setLabSampleNo(dataValueHelper.getDataValueAsString(headers, datas, "Lab_Sample_Number"));
		sampleAnalysis.setAnalysisDate(dataValueHelper.getDataValueAsTimestampToHour(headers, datas, "Anaylsed_Date"));
		sampleAnalysis.setParameter(dataValueHelper.getDataValueAsString(headers, datas, "Parameter"));
		sampleAnalysis.setUom(dataValueHelper.getDataValueAsString(headers, datas, "UOM"));
		sampleAnalysis.setResult(dataValueHelper.getDataValueAsString(headers, datas, "Result"));
		sampleAnalysis.setAnanlysisMethod(dataValueHelper.getDataValueAsString(headers, datas, "Analysis Method"));
		sampleAnalysis.setLor(dataValueHelper.getDataValueAsString(headers, datas, "LOR"));
		return sampleAnalysis;
	}

}
