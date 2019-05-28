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

		// Averiguar como se distinguen
		// Recorrer la lista comprobando esa diferencia
		// Crear una lista para cada tipo de archivo

		// Tranformar los modelos recibidos en el objeto original, llamando a los
		// servicios

		// Una vez creadas las listas de elementos definitivos, llamar al servicio de
		// utilidades
		// para comprimir y generar los archivos

		

		if (listTemplates.size() != 0) {
			// Crear una carpeta que contenga las plantillas
			// nombre de la carpeta es la fecha actual
			File folder = null;
			folder = DownloadFilesZipUtil.createFolder("ftl");

			// Recorrer la lista de plantillas, añadiendo cada plantilla a la carpeta recién
			// creada

			for (DDMTemplate template : listTemplates) {
				String ddmt = template.getScript();
				try {
					DownloadFilesZipUtil.createFile(folder, template.getTemplateKey(), ddmt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

			for (DDMTemplate template : listAdts) {
				String ddmt = template.getScript();
				try {
					DownloadFilesZipUtil.createFile(folder, template.getTemplateKey(), ddmt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

			for (DDMStructure template : listStructures) {
				String ddmt = template.getDefinition();
				try {
					DownloadFilesZipUtil.createFile(folder, template.getStructureKey(), ddmt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DownloadFilesZipUtil.exportFolderToZip(folder);
		}

		actionResponse.setRenderParameter("mvcRenderCommandName", "/");

		return true;
	}

}
