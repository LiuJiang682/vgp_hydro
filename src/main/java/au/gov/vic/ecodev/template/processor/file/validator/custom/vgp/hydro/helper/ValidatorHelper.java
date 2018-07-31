package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ArrayUtils;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroTemplateValue;

public class ValidatorHelper {

	private final List<String> messages;
	private final String currentLine;
	private final boolean hasErrorMessage;
	private final int issueColumnIndex;
	
	public ValidatorHelper(final List<String> messages, final String currentLine, 
			final boolean hasErrorMessage) {
		this(messages, currentLine, hasErrorMessage, Numerals.NOT_FOUND);
	}

	public ValidatorHelper(final List<String> messages, final String currentLine,
			final boolean hasErrorMessage, final int issueColumnIndex) {
		if (null == messages) {
			throw new IllegalArgumentException("Parameter messages cannot be null!");
		}
		this.messages = messages;
		this.currentLine = currentLine;
		this.hasErrorMessage = hasErrorMessage;
		this.issueColumnIndex = issueColumnIndex;
	}

	public Optional<List<String>> updateDataBeanOrCreateErrorOptional(String[] datas, Template dataBean) {
		if (hasErrorMessage) {
			return Optional.of(messages);
		} else {
			if ((null != dataBean) 
					&& (ArrayUtils.isNotEmpty(datas))) {
				List<String> values = Arrays.asList(datas);
				VgpHydroTemplateValue value = new VgpHydroTemplateValue(values, issueColumnIndex);
				dataBean.put(currentLine, value);
			}
			return messages.isEmpty() ? Optional.empty() : Optional.of(messages);
		} 
	}

}
