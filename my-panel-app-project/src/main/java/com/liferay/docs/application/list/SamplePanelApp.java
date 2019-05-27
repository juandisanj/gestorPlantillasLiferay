package com.liferay.docs.application.list;

import com.liferay.docs.constants.SamplePanelCategoryKeys;
import com.liferay.docs.constants.SamplePortletKeys;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Edgar
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + SamplePanelCategoryKeys.CONTROL_PANEL_CATEGORY
	},
	service = PanelApp.class
)
public class SamplePanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return SamplePortletKeys.Sample;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + SamplePortletKeys.Sample + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}