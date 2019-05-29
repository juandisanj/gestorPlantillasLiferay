<%@ include file="/init.jsp"%>
<div class="row" style="margin: 0.2em;">
	<div class="col-md-4" style="margin: 0.2em;">
		<portlet:actionURL name="downloadTemplates" var="downloadTemplatesUrl" />
		<aui:form action="${downloadTemplatesUrl}">
			<table class="table-responsive-md ">
				<table class="table table-striped">
					<thead>
						<tr>
							<th id="check" scope="col"><input name="check"
								type="checkbox" id="checkAll" /></th>
								<th scope="col">Todos</th>
						</tr>
					</thead>
				</table>
				<table class="table table-striped">
					<thead>
						<tr>
							<th id="check" scope="col"><input name="check"
								type="checkbox" id="checkStrus" /></th>
								<th scope="col">Estructuras</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tema" items="${listadoStru}">
							<tr>
								<td style="width: 0.2em;"><aui:input id="checkStru" name="idDato" type="checkbox"
										value="${tema.idDato}" label=" " /></td>
								<td>${tema.nombreDato}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table class="table table-striped">
					<thead>
						<tr>
							<th id="check" scope="col"><input name="check"
								type="checkbox" id="checkTemplates" /></th>
								<th scope="col">Plantillas</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tema" items="${listadoTemplates}">
							<tr>
								<td style="width: 0.2em;"><aui:input id="checkTemp" name="idDato" type="checkbox"
										value="${tema.idDato}" label=" " /></td>
								<td>${tema.nombreDato}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table class="table table-striped">
					<thead>
						<tr>
							<th id="check" scope="col"><input name="check"
								type="checkbox" id="checkAdts" /></th>
								<th scope="col">ADTs</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tema" items="${listadoAdts}">
							<tr>
								<td style="width: 0.2em;"><aui:input id="checkAdt" name="idDato" type="checkbox"
										value="${tema.idDato}" label=" " /></td>
								<td>${tema.nombreDato}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</table>
			<aui:button id="btncheck" name="downloadTemplatesButton"
				type="submit" value="Descargar archivo(s)" />
		</aui:form>
	</div>
</div>
<script>
	$("#checkAll").click(function() {
		$('input:checkbox').not(this).prop('checked', this.checked);
	});

	$("#checkTemplates").click(function() {
		$("input#_sample_checkTemp").not(this).prop('checked', this.checked);
	});

	$("#checkAdts").click(function() {
		$("input#_sample_checkAdt").not(this).prop('checked', this.checked);
	});

	$("#checkStrus").click(function() {
		$("input#_sample_checkStru").not(this).prop('checked', this.checked);
	});

	$('input:checkbox').change(function() {
		$('#_sample_btncheck').prop("disabled", !this.checked);
	}).change()
</script>