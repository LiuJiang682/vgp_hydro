package au.gov.vic.ecodev.template.mail.custom.vgp.hydro;

import org.apache.commons.lang3.StringUtils;

import au.gov.vic.ecodev.mrt.mail.EmailBodyBuilder;
import au.gov.vic.ecodev.mrt.template.processor.context.TemplateProcessorContext;

public class VgpHydroEmailBodyBuilder implements EmailBodyBuilder {

	private TemplateProcessorContext templateProcessorContext;
	
	@Override
	public void setTemplateProcessorContext(TemplateProcessorContext templateProcessorContext) {
		this.templateProcessorContext = templateProcessorContext;
	}

	@Override
	public String build() {
		if (null == templateProcessorContext) {
			throw new IllegalArgumentException("Parameter templateLoaderStateMachineContext cannot be null!");
		}
		String directErrorMessage = templateProcessorContext.getMessage().getDirectErrorMessage();
		if (StringUtils.isEmpty(directErrorMessage)) {
			StringBuilder buf = new StringBuilder("Hi\n");
			buf.append("\n");
			buf.append("The log file for batch: ");
			buf.append(templateProcessorContext.getMessage().getBatchId());
			buf.append(" is available at ");
			buf.append(templateProcessorContext.getMessage().getLogFileName());
			buf.append("\n\n");
			buf.append("The successfull processed files at ");
			buf.append(templateProcessorContext.getMessage().getPassedFileDirectory());
			buf.append("\n\n");
			buf.append("The failed processed files at ");
			buf.append(templateProcessorContext.getMessage().getFailedFileDirectory());
			
			return buf.toString();
		} else {
			StringBuilder buf = new StringBuilder("Hi\n");
			buf.append("\n");
			buf.append("The template file process is failed due to: ");
			buf.append(directErrorMessage);
			return buf.toString();
		}
	}

}
