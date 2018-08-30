package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.builder;

import java.util.ArrayList;
import java.util.List;

import au.gov.vic.ecodev.common.util.IDGenerator;
import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleMeta;
import au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.helper.DataValueHelper;

public class SampleMetaBuilder {

	private static final String HEADER_SAMPLE_ARE_DESCRIPTION = "Sample_Are_Description";
	private static final String HEADER_REFERENCE = "Reference";
	private static final String HEADER_PUMPING_DEPTH = "Pumping_Depth";
	private static final String HEADER_STANDING_WATER_LEVEL = "Standing_Water_Level";
	private static final String HEADER_SAMPLE_BOTTOM = "Sample_Bottom";
	private static final String HEADER_SAMPLE_TOP = "Sample_Top";
	private static final String HEADER_IGSN = "IGSN";
	private static final String HEADER_SAMPLED_DATE = "Sampled_Date";
	private static final String HEADER_PROPARATION_CODE = "Proparation Code";
	private static final String HEADER_TYPE = "Type";
	private static final String HEADER_LAB_CODE = "Lab_Code";
	private static final String HEADER_CORE_ID = "Core_ID";
	private static final String HEADER_SAMPLE_ID = "Sample_ID";
	private static final String HEADER_SITE_ID = "SITE_ID";
	
	private final long sessionId;
	private final List<String> headers;
	private final List<String> datas;
	private final String fileName;
	private final int index;
	
	public SampleMetaBuilder(final long sessionId, final List<String> headers, 
			final List<String> datas, final String fileName, final int index) {
		this.sessionId = sessionId;
		this.headers = headers;
		if (null == datas) {
			this.datas = new ArrayList<>();
		} else {
			this.datas = datas;
		}
		this.fileName = fileName;
		this.index = index;
	}
	
	public SampleMeta build() {
		DataValueHelper dataValueHelper = new DataValueHelper();
		SampleMeta sampleMeta = new SampleMeta();
		sampleMeta.setId(IDGenerator.getUIDAsAbsLongValue());
		sampleMeta.setLoaderId(sessionId);
		sampleMeta.setSiteId(dataValueHelper
				.getDataValueAsLong(headers, datas, HEADER_SITE_ID));
		sampleMeta.setSampleId(dataValueHelper
				.getDataValueAsLong(headers, datas, HEADER_SAMPLE_ID));
		sampleMeta.setFileName(fileName);
		sampleMeta.setRowNumber(String.valueOf(index));
		sampleMeta.setCoreId(dataValueHelper
				.getDataValueAsLong(headers, datas, HEADER_CORE_ID));
		sampleMeta.setLabCode(dataValueHelper
				.getDataValueAsString(headers, datas, HEADER_LAB_CODE));
		sampleMeta.setType(dataValueHelper
				.getDataValueAsString(headers, datas, HEADER_TYPE));
		sampleMeta.setPrepCode(dataValueHelper
				.getDataValueAsString(headers, datas, HEADER_PROPARATION_CODE));
		sampleMeta.setSampleDate(dataValueHelper
				.getDataValueAsTimestampToDate(headers, datas, HEADER_SAMPLED_DATE));
		sampleMeta.setIgsn(dataValueHelper.getDataValueAsString(headers, datas, HEADER_IGSN));
		sampleMeta.setSampleTop(dataValueHelper
				.getDataValueAsBigDecimal(headers, datas, HEADER_SAMPLE_TOP));
		sampleMeta.setSampleBottom(dataValueHelper
				.getDataValueAsBigDecimal(headers, datas, HEADER_SAMPLE_BOTTOM));
		sampleMeta.setStandardWaterLevel(dataValueHelper
				.getDataValueAsBigDecimal(headers, datas, HEADER_STANDING_WATER_LEVEL));
		sampleMeta.setPumpingDepth(dataValueHelper
				.getDataValueAsBigDecimal(headers, datas, HEADER_PUMPING_DEPTH));
		sampleMeta.setReference(dataValueHelper
				.getDataValueAsString(headers, datas, HEADER_REFERENCE));
		sampleMeta.setSampleAreaDesc(dataValueHelper
				.getDataValueAsString(headers, datas, HEADER_SAMPLE_ARE_DESCRIPTION));
		return sampleMeta;
	}
}
