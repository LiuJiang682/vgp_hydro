package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro;

import java.util.List;
import java.util.OptionalInt;

import org.apache.commons.lang3.math.NumberUtils;

import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.helper.HeaderHelper;

public class MandatoryNumberDataValidator {

	private final String[] strs;
	private final String lineNumber;
	private final List<String> columnHeaders;
	private final String code;
	private final String templateName;
	
	public MandatoryNumberDataValidator(final String[] strs, final String lineNumber, 
			final List<String> columnHeaders, final String code,
			final String templateName) {
		this.strs = strs;
		this.lineNumber = lineNumber;
		this.columnHeaders = columnHeaders;
		this.code = code;
		this.templateName = templateName;
	}

	public void validate(List<String> messages) {
		OptionalInt indexOpt = new HeaderHelper(code).findHeaderIndex(columnHeaders);
		
		if (indexOpt.isPresent()) {
			int index = indexOpt.getAsInt();
			String string = null;
			if (index < strs.length) {
				string = strs[index];
			}
			if((!NumberUtils.isParsable(string)) 
					&& (!NumberUtils.isCreatable(string))) {
				String message = new StringBuilder(Strings.LOG_ERROR_HEADER)
						.append("Line ")
						.append(lineNumber)
						.append(": Template ")
						.append(templateName)
						.append(" column ")
						.append(code)
						.append(" must be a number, but got: ")
						.append(string)
						.toString();
				messages.add(message);
			}
		} else {
			messages.add(constructMissingHeaderMessage(lineNumber));
			return;
		}
	}
	
	private String constructMissingHeaderMessage(String currentLineNumber) {
		String message = new StringBuilder(Strings.LOG_ERROR_HEADER)
				.append("Line ")
				.append(currentLineNumber)
				.append(": Template ")
				.append(templateName)
				.append(" missing ")
				.append(code)
				.append(" column")
				.toString();
		return message;
	}
}
