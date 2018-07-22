package au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.model.TemplateValue;

public class VgpHydroTemplate implements Template {

	private Map<String, List<String>> datas = new HashMap<>();
	
	@Override
	public void put(String key, List<String> values) {
		datas.put(key, values);
	}

	@Override
	public List<String> get(String key) {
		return datas.get(key);
	}

	@Override
	public List<String> getKeys() {
		return new ArrayList<String>(datas.keySet());
	}

	@Override
	public void put(String key, TemplateValue value) {
		datas.put(key, ((VgpHydroTemplateValue)value).getDatas());
	}

	@Override
	public TemplateValue getTemplateValue(String key) {
		return new VgpHydroTemplateValue(get(key));
	}

}
