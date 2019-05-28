package com.liferay.docs.portlet.service;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

public class StructureService {
	
	public static List<com.liferay.dynamic.data.mapping.model.DDMStructure> getStructures() {
		List<com.liferay.dynamic.data.mapping.model.DDMStructure> lista = DDMStructureLocalServiceUtil.getDDMStructures(0, Integer.MAX_VALUE); 
		return lista;
	}
	
	public com.liferay.dynamic.data.mapping.model.DDMStructure getTemplate(long structureId) throws PortalException {
		com.liferay.dynamic.data.mapping.model.DDMStructure structure = DDMStructureLocalServiceUtil.getDDMStructure(structureId);
		return structure;
	}
	
	public DDMStructure getStructure(long structureId) throws PortalException {
		com.liferay.dynamic.data.mapping.model.DDMStructure structure = DDMStructureLocalServiceUtil.getStructure(structureId);
		return structure;
	}
	
	public String getStructureName(com.liferay.dynamic.data.mapping.model.DDMStructure structure) throws PortalException {
		String structureName = structure.getName();
		return structureName;
	}
	
	public String getStructureScript(com.liferay.dynamic.data.mapping.model.DDMStructure structure) throws PortalException {
		String structureScript = structure.getDefinition();
		return structureScript;
	}
	
	public boolean getByClassName(com.liferay.dynamic.data.mapping.model.DDMStructure structure, String className){
		String clase = structure.getClassName();
		if (clase == className) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public DDMStructure getStructureById(long structureId) throws PortalException {
		DDMStructure structure = DDMStructureLocalServiceUtil.getDDMStructure(structureId);
		
		return structure;
	}

}
