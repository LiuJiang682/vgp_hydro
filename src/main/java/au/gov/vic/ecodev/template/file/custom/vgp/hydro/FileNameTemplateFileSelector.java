package au.gov.vic.ecodev.template.file.custom.vgp.hydro;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import au.gov.vic.ecodev.template.constants.Constants.Strings;
import au.gov.vic.ecodev.mrt.template.file.TemplateFileSelector;
import au.gov.vic.ecodev.utils.file.DirectoryFilesScanner;

public class FileNameTemplateFileSelector implements TemplateFileSelector {

	private static final String UNDER_LINE = "_";
	
	private String directory;
	
	@Override
	public void setSelectionFileDirectory(String directory) {
		this.directory = directory;
	}

	@Override
	public Optional<List<String>> getTemplateFileInDirectory(List<String> dataTemplate) throws Exception {
		if (CollectionUtils.isEmpty(dataTemplate)) {
			throw new IllegalArgumentException("FileNameTemplateFileSelector:getTemplateFileInDirectory -- dataTemplate parameter cannot be null!");
		}
		if (StringUtils.isEmpty(directory)) {
			throw new IllegalArgumentException("FileNameTemplateFileSelector:getTemplateFileInDirectory -- directory parameter cannot be null!");
		}
		
		List<File> files = new DirectoryFilesScanner(directory).scan();
		return findTemplateFileName(files, dataTemplate);
	}

	protected final Optional<List<String>> findTemplateFileName(List<File> files, List<String> dataTemplate) {
		Optional<List<String>> templateFileName = Optional.empty();
		List<String> fileNames = new ArrayList<>();
		files.stream()
			.forEach(file ->{
				String fileName = file.getName().toUpperCase();
				dataTemplate.stream()
					.forEach(template -> {
						String prefix = template + UNDER_LINE;
						if (fileName.startsWith(prefix.toUpperCase())) {
							fileNames.add(template + Strings.SPACE + file.getAbsolutePath());
						}
					});
			});
		if (CollectionUtils.isNotEmpty(fileNames)) {
			templateFileName = Optional.of(fileNames);
		}
		return templateFileName;
	}

}
