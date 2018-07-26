package au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.mrt.template.processor.model.TemplateValue;
import au.gov.vic.ecodev.template.constants.Constants.Numerals;

public class VgpHydroLocMetaTemplate implements Template {

	private Map<String, VgpHydroTemplateValue> datas = new HashMap<>();
	
	@Override
	public void put(String key, List<String> values) {
		VgpHydroTemplateValue vgpHydroTemplateValue = new VgpHydroTemplateValue(values, Numerals.NOT_FOUND);
		datas.put(key, vgpHydroTemplateValue);
	}

	@Override
	public List<String> get(String key) {
		VgpHydroTemplateValue vgpHydroTemplateValue = datas.get(key);
		return (null == vgpHydroTemplateValue) ? null : vgpHydroTemplateValue.getDatas();
	}

	@Override
	public List<String> getKeys() {
		return new ArrayList<String>(datas.keySet());
	}

	@Override
	public void put(String key, TemplateValue value) {
		datas.put(key, ((VgpHydroTemplateValue)value));
	}

	@Override
	public TemplateValue getTemplateValue(String key) {
		return (TemplateValue) datas.get(key);
	}

}
