package au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import au.gov.vic.ecodev.mrt.template.processor.model.TemplateValue;

public class VgpHydroTemplateValue implements TemplateValue, Serializable {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -5446260102996877008L;

	private final List<String> datas;
	private final int issueColumnIndex;
	
	public VgpHydroTemplateValue(final List<String> datas, final int issueColumnIndex) {
		this.datas = datas;
		this.issueColumnIndex = issueColumnIndex;
	}

	public List<String> getDatas() {
		return datas;
	}
	
	public int getIssueColumnIndex() {
		return issueColumnIndex;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
