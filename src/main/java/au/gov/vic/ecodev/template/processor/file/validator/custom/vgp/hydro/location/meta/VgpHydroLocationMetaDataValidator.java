package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.location.meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.validator.Validator;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.MandatoryStringDataValidator;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper.ValidatorHelper;
import au.gov.vic.ecodev.utils.constants.Constants.Numeral;
import au.gov.vic.ecodev.utils.validator.common.ListSizeValidator;

public class VgpHydroLocationMetaDataValidator implements Validator {
	
	private static final String SINGLE_QUOTE = "\'";

	private static final String DOUBLE_QUOTE = "\"";

	private static final List<String> MANDATORY_FIELDS = Arrays.asList("SITE_ID");
	
	private static final List<String> NUMBER_FIELDS = Arrays.asList("EASTING", "NORTHING", "LATITUDE", "LONGITUDE", "KB", "ELEVATION", "BORE DIAMETER", "TD", "TVD");
	
	private static final String VGP_HYDRO_LOC_META = "vgphydroLocMeta";

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
			String message = "Location meta data record requires minimum 1 columns, only got 0";
			messages.add(message);
		} else if (strs.length < Numerals.ONE) {
			String message = "Location meta data record requires minimum 1 columns, only got " + strs.length;
			messages.add(message);
		} else {
			List<String> columnHeaders = templateParamMap.get(Strings.COLUMN_HEADERS);
			int columnCount = new ListSizeValidator(columnHeaders).validate(messages);
			if (Numeral.INVALID_COLUMN_COUNT != columnCount) {
				new MandatoryStringDataValidator(strs, currentLine, columnHeaders,
						VgpHydroLocationMetaHeaderValidator.SITE_ID, VGP_HYDRO_LOC_META).validate(messages);
				doOptionalNumberFieldValidation(messages, columnHeaders);
			}
		}
		
		return new ValidatorHelper(messages, currentLine, false)
				.updateDataBeanOrCreateErrorOptional(strs, dataBean);
	}

	protected final void doOptionalNumberFieldValidation(List<String> messages, final List<String> columnHeaders) {
		AtomicInteger headerIndex = new AtomicInteger();
		columnHeaders.stream()
			.forEach(header -> {
				if ((!isMandatoryField(header)) 
						&& (isNumberField(header))) {
					int arrayIndex = headerIndex.get();
					if (arrayIndex < strs.length) {
						String value = strs[arrayIndex];
						if (!isNumeralValueOrNull(value)) {
							String message = new StringBuilder(header)
									.append(" is expected a number value, but got: ")
									.append(value)
									.toString();
							messages.add(message);
							
						}
					}
				}
				headerIndex.incrementAndGet();
			});
	}

	protected final boolean isNumeralValueOrNull(String value) {
		boolean flag = true;
		if (StringUtils.isNotBlank(value)) {
			value = value.replaceAll(Strings.COMMA, Strings.EMPTY);
			value = value.replaceAll(DOUBLE_QUOTE, Strings.EMPTY);
			value = value.replaceAll(SINGLE_QUOTE, Strings.EMPTY);
			if ((!NumberUtils.isParsable(value)) && (!NumberUtils.isCreatable(value))) {
				flag = false;
			}
		}
		return flag;
	}

	protected final boolean isNumberField(final String header) {
		return NUMBER_FIELDS.contains(header.toUpperCase());
	}

	protected final boolean isMandatoryField(final String header) {
		return MANDATORY_FIELDS.contains(header.toUpperCase());
	}
}
