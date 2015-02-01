<%@page import="pl.skimina.ParametrySymulacji"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script src="js/jquery-1.11.2.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#plynWewn").change(function(){
				if( $("#plynWewn").val() == 0){
					$("#ro_t").show();
					$("#c_t").show();
				}else{
					$("#ro_t").hide();
					$("#c_t").hide();
				} 
				
			});
			


			$("#ro_t").hide();
			$("#c_t").hide();


		}); 
	</script>

	<head>
	<body>
	<div>	
		<div id="titlediv">
			<span>Symulator wymiennika ciepła typu rura w rurze</span>
		</div>
		<form method="post" action="calculations.do" target="_blank">
			<table id="params">
				<tr id="head1">
					<td colspan=2 class="head">Płyn wewnątrz</td>
				</tr>
				<tr class="row">
					<td>Temperatura</td>
					<td><input type="text" name="<%=ParametrySymulacji.TEMP_T%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Rodzaj płynu</td>
					<td>
					<select id="plynWewn" name="<%=ParametrySymulacji.PLYN_WEW%>" >
						<c:forEach var="material" items="${material_wew}">
							<option value="${material.code}">${material.name}</option>
						</c:forEach>
						
					</select>
					</td>
				</tr>
				<tr id="ro_t" class="row">
					<td>Gęstość</td>
					<td><input type="text" name="<%=ParametrySymulacji.RO_T%>" value="0"/></td>
				</tr>
				<tr id="c_t" class="row">
					<td>Ciepło Właściwe</td>
					<td><input type="text" name="<%=ParametrySymulacji.C_T%>" value="0"/></td>
				</tr>
	
	
				<tr id="head2">
					<td colspan="2" class="head">Ścianka wewn</td>
				</tr>
				<tr class="row">
					<td>Temperatura</td>
					<td><input type="text" name="<%=ParametrySymulacji.TEMP_P%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Gęstość</td>
					<td><input type="text" name="<%=ParametrySymulacji.RO_P%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Ciepło Właściwe</td>
					<td><input type="text" name="<%=ParametrySymulacji.C_P%>" value="0"/></td>
				</tr>
	
	
	
				<tr id="head3">
					<td colspan="2" class="head">Płyn zewnetrzny</td>
				</tr>
				<tr class="row">
					<td>Temperatura</td>
					<td><input type="text" name="<%=ParametrySymulacji.TEMP_S%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Gęstość</td>
					<td><input type="text" name="<%=ParametrySymulacji.RO_S%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Ciepło Właściwe</td>
					<td><input type="text" name="<%=ParametrySymulacji.C_S%>" value="0"/></td>
				</tr>
	
	
	
				<tr id="head4">
					<td colspan="2" class="head">scianka zewnetrzna</td>
				</tr>
				<tr class="row">
					<td>Temperatura</td>
					<td><input type="text" name="<%=ParametrySymulacji.TEMP_Z%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Gęstość</td>
					<td><input type="text" name="<%=ParametrySymulacji.RO_Z%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Ciepło Właściwe</td>
					<td><input type="text" name="<%=ParametrySymulacji.C_Z%>" value="0"/></td>
				</tr>
	
	
				<tr id="head5">
					<td colspan="2" class="head">Inne Parametry</td>
				</tr>
				<tr class="row">
					<td>Średnica D1</td>
					<td><input type="text" name="<%=ParametrySymulacji.D1%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Średnica D2</td>
					<td><input type="text" name="<%=ParametrySymulacji.D2%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Średnica D3</td>
					<td><input type="text" name="<%=ParametrySymulacji.D3%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Średnica D4</td>
					<td><input type="text" name="<%=ParametrySymulacji.D4%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Prędkość płynu wewnętrznego</td>
					<td><input type="text" name="<%=ParametrySymulacji.V_T%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Prędkość płynu zewnętrznego</td>
					<td><input type="text" name="<%=ParametrySymulacji.V_S%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Długość wymiennika</td>
					<td><input type="text" name="<%=ParametrySymulacji.DLUGOSC_WYMIENNIKA%>" value="0"/></td>
				</tr>
				<tr class="row">
					<td>Ilość sekcji</td>
					<td><input type="text" name="<%=ParametrySymulacji.ILOSC_SEKCJI%>" value="0"/></td>
				</tr>
	
				<tr id="head6">
					<td colspan="2" class="head"><input type="submit" value="Uruchom obliczenia"/></td>
				</tr>
			</table>
		</form>

	</div>
	</body>
</html>