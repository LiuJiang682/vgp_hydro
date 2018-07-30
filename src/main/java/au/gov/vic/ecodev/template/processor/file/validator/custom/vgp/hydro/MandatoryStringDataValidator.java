package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.VgpHydroSamplesMetaHeaderPredicate;

public class MandatoryStringDataValidator {

	private final String[] strs;
	private final String lineNumber;
	private final List<String> columnHeaders;
	private final String code;
	private final String templateName;
	
	public MandatoryStringDataValidator(final String[] strs, final String lineNumber, 
			final List<String> columnHeaders, final String code,
			final String templateName) {
		this.strs = strs;
		this.lineNumber = lineNumber;
		this.columnHeaders = columnHeaders;
		this.code = code;
		this.templateName = templateName;
	}

	public void validate(List<String> messages) {
		OptionalInt indexOpt = IntStream.range(Numerals.ZERO, columnHeaders.size())
				.filter(i -> VgpHydroSamplesMetaHeaderPredicate.isMatched(code).test(columnHeaders.get(i)))
				.findFirst();
				
		if (indexOpt.isPresent()) {
			int index = indexOpt.getAsInt();
			String string = null;
			if (index < strs.length) {
				string = strs[index];
			}
			if (StringUtils.isEmpty(string)) {
				String message = new StringBuilder(Strings.LOG_ERROR_HEADER)
					.append("Line ")
					.append(lineNumber)
					.append(": Template ")
					.append(templateName)
					.append(" column ")
					.append(code)
					.append(" cannot be null or empty")
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
