package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.List;

public enum SiteIdRepository {

	INSTANCE;
	
	private SiteIdRepository() {
		siteIds = new ArrayList<>();
	}
	
	private List<String> siteIds;
	
	public void add(final String siteId) {
		siteIds.add(siteId);
	}
	
}
