package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import au.gov.vic.ecodev.common.util.IDGenerator;
import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleMeta;
import au.gov.vic.ecodev.mrt.template.processor.exception.TemplateProcessorException;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.persistent.Dao;
import au.gov.vic.ecodev.mrt.template.processor.update.TemplateUpdater;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.VgpHydroSamplesMetaHeaderPredicate;
import au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro.VgpHydroSamplesMetaDaoImpl;

public class VgpHydroSamplesMetaTemplateUpdater implements TemplateUpdater {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroSamplesMetaTemplateUpdater.class);
	
	private List<Dao> daos;
	
	@Override
	public void setDaos(List<Dao> daos) {
		LOGGER.info("Inside VgpHydroSamplesMetaTemplateUpdater.setDaos");
		this.daos = daos;
	}

	@Override
	public void update(final long sessionId, final Template template) 
			throws TemplateProcessorException {
		LOGGER.info("Inside VgpHydroSamplesMetaTemplateUpdater.update");
		
		if (CollectionUtils.isEmpty(daos)) {
			throw new TemplateProcessorException("Dao list cannot be null or empty!");
		}
		
		List<String> headers = template.get(Strings.STRING_ZERO);
		int len = template.getKeys().size();
		Dao dao = daos.get(Numerals.ZERO);
		for(int index = Numerals.ONE; index < len; index++) {
			List<String> datas = template.get(String.valueOf(index));
			SampleMeta sampleMeta = convertToSampleMeta(sessionId, headers, datas);
			dao.updateOrSave(sampleMeta);
		}
	}

	protected final SampleMeta convertToSampleMeta(final long sessionId, 
			final List<String> headers, 
			final List<String> datas) {
		SampleMeta sampleMeta = new SampleMeta();
		sampleMeta.setId(IDGenerator.getUID().longValue());
		sampleMeta.setLoaderId(sessionId);
		sampleMeta.setSiteId(getDataValueAsLong(headers, datas, "SITE_ID"));
		sampleMeta.setSampleId(getDataValueAsLong(headers, datas, "Sample_ID"));
		sampleMeta.setCoreId(getDataValueAsLong(headers, datas, "Core_ID"));
		sampleMeta.setLabCode(getDataValueAsString(headers, datas, "Lab_Code"));
		return sampleMeta;
	}

	protected final String getDataValueAsString(final List<String> headers, 
			final List<String> datas, final String fieldName) {
		String value = null;
		OptionalInt indexOpt = IntStream.range(Numerals.ZERO, headers.size())
				.filter(i -> VgpHydroSamplesMetaHeaderPredicate.isMatched(fieldName)
						.test(headers.get(i)))
				.findFirst();
		if (indexOpt.isPresent()) {
			int index = indexOpt.getAsInt();
			if (index < datas.size()) {
				value = datas.get(index);
			}
		}
		return value;
	}

	protected final long getDataValueAsLong(final List<String> headers, 
			final List<String> datas, final String fieldName) {
		long value = Numerals.NOT_FOUND;
		OptionalInt indexOpt = IntStream.range(Numerals.ZERO, headers.size())
				.filter(i -> VgpHydroSamplesMetaHeaderPredicate.isMatched(fieldName)
						.test(headers.get(i)))
				.findFirst();
		if (indexOpt.isPresent()) {
			int index = indexOpt.getAsInt();
			if (index < datas.size()) {
				try {
					value = Long.parseLong(datas.get(index));
				} catch(NumberFormatException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
		return value;
	}

	@Override
	public List<Class<? extends Dao>> getDaoClasses() {
		LOGGER.info("Inside VgpHydroSamplesMetaTemplateUpdater.getDaoClasses");
		List<Class<? extends Dao>> daoClasses = new ArrayList<>();
		daoClasses.add(VgpHydroSamplesMetaDaoImpl.class);
		return daoClasses;
	}

}
