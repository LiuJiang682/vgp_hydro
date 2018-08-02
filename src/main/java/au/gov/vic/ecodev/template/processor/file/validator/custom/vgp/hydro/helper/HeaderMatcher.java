package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.helper;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class HeaderMatcher {

	private final String header;
	private final List<String> headers;
	
	public HeaderMatcher(String header, List<String> headers) {
		if (StringUtils.isBlank(header)) {
			throw new IllegalArgumentException("HeaderMatcher:header parameter cannot be null!");
		}
		this.header = header;
		if (CollectionUtils.isEmpty(headers)) {
			throw new IllegalArgumentException("HeaderMatcher:headers parameter cannot be null!");
		}
		this.headers = headers;
	}

	public boolean isOneOfHeaders() {
		return headers.stream()
				.anyMatch(header::equalsIgnoreCase);
	}
}
