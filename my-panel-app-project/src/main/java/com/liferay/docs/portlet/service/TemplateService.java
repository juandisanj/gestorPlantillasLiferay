package com.liferay.docs.portlet.service;

import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

public class TemplateService {
	
	
	public static List<com.liferay.dynamic.data.mapping.model.DDMTemplate> getTemplates() {
		List<com.liferay.dynamic.data.mapping.model.DDMTemplate> lista = DDMTemplateLocalServiceUtil.getDDMTemplates(0, Integer.MAX_VALUE); 
		return lista;
	}
	
	public static com.liferay.dynamic.data.mapping.model.DDMTemplate getTemplate(long templateId) throws PortalException {
		com.liferay.dynamic.data.mapping.model.DDMTemplate template = DDMTemplateLocalServiceUtil.getTemplate(templateId);
		return template;
	}
		
	
	public Long getTemplateId(com.liferay.dynamic.data.mapping.model.DDMTemplate template) throws PortalException {
		Long templateId = template.getTemplateId();
		return templateId;
	}
	
	public String getTemplateName(com.liferay.dynamic.data.mapping.model.DDMTemplate template) throws PortalException {
		String templateName = template.getName();
		return templateName;
	}
	
	public String getTemplateScript(com.liferay.dynamic.data.mapping.model.DDMTemplate template) throws PortalException {
		String templateScript = template.getScript();
		return templateScript;
	}
	
	public boolean getByClassName(com.liferay.dynamic.data.mapping.model.DDMTemplate template, String className){
		String clase = template.getClassName();
		if (clase == className) {
			return true;
		} else {
			return false;
		}
		
	}

}
