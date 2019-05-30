package com.liferay.docs.portlet.service;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

public class TemplateService {
	
	
	public static List<DDMTemplate> getTemplates() {
		List<com.liferay.dynamic.data.mapping.model.DDMTemplate> lista = DDMTemplateLocalServiceUtil.getDDMTemplates(0, Integer.MAX_VALUE); 
		return lista;
	}
	
	public static DDMTemplate getTemplate(long templateId) throws PortalException {
		DDMTemplate template = DDMTemplateLocalServiceUtil.getTemplate(templateId);
		return template;
	}
		
	
	public Long getTemplateId(DDMTemplate template) throws PortalException {
		Long templateId = template.getTemplateId();
		return templateId;
	}
	
	public String getTemplateName(DDMTemplate template) throws PortalException {
		String templateName = template.getName();
		return templateName;
	}
	
	public String getTemplateScript(DDMTemplate template) throws PortalException {
		String templateScript = template.getScript();
		return templateScript;
	}
	
	public boolean getByClassName(DDMTemplate template, String className){
		String clase = template.getClassName();
		if (clase == className) {
			return true;
		} else {
			return false;
		}
		
	}

}
