package com.liferay.docs.portlet.service;

import com.liferay.docs.model.ResumenDatos;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResumenDatosService {
	
	private TemplateService templateService = new TemplateService();
	private StructureService structureService = new StructureService();
	
	public List<ResumenDatos> getAll() {
		
		List<ResumenDatos> resumenDatos = new ArrayList<>();
		List<DDMTemplate> listaTemplates = TemplateService.getTemplates();
		List<com.liferay.dynamic.data.mapping.model.DDMStructure> listaStructures = StructureService.getStructures();
		
		for (DDMTemplate template: listaTemplates) {
			ResumenDatos dato = new ResumenDatos();
			dato.setIdDato(template.getTemplateId());
			dato.setNombreDato(template.getName("Es_es"));
			dato.setScriptDatos(template.getScript());
			dato.setClaseDato(template.getClassName());
			
			resumenDatos.add(dato);
		}
		
		for (com.liferay.dynamic.data.mapping.model.DDMStructure structure: listaStructures) {
			ResumenDatos dato = new ResumenDatos();
			dato.setIdDato(structure.getStructureId());
			dato.setNombreDato(structure.getName("Es_es"));
			dato.setScriptDatos(structure.getDefinition());
			dato.setClaseDato(structure.getClassName());
			
			resumenDatos.add(dato);
		}
		return resumenDatos;	
		
	}
	
	public List<ResumenDatos> getByClassName(String className){
		
		List<DDMTemplate> listaTemplates = TemplateService.getTemplates();
		List<ResumenDatos> resumenDatos = new ArrayList<>();
		List<com.liferay.dynamic.data.mapping.model.DDMStructure> listaStructures = StructureService.getStructures();
		
		for (DDMTemplate template: listaTemplates) {
			ResumenDatos dato = new ResumenDatos();
			if (templateService.getByClassName(template, className) == true) {
				dato.setIdDato(template.getTemplateId());
				dato.setNombreDato(template.getName("Es_es"));
				dato.setScriptDatos(template.getScript());
				dato.setClaseDato(template.getClassName());
				
				resumenDatos.add(dato);				
			}
		}
		
		for (com.liferay.dynamic.data.mapping.model.DDMStructure structure: listaStructures) {
			ResumenDatos dato = new ResumenDatos();
			if (structureService.getByClassName(structure, className) == true) {
				dato.setIdDato(structure.getStructureId());
				dato.setNombreDato(structure.getName("Es_es"));
				dato.setScriptDatos(structure.getDefinition());
				dato.setClaseDato(structure.getClassName());
				
				resumenDatos.add(dato);
			}
		}
		
		return resumenDatos;
		
	}

}
