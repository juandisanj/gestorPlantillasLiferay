<%@ include file="/init.jsp"%>
<div class="row" style="margin: 0.2em;">
	<div class="col-md-12">
		<div class="table-responsive">
			<table
				class="show-quick-actions-on-hover table table-autofit table-hover table-list table-nowrap">
				<thead>
					<tr class="text-center">
						<th id="check" scope="col"><input name="check"
							type="checkbox" id="checkAll" /> Todos</th>
						<th id="check" scope="col"><input name="check"
							type="checkbox" id="checkStrus" /> Estructuras</th>
						<th id="check" scope="col"><input name="check"
							type="checkbox" id="checkTemplates" /> Plantillas</th>
						<th id="check" scope="col"><input name="check"
							type="checkbox" id="checkAdts" /> ADTs</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<div class="row" style="margin: 0.2em;">
	<div class="col-md-3">
		<portlet:actionURL name="downloadTemplates" var="downloadTemplatesUrl" />
		<aui:form action="${downloadTemplatesUrl}">
			<div class="table-responsive">
				<table
					class="show-quick-actions-on-hover table table-autofit table-hover table-list table-nowrap">
					<thead>
						<tr class="text-center">
							<th colspan="2">Estructuras</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tema" items="${listadoStru}">
							<tr>
								<td><aui:input id="checkStru" name="idDato" type="checkbox"
									value="${tema.idDato}" label=" " /></td>
								<td class="text-left">${tema.nombreDato}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</div>
	<div class="col-md-3">
		<div class="table-responsive">
			<table
				class="show-quick-actions-on-hover table table-autofit table-hover table-list table-nowrap">
				<thead>
					<tr class="text-center">
						<th colspan="2">Plantillas</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tema" items="${listadoTemplates}">
						<tr>
							<td><aui:input id="checkTemp" name="idDato"
								type="checkbox" value="${tema.idDato}" label=" " /></td>
							<td class="text-left">${tema.nombreDato}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-3">
		<div class="table-responsive">
			<table
				class="show-quick-actions-on-hover table table-autofit table-hover table-list table-nowrap">
				<thead>
					<tr class="text-center">
						<th colspan="2">ADTs</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tema" items="${listadoAdts}">
						<tr>
							<td><aui:input id="checkAdt" name="idDato" type="checkbox"
								value="${tema.idDato}" label=" " /></td>
							<td class="text-left">${tema.nombreDato}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-3">
		<aui:button id="btncheck" name="downloadTemplatesButton" type="submit" value="Descargar archivo(s)" />
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

		$('#_sample_btncheck').prop("disabled", $('input:checkbox:checked').length == 0);

	}).change()
	
</script>