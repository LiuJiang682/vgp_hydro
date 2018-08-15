package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.observations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.MandatoryStringDataValidator;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper.ValidatorHelper;
import au.gov.vic.ecodev.utils.constants.Constants.Numeral;
import au.gov.vic.ecodev.utils.validator.common.ListSizeValidator;
import au.gov.vic.ecodev.utils.validator.helper.ErrorMessageChecker;

public class VgpHydroObservationsDataValidator implements Validator {

	private static final String VGP_HYDRO_OBSERVATIONS = "vgphydroObservation";
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
			String message = "Observations data record requires minimum 3 columns, only got 0";
			messages.add(message);
		} else if (strs.length < Numerals.FOUR) {
			String message = "Observations data record requires minimum 3 columns, only got " + strs.length;
			messages.add(message);
		} else {
			List<String> columnHeaders = templateParamMap.get(Strings.COLUMN_HEADERS);
			int columnCount = new ListSizeValidator(columnHeaders).validate(messages);
			if (Numeral.INVALID_COLUMN_COUNT != columnCount) {
				new MandatoryStringDataValidator(strs, currentLine, columnHeaders,
						VgpHydroObservationsMandatoryHeaders.SITE_ID.getCode(), 
						VGP_HYDRO_OBSERVATIONS).validate(messages);
				new MandatoryStringDataValidator(strs, currentLine, columnHeaders,
						VgpHydroObservationsMandatoryHeaders.IGSN.getCode(), 
						VGP_HYDRO_OBSERVATIONS).validate(messages);
				new MandatoryStringDataValidator(strs, currentLine, columnHeaders,
						VgpHydroObservationsMandatoryHeaders.SAMPLE_TIME.getCode(), 
						VGP_HYDRO_OBSERVATIONS).validate(messages);
			}
		}
		
		boolean hasErrorMessage = new ErrorMessageChecker(messages).isContainsErrorMessages();
		return new ValidatorHelper(messages, currentLine, hasErrorMessage)
				.updateDataBeanOrCreateErrorOptional(strs, dataBean);
	}

}
