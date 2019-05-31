package com.liferay.docs.portlet.service;

import com.liferay.docs.model.ResumenDatos;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResumenDatosService {
	
	private TemplateService templateService = new TemplateService();
	private StructureService structureService = new StructureService();
	
	public List<ResumenDatos> getAll() {
		
		List<ResumenDatos> resumenDatos = new ArrayList<>();
		List<DDMTemplate> listaTemplates = templateService.getTemplates();
		List<DDMStructure> listaStructures = structureService.getStructures();
		
		for (DDMTemplate template: listaTemplates) {
			ResumenDatos dato = new ResumenDatos();
			dato.setIdDato(template.getTemplateId());
			dato.setNombreDato(template.getName("Es_es"));
			dato.setScriptDatos(template.getScript());
			dato.setClaseDato(template.getClassNameId());

			resumenDatos.add(dato);
		}
		
		for (DDMStructure structure: listaStructures) {
			ResumenDatos dato = new ResumenDatos();
			dato.setIdDato(structure.getStructureId());
			dato.setNombreDato(structure.getName("Es_es"));
			dato.setScriptDatos(structure.getDefinition());
			dato.setClaseDato(structure.getClassNameId());
			dato.setTipo("structure");
			
			resumenDatos.add(dato);
		}
		return resumenDatos;	
	}
	
	public List<ResumenDatos> getAll2(long groupId, long userId) {
		
		List<DDMStructure> listaStructures = structureService.getStructures();
		List<DDMTemplate> listaDDMTemplates = templateService.getTemplatesByGroupId(groupId, userId);
		
		List<ResumenDatos> listResu = new ArrayList<>();
		List<ResumenDatos> listTemplates = new ArrayList<>();
		List<ResumenDatos> listStructures = new ArrayList<>();
		
		for(DDMTemplate template: listaDDMTemplates) {
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
		
		for (DDMStructure structure: listaStructures) {
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
	
	public List<ResumenDatos> getByClassName(String className){
		
		List<DDMTemplate> listaTemplates = templateService.getTemplates();
		List<ResumenDatos> resumenDatos = new ArrayList<>();
		List<DDMStructure> listaStructures = StructureService.getStructures();
		
		for (DDMTemplate template: listaTemplates) {
			ResumenDatos dato = new ResumenDatos();
			if (templateService.getByClassName(template, className) == true) {
				dato.setIdDato(template.getTemplateId());
				dato.setNombreDato(template.getName("Es_es"));
				dato.setScriptDatos(template.getScript());
				dato.setClaseDato(template.getClassNameId());
				
				resumenDatos.add(dato);				
			}
		}
		
		for (DDMStructure structure: listaStructures) {
			ResumenDatos dato = new ResumenDatos();
			if (structureService.getByClassName(structure, className) == true) {
				dato.setIdDato(structure.getStructureId());
				dato.setNombreDato(structure.getName("Es_es"));
				dato.setScriptDatos(structure.getDefinition());
				dato.setClaseDato(structure.getClassNameId());
				
				resumenDatos.add(dato);
			}
		}
		return resumenDatos;
	}
	
	public List<ResumenDatos> getAllByGroupId(long groupId, long userId) {
		
		List<ResumenDatos> resumenDatos = new ArrayList<>();
		List<DDMTemplate> listaTemplates = templateService.getTemplatesByGroupId(groupId, userId);
		List<DDMStructure> listaStructures = structureService.getStructuresByGroupId(groupId);
		
		for (DDMTemplate template: listaTemplates) {
			ResumenDatos dato = new ResumenDatos();
			dato.setIdDato(template.getTemplateId());
			dato.setNombreDato(template.getName("Es_es"));
			dato.setScriptDatos(template.getScript());
			dato.setClaseDato(template.getClassNameId());
			
			resumenDatos.add(dato);
		}
		
		for (DDMStructure structure: listaStructures) {
			ResumenDatos dato = new ResumenDatos();
			dato.setIdDato(structure.getStructureId());
			dato.setNombreDato(structure.getName("Es_es"));
			dato.setScriptDatos(structure.getDefinition());
			dato.setClaseDato(structure.getClassNameId());
			dato.setTipo("structure");
			
			resumenDatos.add(dato);
		}
		return resumenDatos;	
		
	}
	
	public ResumenDatos getDatoById(long idDato, long groupId, long userId) {
		ResumenDatos dato = new ResumenDatos();
		
		for(ResumenDatos rd : getAll2(groupId, userId)) {
			if(rd.getIdDato() == idDato) {
				dato = rd;
			}
		}
		return dato;
	}
	
	public List<ResumenDatos> getTemplates(long groupId, long userId) {
		List<ResumenDatos> listResu = new ArrayList<>();
		List<DDMTemplate> listaDDMTemplates = templateService.getTemplatesByGroupId(groupId, userId);
		List<ResumenDatos> listTemplates = new ArrayList<>();
		
		for(DDMTemplate template: listaDDMTemplates) {
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
			}
		}
		
		return listTemplates;
	}
	
	public List<ResumenDatos> getAdts(long groupId, long userId){
		List<ResumenDatos> listResu = new ArrayList<>();
		List<DDMTemplate> listaDDMTemplates = templateService.getTemplatesByGroupId(groupId, userId);
		List<ResumenDatos> listAdts = new ArrayList<>();
		
		for(DDMTemplate template: listaDDMTemplates) {
			ResumenDatos dato = new ResumenDatos();
			dato.setIdDato(template.getTemplateId());
			dato.setNombreDato(template.getName("Es_es"));
			dato.setScriptDatos(template.getScript());
			dato.setClaseDato(template.getClassNameId());
			
			listResu.add(dato); 
		}
		long classId = listResu.get(0).getClaseDato();
		for(ResumenDatos template : listResu) {
			if(classId == template.getClaseDato()){
				template.setTipo("adt");
				listAdts.add(template);
			}
		}
		
		return listAdts;
	}
	
	public List<ResumenDatos> getStructures(long groupId, long userId){
		List<ResumenDatos> listStructures = new ArrayList<>();
		
		for(ResumenDatos strs : getAllByGroupId(groupId, userId)) {
			if("structure".equals(strs.getTipo())){
				listStructures.add(strs);
			}
		}
		
		return listStructures;
	}

}
