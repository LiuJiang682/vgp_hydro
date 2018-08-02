package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.MandatoryNumberDataValidator;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.MandatoryStringDataValidator;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper.ValidatorHelper;
import au.gov.vic.ecodev.utils.constants.Constants.Numeral;
import au.gov.vic.ecodev.utils.validator.common.ListSizeValidator;
import au.gov.vic.ecodev.utils.validator.helper.ErrorMessageChecker;

public class VgpHydroSamplesMetaDataValidator  implements Validator {
	
	private static final String VGP_HYDRO_SAMPLES_META = "vgphydroSampMeta";
	
	private String[] strs;

	@Override
	public void init(String[] strs) {
		this.strs = strs;
	}

	@Override
	public Optional<List<String>> validate(Map<String, List<String>> templateParamMap, Template dataBean) {
		List<String> messages = new ArrayList<>();
		List<String> currentLineList = templateParamMap.get(Strings.CURRENT_LINE);
		String currentLine = Strings.STRING_ZERO;
		if (CollectionUtils.isNotEmpty(currentLineList)) {
			currentLine = currentLineList.get(Numerals.ZERO);
		}
		
		if (null == strs) {
			String message = "Samples meta data record requires minimum 8 columns, only got 0";
			messages.add(message);
		} else if (strs.length < Numerals.EIGHT) {
			String message = "Samples meta data record requires minimum 8 columns, only got " + strs.length;
			messages.add(message);
		} else {
			List<String> columnHeaders = templateParamMap.get(Strings.COLUMN_HEADERS);
			int columnCount = new ListSizeValidator(columnHeaders).validate(messages);
			if (Numeral.INVALID_COLUMN_COUNT != columnCount) {
				new MandatoryStringDataValidator(strs, currentLine, columnHeaders,
						VgpHydroSamplesMetaMandatoryHeaders.SITE_ID.getDisplayLabel(), 
						VGP_HYDRO_SAMPLES_META).validate(messages);
				new SiteIdLocationValidator(strs, currentLine, columnHeaders,
						VGP_HYDRO_SAMPLES_META).validate(messages);
				new MandatoryStringDataValidator(strs, currentLine, columnHeaders,
						VgpHydroSamplesMetaMandatoryHeaders.SAMPLE_ID.getDisplayLabel(), 
						VGP_HYDRO_SAMPLES_META).validate(messages);
				new MandatoryStringDataValidator(strs, currentLine, columnHeaders,
						VgpHydroSamplesMetaMandatoryHeaders.IGSN.getDisplayLabel(), 
						VGP_HYDRO_SAMPLES_META).validate(messages);
				new MandatoryNumberDataValidator(strs, currentLine, columnHeaders, 
						VgpHydroSamplesMetaMandatoryHeaders.SAMPLE_TOP.getDisplayLabel(),
						VGP_HYDRO_SAMPLES_META).validate(messages);
				new MandatoryNumberDataValidator(strs, currentLine, columnHeaders, 
						VgpHydroSamplesMetaMandatoryHeaders.SAMPLE_BOTTOM.getDisplayLabel(),
						VGP_HYDRO_SAMPLES_META).validate(messages);
				new MandatoryNumberDataValidator(strs, currentLine, columnHeaders, 
						VgpHydroSamplesMetaMandatoryHeaders.STANDING_WATER_LEVEL.getDisplayLabel(),
						VGP_HYDRO_SAMPLES_META).validate(messages);
				new MandatoryNumberDataValidator(strs, currentLine, columnHeaders, 
						VgpHydroSamplesMetaMandatoryHeaders.PUMPING_DEPTH.getDisplayLabel(),
						VGP_HYDRO_SAMPLES_META).validate(messages);
				new MandatoryStringDataValidator(strs, currentLine, columnHeaders,
						VgpHydroSamplesMetaMandatoryHeaders.REFERENCE.getDisplayLabel(), 
						VGP_HYDRO_SAMPLES_META).validate(messages);
			}
		}
		
		boolean hasErrorMessage = new ErrorMessageChecker(messages).isContainsErrorMessages();
		return new ValidatorHelper(messages, currentLine, hasErrorMessage)
				.updateDataBeanOrCreateErrorOptional(strs, dataBean);
	}

}
