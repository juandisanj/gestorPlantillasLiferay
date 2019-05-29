<%@ include file="/init.jsp"%>

<div class="row" style="margin:0.2em;">

    <div class ="col-md-9" style="margin:0.2em;">

        <h3><input name="check" type="checkbox" id="checkAll"/> Listado de Plantillas, ADT y Estructuras:</h3>

        <portlet:actionURL name="downloadTemplates" var="downloadTemplatesUrl"/>

        <aui:form action="${downloadTemplatesUrl}">

            <div class="d-flex flex-column">

                <c:forEach var="tema" items="${listadoTemplates}">

                    <aui:input name="idDato" type="checkbox" value="${tema.idDato}" label="${tema.nombreDato}"/>

                </c:forEach>              

            </div>

            <aui:button name="downloadTemplatesButton" type="submit" value="Descargar temas"/>

        </aui:form>

    </div>

    <portlet:actionURL name="selectTypeTemplates" var="selectTypeTemplatesUrl"/>

    <div class="col-md-3" style="margin:0.2em;">

        <aui:form action="${selectTypeUrl}">

            <aui:select name="tipoRecogido">

                <c:forEach var="tipo" items="${listaTipos}">

                    <aui:option value="${tipo}">${tipo}</aui:option>

                </c:forEach>

            </aui:select>

            <aui:button name="selectTypeButton" type="submit" value="Filtrar"/>

        </aui:form>

    </div>

</div>

<script>

$("#checkAll").click(function () {

    $('input:checkbox').not(this).prop('checked', this.checked);

});

</script>