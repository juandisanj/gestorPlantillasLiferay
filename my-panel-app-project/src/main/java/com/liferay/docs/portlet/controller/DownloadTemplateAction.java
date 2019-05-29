package com.liferay.docs.portlet.controller;

import com.liferay.docs.constants.SamplePortletKeys;
import com.liferay.docs.model.ResumenDatos;
import com.liferay.docs.portlet.service.ResumenDatosService;
import com.liferay.docs.portlet.service.StructureService;
import com.liferay.docs.portlet.service.TemplateService;
import com.liferay.docs.portlet.utils.DownloadFilesZipUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + SamplePortletKeys.Sample,
		"mvc.command.name=downloadTemplates" }, service = MVCActionCommand.class)

public class DownloadTemplateAction implements MVCActionCommand {

	private static final Log _log = new LogFactoryUtil().getLog(DownloadTemplateAction.class);

	private TemplateService tempService = new TemplateService();
	private ResumenDatosService resumenDatosService = new ResumenDatosService();
	private StructureService structureService = new StructureService();

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

		_log.info("Method DownloadTemplateAction.processAction: Descarga de ficheros seleccionados");

		String[] listChecks = actionRequest.getParameterValues("idDato");
		// Convertir los ids en un listado de ResumenDatos
		List<ResumenDatos> listCheckTemplates = new ArrayList<>();
		for (String s : listChecks) {
			ResumenDatos dato = resumenDatosService.getDatoById(Long.parseLong(s));
			listCheckTemplates.add(dato);
		}

		List<DDMTemplate> listTemplates = new ArrayList<>();
		List<DDMTemplate> listAdts = new ArrayList<>();
		List<DDMStructure> listStructures = new ArrayList<>();

		// Identificar tipo de archivo / archivos recibidos

		for (ResumenDatos dato : listCheckTemplates) {
			try {
				if ("ftl".equals(dato.getTipo())) {
					DDMTemplate template = tempService.getTemplate(dato.getIdDato());
					listTemplates.add(template);
				} else if ("adt".equals(dato.getTipo())) {
					DDMTemplate adt = tempService.getTemplate(dato.getIdDato());
					listAdts.add(adt);
				} else if ("structure".equals(dato.getTipo())) {
					DDMStructure structure = structureService.getStructure(dato.getIdDato());
					listStructures.add(structure);
				}
			} catch (PortalException e) {
				e.getMessage();
			}
		}

		if (listTemplates.size() != 0) {
			// Crear una carpeta que contenga las plantillas
			// nombre de la carpeta es la fecha actual
			File folder = null;
			folder = DownloadFilesZipUtil.createFolder("ftl");

			// Recorrer la lista de plantillas, añadiendo cada plantilla a la carpeta recién
			// creada

			List<String> listNames = new ArrayList<>();
			for (DDMTemplate template : listTemplates) {
				String ddmt = template.getScript();
				listNames.add(template.getTemplateKey());
				
				int count = checkDuplicates(listNames, template.getTemplateKey());
				if(count == 1) {
					try {
						DownloadFilesZipUtil.createFile(folder, template.getTemplateKey(), ddmt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					try {
						DownloadFilesZipUtil.createFile(folder, template.getTemplateKey() + "_" + String.valueOf(count), ddmt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			DownloadFilesZipUtil.exportFolderToZip(folder);
		}
		if (listAdts.size() != 0) {
			// Crear una carpeta que contenga las plantillas
			// nombre de la carpeta es la fecha actual
			File folder = null;
			folder = DownloadFilesZipUtil.createFolder("adt");

			// Recorrer la lista de plantillas, añadiendo cada plantilla a la carpeta recién
			// creada

			List<String> listNames = new ArrayList<>();
			for (DDMTemplate template : listAdts) {
				String ddmt = template.getScript();
				listNames.add(template.getTemplateKey());
				
				int count = checkDuplicates(listNames, template.getTemplateKey());
				
				if(count == 1) {
					try {
						DownloadFilesZipUtil.createFile(folder, template.getTemplateKey(), ddmt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					try {
						DownloadFilesZipUtil.createFile(folder, template.getTemplateKey() + "_" + String.valueOf(count), ddmt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			DownloadFilesZipUtil.exportFolderToZip(folder);
		}
		if (listStructures.size() != 0) {
			// Crear una carpeta que contenga las plantillas
			// nombre de la carpeta es la fecha actual
			File folder = null;
			folder = DownloadFilesZipUtil.createFolder("str");

			// Recorrer la lista de plantillas, añadiendo cada plantilla a la carpeta recién
			// creada

			List<String> listNames = new ArrayList<>();
			
			for (DDMStructure structure : listStructures) {
				String ddmt = structure.getDefinition();
				
				listNames.add(structure.getStructureKey());
				
				int count = checkDuplicates(listNames, structure.getStructureKey());
				
				if(count == 1) {
					try {
						DownloadFilesZipUtil.createFile(folder, structure.getStructureKey(), ddmt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					try {
						DownloadFilesZipUtil.createFile(folder, structure.getStructureKey() + "_" + String.valueOf(count), ddmt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				
			}
			DownloadFilesZipUtil.exportFolderToZip(folder);
		}

		actionResponse.setRenderParameter("mvcRenderCommandName", "/");

		return true;
	}
	
	private int checkDuplicates(List<String> listNames, String name) {
		int count = 0;
		for(String s : listNames) {
			if(s.equals(name)) {
				count++;
			}
		}
		return count;
	}

}
