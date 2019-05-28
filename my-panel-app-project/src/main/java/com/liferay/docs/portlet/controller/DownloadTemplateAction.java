package com.liferay.docs.portlet.controller;

import com.liferay.docs.constants.SamplePortletKeys;
import com.liferay.docs.portlet.service.TemplateService;
import com.liferay.docs.portlet.utils.DownloadFilesZipUtil;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name="+ SamplePortletKeys.Sample,
			"mvc.command.name=downloadTemplates"
		},
		service = MVCActionCommand.class
	)

public class DownloadTemplateAction implements MVCActionCommand {
	
	private static final Log _log = new LogFactoryUtil().getLog(DownloadTemplateAction.class);
	
	private TemplateService tempService = new TemplateService();

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

		_log.info("Method DownloadTemplateAction.processAction: Descarga de ficheros seleccionados");
		
		// Sustituir la lista completa por el listado recibido de sde el formulario
		actionRequest.getAttribute("listTemplate");
		List<DDMTemplate> listTemplate = tempService.getTemplates();
		
		File folder = null;
		
		if(listTemplate.size() != 0) {
			// Crear una carpeta que contenga las plantillas
			// nombre de la carpeta es la fecha actual
			folder = DownloadFilesZipUtil.createFolder();
			
			// Recorrer la lista de plantillas, añadiendo cada plantilla a la carpeta recién creada
					
			for(DDMTemplate template : listTemplate) {
				String ddmt = template.getScript();
				try {
					DownloadFilesZipUtil.createFile(folder, template.getTemplateKey(), ddmt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		
		}
		
		DownloadFilesZipUtil.exportFolderToZip(folder);
	
		actionResponse.setRenderParameter("mvcRenderCommandName", "/");
		
		return true;
	}

}

