package com.liferay.docs.portlet;

import com.liferay.docs.constants.SamplePortletKeys;
import com.liferay.docs.model.ResumenDatos;
import com.liferay.docs.portlet.service.ResumenDatosService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Edgar
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SamplePortletKeys.Sample,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supports.mime-type=text/html",
		"language.id=es_ES"
	},
	service = Portlet.class
)
public class SamplePortlet extends MVCPortlet {
	
	ResumenDatosService resumenDatosService = new ResumenDatosService();

	public void render (RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {               
        
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long userId = td.getUserId();
		long groupId = td.getSiteGroupId();
		
		List<ResumenDatos> resumenDatosStru = resumenDatosService.getStructures(groupId, userId);
		List<ResumenDatos> resumenDatosTempl = resumenDatosService.getTemplates(groupId, userId);
		List<ResumenDatos> resumenDatosAdts = resumenDatosService.getAdts(groupId, userId);
		renderRequest.setAttribute("listadoStru", resumenDatosStru);
		renderRequest.setAttribute("listadoTemplates", resumenDatosTempl);
		renderRequest.setAttribute("listadoAdts", resumenDatosAdts);        
        
        super.render(renderRequest, renderResponse);
	}
}
