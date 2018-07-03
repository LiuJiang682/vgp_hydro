package au.gov.vic.ecodev.template.processor.file.custom.vgp.hydro;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import au.gov.vic.ecodev.utils.file.finder.DirectoryTreeReverseTraversalZipFileFinder;
import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;
import au.gov.vic.ecodev.mrt.template.processor.model.Template;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.HydroTemplate;

public class VgpHydroFileParser {

	private static final Logger LOGGER = Logger.getLogger(VgpHydroFileParser.class);
	
	private final File file;
	private final TemplateProcessorContext templateProcessorContext;
	
	public VgpHydroFileParser(File file, TemplateProcessorContext templateProcessorContext) {
		if (null == file) {
			throw new IllegalArgumentException("VgpHydroFileParser:file parameter cannot be null!");
		}
		this.file = file;
		if (null == templateProcessorContext) {
			throw new IllegalArgumentException("VgpHydroFileParser:templateProcessorContext parameter cannot be null!");
		}
		this.templateProcessorContext = templateProcessorContext;
	}

	public void parse() throws Exception {
		Map<String, List<String>> templateParamMap = new HashMap<>();
		Template dataBean = new HydroTemplate();
		String zipFile = new DirectoryTreeReverseTraversalZipFileFinder(file.getParent()).findZipFile();
		LineNumberReader lineNumberReader = getLineNumberReader();
		
		String line;
		while(null != (line = lineNumberReader.readLine())) {
			LOGGER.info(line);
		}
	}

	private LineNumberReader getLineNumberReader() throws Exception {
		return new LineNumberReader(new FileReader(file.getAbsolutePath()));
	}
}
