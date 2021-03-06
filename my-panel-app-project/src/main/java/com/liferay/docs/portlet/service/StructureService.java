package com.liferay.docs.portlet.service;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

public class StructureService {
	
	public static List<DDMStructure> getStructures() {
		List<DDMStructure> lista = DDMStructureLocalServiceUtil.getDDMStructures(0, Integer.MAX_VALUE); 
		return lista;
	}
	
	public List<DDMStructure> getStructuresByGroupId(long groupId, long userId){
		
		List<DDMStructure> listStructures = new ArrayList<>();
		
		for(DDMStructure structure : getStructures()) {
			if (structure.getUserId() == userId && structure.getGroupId() == groupId) {
				listStructures.add(structure);
			}
		}
		
		return listStructures;
	}
	
	public DDMStructure getTemplate(long structureId) throws PortalException {
		DDMStructure structure = DDMStructureLocalServiceUtil.getDDMStructure(structureId);
		return structure;
	}
	
	public DDMStructure getStructure(long structureId) throws PortalException {
		DDMStructure structure = DDMStructureLocalServiceUtil.getStructure(structureId);
		return structure;
	}
	
	public String getStructureName(DDMStructure structure) throws PortalException {
		String structureName = structure.getName();
		return structureName;
	}
	
	public String getStructureScript(DDMStructure structure) throws PortalException {
		String structureScript = structure.getDefinition();
		return structureScript;
	}
	
	public boolean getByClassName(DDMStructure structure, String className){
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
