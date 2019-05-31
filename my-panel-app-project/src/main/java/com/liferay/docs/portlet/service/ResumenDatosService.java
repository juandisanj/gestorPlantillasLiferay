package com.liferay.docs.portlet.service;

import com.liferay.docs.model.ResumenDatos;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResumenDatosService {
	
	private TemplateService templateService = new TemplateService();
	private StructureService structureService = new StructureService();
	
	public List<ResumenDatos> getAll(long groupId, long userId) {
		
		
		List<ResumenDatos> listResu = new ArrayList<>();
		List<ResumenDatos> listTemplates = new ArrayList<>();
		
		for(DDMTemplate template: templateService.getTemplatesByGroupId(groupId, userId)) {
			ResumenDatos dato = new ResumenDatos();
			dato.setIdDato(template.getTemplateId());
			dato.setNombreDato(template.getName("Es_es"));
			dato.setScriptDatos(template.getScript());
			dato.setClaseDato(template.getClassNameId());
			
			listResu.add(dato); 
		}
		
		long classId = listResu.get(0).getClaseDato();
		
		for(ResumenDatos template : listResu) {
			if(classId != template.getClaseDato()){
				template.setTipo("ftl");
				listTemplates.add(template);
			}else {
				template.setTipo("adt");
				listTemplates.add(template);
			}
		}
		
		for (DDMStructure structure: structureService.getStructuresByGroupId(groupId, userId)) {
			ResumenDatos dato = new ResumenDatos();
			dato.setIdDato(structure.getStructureId());
			dato.setNombreDato(structure.getName("Es_es"));
			dato.setScriptDatos(structure.getDefinition());
			dato.setClaseDato(structure.getClassNameId());
			dato.setTipo("structure");
			
			listTemplates.add(dato);
		}
		
		return listTemplates;
	}
	
	public ResumenDatos getDatoById(long idDato, long groupId, long userId) {
		ResumenDatos dato = new ResumenDatos();
		
		for(ResumenDatos rd : getAll(groupId, userId)) {
			if(rd.getIdDato() == idDato) {
				dato = rd;
			}
		}
		return dato;
	}
	
	public List<ResumenDatos> getTemplates(long groupId, long userId) {
		List<ResumenDatos> listTemplates = new ArrayList<>();
		
		for(ResumenDatos template : getAll(groupId, userId)) {
			if("ftl".equals(template.getTipo())){
				listTemplates.add(template);
			}
		}
		
		return listTemplates;
	}
	
	public List<ResumenDatos> getAdts(long groupId, long userId){
		List<ResumenDatos> listAdts = new ArrayList<>();
		
		for(ResumenDatos adt : getAll(groupId, userId)) {
			if("adt".equals(adt.getTipo())){
				listAdts.add(adt);
			}
		}
		
		return listAdts;
	}
	
	public List<ResumenDatos> getStructures(long groupId, long userId){
		List<ResumenDatos> listStructures = new ArrayList<>();
		
		for(ResumenDatos strs : getAll(groupId, userId)) {
			if("structure".equals(strs.getTipo())){
				listStructures.add(strs);
			}
		}
		
		return listStructures;
	}

}
