<%@ include file="/init.jsp"%>

<portlet:actionURL name="downloadTemplates" var="downloadTemplatesUrl"/>
<aui:form action="${downloadTemplatesUrl}">
	<c:forEach var="tema" items="${listadoTemplates}">
		<aui:row>
			<aui:col width="<%= 5 %>">
				<aui:input name="idDato" type="checkbox" value="${tema.idDato}"/>
			</aui:col>
			<aui:col width="<%= 95 %>">
				<aui:input name="" type="text" label="${tema.nombreDato}"/>
			</aui:col>
		</aui:row>
	</c:forEach>
	<aui:button name="downloadTemplatesButton" type="submit" value="Descargar temas"/>
</aui:form>
