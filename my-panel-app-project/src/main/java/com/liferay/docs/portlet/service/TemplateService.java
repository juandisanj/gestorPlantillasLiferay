package com.liferay.docs.portlet.service;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

public class TemplateService {
	
	private DDMTemplateLocalServiceUtil templateService = new DDMTemplateLocalServiceUtil();
	
	
	public List<DDMTemplate> getTemplates() {
		List<DDMTemplate> lista = templateService.getDDMTemplates(0, Integer.MAX_VALUE); 
		return lista;
	}
	
	public List<DDMTemplate> getTemplatesByGroupId(long groupId, long userId){
		List<DDMTemplate> listTemplateGroup = new ArrayList<>();
		
		List<DDMTemplate> listDDMTemplate = getTemplates();
		
		for(DDMTemplate template : listDDMTemplate) {
			
			if (template.getUserId() == userId) {
				listTemplateGroup.add(template);
			}
		}
		
		Long id = listTemplateGroup.get(0).getClassNameId();
		
		List<DDMTemplate> listTemp = new ArrayList<>();
		List<DDMTemplate> listAdt = new ArrayList<>();
		
		for(DDMTemplate temp : listTemplateGroup) {
			if(temp.getClassNameId() == id) {
				listTemp.add(temp);
			}else if(temp.getClassNameId() != id) {
				listAdt.add(temp);
			}
		}
		
		return listTemplateGroup;
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
