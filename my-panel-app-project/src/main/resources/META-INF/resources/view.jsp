<%@ include file="/init.jsp"%>
<portlet:actionURL name="${downloadTemplates}" var="downloadTemplatesUrl"/>
<aui:form action="<%= downloadTemplatesUrl.toString() %>" method="post">
	<c:forEach var="tema" items="${listadoTemplates}">
		<aui:row>
			<aui:col width="<%= 5 %>">
				<aui:input name="" type="checkbox" value="${tema.templateId}"/>
			</aui:col>
			<aui:col width="<%= 95 %>">
				<aui:input name="" type="text" label="${tema.name}"/>
			</aui:col>
		</aui:row>
	</c:forEach>
	<aui:button name="downloadTemplates" type="submit" value="Descargar temas"/>
</aui:form>
