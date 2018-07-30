package au.gov.vic.ecodev.template.constants;

import au.gov.vic.ecodev.mrt.constants.LogSeverity;

public interface Constants {

	interface Strings {
		static final String COLON = ":";
		static final String SPACE = " ";
		static final String LOG_INFO_HEADER = LogSeverity.INFO.name() + COLON + SPACE;
		static final String LOG_WARNING_HEADER = LogSeverity.WARNING.name() + COLON + SPACE;
		static final String LOG_ERROR_HEADER = LogSeverity.ERROR.name() + COLON + SPACE;
		static final String CURRENT_LINE = "currentLine";
		static final String TAB = "\t";
		static final String STRING_ZERO = "0";
		static final String COLUMN_HEADERS = "Column_headers";
		static final String EMPTY = "";
		static final String COMMA = ",";
		static final String UNDER_LINE = "_";
	}
	
	interface Numerals {
		static final int NEGATIVE_ONE = -1;
		static final int NOT_FOUND = NEGATIVE_ONE;
		static final int ZERO = 0;
		static final int ONE = 1;
		static final int TWO = 2;
		static final int THREE = 3;
		static final int SEVEN = 7;
		static final int EIGHT = 8;
		static final int NINE = 9;
		static final int TEN = 10;
		static final int ELEVEN = 11;
		static final int TWELVE = 12;
	}
}
