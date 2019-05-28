package com.liferay.docs.portlet;

import com.liferay.docs.constants.SamplePortletKeys;
import com.liferay.docs.model.ResumenDatos;
import com.liferay.docs.portlet.service.ResumenDatosService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(

	    immediate = true,

	    property = {

	       "javax.portlet.name=" + SamplePortletKeys.Sample,

	       "mvc.command.name=/listadoClase"

	    },

	    service = MVCRenderCommand.class

	)

public class ByClassRender implements MVCRenderCommand {

	ResumenDatosService resumenDatosService = new ResumenDatosService();

	public String render (RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		String className = renderRequest.getParameter("className");
		 
		List<ResumenDatos> resumenDatos = resumenDatosService.getByClassName(className);
		
		renderRequest.setAttribute("listadoTemplates", resumenDatos);
		
		
		return "/listadoClase.jsp";		

    }

}
