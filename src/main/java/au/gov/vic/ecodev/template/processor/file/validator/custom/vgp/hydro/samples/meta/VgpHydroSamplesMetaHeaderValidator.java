package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper.ValidatorHelper;
import au.gov.vic.ecodev.utils.validator.helper.ErrorMessageChecker;

public class VgpHydroSamplesMetaHeaderValidator  implements Validator {
	
	private String[] strs;
	
	@Override
	public void init(String[] strs) {
		this.strs = strs;	
	}

	@Override
	public Optional<List<String>> validate(Map<String, List<String>> templateParamMap, Template dataBean) {
		List<String> messages = new ArrayList<>();
		String currentLine = Strings.STRING_ZERO;
		List<String> currentLineList = templateParamMap.get(Strings.CURRENT_LINE);
		if (CollectionUtils.isNotEmpty(currentLineList)) {
			currentLine = currentLineList.get(Numerals.ZERO);
		}
		List<String> headers = null;
		
		if (null == strs) {
			String message = "Header requires minimum 8 columns, only got 0";
			messages.add(message);
		} else if (strs.length < Numerals.EIGHT) {
			String message = "Header requires minimum 8 columns, only got " + strs.length;
			messages.add(message);
		} else {
			headers = Arrays.asList(strs);
			doMandatoryFieldCheck(messages, headers);
		}
		
		boolean hasErrorMessage = new ErrorMessageChecker(messages).isContainsErrorMessages();
		if (!hasErrorMessage) {
			templateParamMap.put(Strings.COLUMN_HEADERS, headers);
		} 
		return new ValidatorHelper(messages, currentLine, hasErrorMessage)
				.updateDataBeanOrCreateErrorOptional(strs, dataBean);
	}

	protected final void doMandatoryFieldCheck(List<String> messages, List<String> strs) {
		for(VgpHydorSamplesMetaMandatoryHeaders header : VgpHydorSamplesMetaMandatoryHeaders.values()) {
			String mandatoryHeaderString = header.getDisplayLabel();
			if (!strs.stream().anyMatch(mandatoryHeaderString::equalsIgnoreCase)) {
				String message = new StringBuilder("Sample Meta Data Header requires the ")
						.append(mandatoryHeaderString)
						.append(" column")
						.toString();
				messages.add(message);
			}
		}
	}

}
