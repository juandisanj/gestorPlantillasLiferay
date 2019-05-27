package com.liferay.docs.portlet.service;

import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

public class TemplateService {
	
	public static List<com.liferay.dynamic.data.mapping.model.DDMTemplate> getTemplates() {
		List<com.liferay.dynamic.data.mapping.model.DDMTemplate> lista = DDMTemplateLocalServiceUtil.getDDMTemplates(0, Integer.MAX_VALUE); 
		return lista;
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

}
