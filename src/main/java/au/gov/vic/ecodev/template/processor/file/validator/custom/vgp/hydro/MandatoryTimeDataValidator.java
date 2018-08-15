package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.OptionalInt;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.helper.HeaderHelper;

public class MandatoryTimeDataValidator {

	private final String timeFormat;
	private final String[] strs;
	private final String lineNumber;
	private final List<String> columnHeaders;
	private final String code;
	private final String templateName;
	
	public MandatoryTimeDataValidator(final String timeFormat, final String[] strs, 
			final String lineNumber, final List<String> columnHeaders, final String code,
			final String templateName) {
		if (StringUtils.isEmpty(timeFormat)) {
			throw new IllegalArgumentException("MandatoryTimeDataValidator:timeFormat parameter cannot be null or empty!");
		}
		this.timeFormat = timeFormat;
		this.strs = strs;
		this.lineNumber = lineNumber;
		this.columnHeaders = columnHeaders;
		this.code = code;
		this.templateName = templateName;
	}

	public void validate(List<String> messages) {
		new MandatoryStringDataValidator(strs, lineNumber, columnHeaders, code, templateName)
			.validate(messages);
		if (messages.isEmpty()) {
			OptionalInt indexOpt = new HeaderHelper(code).findHeaderIndex(columnHeaders);
			if (indexOpt.isPresent()) {
				int index = indexOpt.getAsInt();
				String string = null;
				if (index < strs.length) {
					string = strs[index];
				}
				SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
				try {
					sdf.parse(string);
				} catch (Exception e) {
					String message = new StringBuilder(Strings.LOG_ERROR_HEADER)
							.append("Line ")
							.append(lineNumber)
							.append(": Template ")
							.append(templateName)
							.append(" column ")
							.append(code)
							.append(" expected in ")
							.append(timeFormat)
							.append(" format, but got: ")
							.append(string)
							.toString();
					messages.add(message);
				}
			}
			
		}
		
	}

}
