package au.gov.vic.ecodev.template.processor.updater.custom.vgp.hydro.helper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.OptionalInt;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta.helper.HeaderHelper;

public class DataValueHelper {

	private static final Logger LOGGER = Logger.getLogger(DataValueHelper.class);
	
	public final Timestamp getDataValueAsTimestamp(final List<String> headers, 
			final List<String> datas, final String fieldName) {
		String value = null;
		OptionalInt indexOpt = new HeaderHelper(fieldName).findHeaderIndex(headers);
		if (indexOpt.isPresent()) {
			int index = indexOpt.getAsInt();
			if (index < datas.size()) {
				value = datas.get(index);
			}
		}
		try {
			if (StringUtils.isNotBlank(value)) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date = sdf.parse(value);
				return new Timestamp(date.getTime());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	public final String getDataValueAsString(final List<String> headers, 
			final List<String> datas, final String fieldName) {
		String value = null;
		OptionalInt indexOpt = new HeaderHelper(fieldName).findHeaderIndex(headers);
		if (indexOpt.isPresent()) {
			int index = indexOpt.getAsInt();
			if (index < datas.size()) {
				value = datas.get(index);
			}
		}
		return value;
	}
	
	public final long getDataValueAsLong(final List<String> headers, 
			final List<String> datas, final String fieldName) {
		long value = Numerals.NOT_FOUND;
		OptionalInt indexOpt = new HeaderHelper(fieldName).findHeaderIndex(headers);
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
	
	public BigDecimal getDataValueAsBigDecimal(final List<String> headers,
			final List<String> datas, final String fieldName) {
		String valueString = getDataValueAsString(headers, datas, fieldName);
		BigDecimal value = new BigDecimal(String.valueOf(Numerals.NOT_FOUND));
		if (NumberUtils.isParsable(valueString) 
				|| (NumberUtils.isCreatable(valueString))) {
			value = new BigDecimal(valueString);
		}
		return value;
	}
}
