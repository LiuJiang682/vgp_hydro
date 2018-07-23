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
	private final boolean hasErrorMessage;
	private final int issueColumnIndex;
	
	public ValidatorHelper(final List<String> messages, 
			final boolean hasErrorMessage) {
		this(messages, hasErrorMessage, Numerals.NOT_FOUND);
	}

	public ValidatorHelper(List<String> messages, boolean hasErrorMessage, int issueColumnIndex) {
		if (null == messages) {
			throw new IllegalArgumentException("Parameter messages cannot be null!");
		}
		this.messages = messages;
		this.hasErrorMessage = hasErrorMessage;
		this.issueColumnIndex = issueColumnIndex;
	}

	public Optional<List<String>> updateDataBeanOrCreateErrorOptional(String[] datas, Template dataBean) {
		if (hasErrorMessage) {
			return Optional.of(messages);
		} else {
			if ((null != dataBean) 
					&& (ArrayUtils.isNotEmpty(datas)) 
					&& (Numerals.TWO <= datas.length)) {
				String[] newDatas = Arrays.copyOfRange(datas, Numerals.ONE, datas.length);
				List<String> values = Arrays.asList(newDatas);
				VgpHydroTemplateValue value = new VgpHydroTemplateValue(values, issueColumnIndex);
				dataBean.put(datas[Numerals.ZERO], value);
			}
			return messages.isEmpty() ? Optional.empty() : Optional.of(messages);
		} 
	}

}
