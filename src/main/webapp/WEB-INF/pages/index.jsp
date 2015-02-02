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
			
			$("#ruraWewn").change(function(){
				if( $("#ruraWewn").val() == 0){
					$("#ro_p").show();
					$("#c_p").show();
				}else{
					$("#ro_p").hide();
					$("#c_p").hide();
				} 
				
			});
			
			$("#plynZewn").change(function(){
				if( $("#plynZewn").val() == 0){
					$("#ro_s").show();
					$("#c_s").show();
				}else{
					$("#ro_s").hide();
					$("#c_s").hide();
				} 
				
			});
			
			$("#ruraZewn").change(function(){
				if( $("#ruraZewn").val() == 0){
					$("#ro_z").show();
					$("#c_z").show();
				}else{
					$("#ro_z").hide();
					$("#c_z").hide();
				} 
				
			});
			


			$("#ro_t").hide();
			$("#c_t").hide();
			
			$("#ro_p").hide();
			$("#c_p").hide();
			
			$("#ro_s").hide();
			$("#c_s").hide();
			
			$("#ro_z").hide();
			$("#c_z").hide();
			
			$("#showHelp").click(function(){
				$("#helpImg").css("z-index", "1");
			});
			
			$("#closeHelp").click(function(){
				$("#helpImg").css("z-index", "-1");
			});


		}); 
	</script>

	<head>
	<body>
	<div>	
		<div id="helpImg" style="position: absolute; z-index: -1; height: 100%;width: 100%;background: rgba(50,50,50,0.8);">
			<div>
				<img src="/app/img/rysunek.png" style="margin: 200px auto; margin-bottom: 20px; border: 3px solid white;display: inherit;">
				<input id="closeHelp" type="button" value="Zamknij" style="margin: 0px auto;display: inherit;">
			</div>
		</div>
		<div id="titlediv">
			<span>Symulator wymiennika ciepła typu rura w rurze</span>
		</div>
		<form method="post" action="calculations.do" target="_blank">
			<table id="params">
			
				<tr id="head1">
					<td colspan="3" class="head">Płyn wewnątrz</td>
				</tr>
				<tr class="row">
					<td>Temperatura</td>
					<td><input type="text" name="<%=ParametrySymulacji.TEMP_T%>" value="0"/></td>
					<td class="unit">[&deg;K]</td>
				</tr>
				<tr class="row">
					<td>Rodzaj płynu</td>
					<td colspan="2">
						<select id="plynWewn" name="<%=ParametrySymulacji.PLYN_WEW%>" >
							<c:forEach var="plyn_w" items="${plyn_wew}">
								<option value="${plyn_w.code}">${plyn_w.name}</option>
							</c:forEach>
							
						</select>
					</td>
				</tr>
				<tr id="ro_t" class="row">
					<td>Gęstość</td>
					<td><input type="text" name="<%=ParametrySymulacji.RO_T%>" value="0"/></td>
					<td class="unit">[kg/m&sup3;]</td>
				</tr>
				<tr id="c_t" class="row">
					<td>Ciepło Właściwe</td>
					<td><input type="text" name="<%=ParametrySymulacji.C_T%>" value="0"/></td>
					<td class="unit">[J/(kg*&deg;K)]</td>
				</tr>
	
	
	
	
				<tr id="head2">
					<td colspan="3" class="head">Ścianka wewn</td>
				</tr>
				<tr class="row">
					<td>Temperatura</td>
					<td><input type="text" name="<%=ParametrySymulacji.TEMP_P%>" value="0"/></td>
					<td class="unit">[&deg;K]</td>
				</tr>
				<tr class="row">
					<td>Rodzaj materiału</td>
					<td colspan="2">
						<select id="ruraWewn" name="<%=ParametrySymulacji.MAT_WEW%>" >
							<c:forEach var="rura_w" items="${rura_wew}">
								<option value="${rura_w.code}">${rura_w.name}</option>
							</c:forEach>
							
						</select>
					</td>
				</tr>
				<tr id="ro_p" class="row">
					<td>Gęstość</td>
					<td><input type="text" name="<%=ParametrySymulacji.RO_P%>" value="0"/></td>
					<td class="unit">[kg/m&sup3;]</td>
				</tr>
				<tr id="c_p" class="row">
					<td>Ciepło Właściwe</td>
					<td><input type="text" name="<%=ParametrySymulacji.C_P%>" value="0"/></td>
					<td class="unit">[J/(kg*&deg;K)]</td>
				</tr>
	
	
	
				<tr id="head3">
					<td colspan="3" class="head">Płyn zewnetrzny</td>
				</tr>
				<tr class="row">
					<td>Temperatura</td>
					<td><input type="text" name="<%=ParametrySymulacji.TEMP_S%>" value="0"/></td>
					<td class="unit">[&deg;K]</td>
				</tr>
				<tr class="row">
					<td>Rodzaj materiału</td>
					<td colspan="2">
						<select id="plynZewn" name="<%=ParametrySymulacji.PLYN_ZEW%>" >
							<c:forEach var="plyn_z" items="${plyn_zew}">
								<option value="${plyn_z.code}">${plyn_z.name}</option>
							</c:forEach>
							
						</select>
					</td>
				</tr>
				<tr id="ro_s" class="row">
					<td>Gęstość</td>
					<td><input type="text" name="<%=ParametrySymulacji.RO_S%>" value="0"/></td>
					<td class="unit">[kg/m&sup3;]</td>
				</tr>
				<tr id="c_s" class="row">
					<td>Ciepło Właściwe</td>
					<td><input type="text" name="<%=ParametrySymulacji.C_S%>" value="0"/></td>
					<td class="unit">[J/(kg*&deg;K)]</td>
				</tr>
	
	
	
				<tr id="head4">
					<td colspan="3" class="head">Scianka zewnetrzna</td>
				</tr>
				<tr class="row">
					<td>Temperatura</td>
					<td><input type="text" name="<%=ParametrySymulacji.TEMP_Z%>" value="0"/></td>
					<td class="unit">[&deg;K]</td>
				</tr>
				<tr class="row">
					<td>Rodzaj materiału</td>
					<td colspan="2">
						<select id="ruraZewn" name="<%=ParametrySymulacji.MAT_ZEW%>" >
							<c:forEach var="rura_z" items="${rura_zew}">
								<option value="${rura_z.code}">${rura_z.name}</option>
							</c:forEach>
							
						</select>
					</td>
				</tr>
				<tr id="ro_z" class="row">
					<td>Gęstość</td>
					<td><input type="text" name="<%=ParametrySymulacji.RO_Z%>" value="0"/></td>
					<td class="unit">[kg/m&sup3;]</td>
				</tr>
				<tr id="c_z" class="row">
					<td>Ciepło Właściwe</td>
					<td><input type="text" name="<%=ParametrySymulacji.C_Z%>" value="0"/></td>
					<td class="unit">[J/(kg*&deg;K)]</td>
				</tr>
	
	
				<tr id="head5">
					<td colspan="3" class="head">Inne Parametry</td>
				</tr>
				<tr class="row">
					<td>Średnica D1</td>
					<td><input type="text" name="<%=ParametrySymulacji.D1%>" value="0"/></td>
					<td class="unit">[m]</td>
				</tr>
				<tr class="row">
					<td>Średnica D2</td>
					<td><input type="text" name="<%=ParametrySymulacji.D2%>" value="0"/></td>
					<td class="unit">[m]</td>
				</tr>
				<tr class="row">
					<td>Średnica D3</td>
					<td><input type="text" name="<%=ParametrySymulacji.D3%>" value="0"/></td>
					<td class="unit">[m]</td>
				</tr>
				<tr class="row">
					<td>Średnica D4</td>
					<td><input type="text" name="<%=ParametrySymulacji.D4%>" value="0"/></td>
					<td class="unit">[m]</td>
				</tr>
				<tr class="row">
					<td>Prędkość płynu wewnętrznego</td>
					<td><input type="text" name="<%=ParametrySymulacji.V_T%>" value="0"/></td>
					<td class="unit">[m/s]</td>
				</tr>
				<tr class="row">
					<td>Prędkość płynu zewnętrznego</td>
					<td><input type="text" name="<%=ParametrySymulacji.V_S%>" value="0"/></td>
					<td class="unit">[m/s]</td>
				</tr>
				<tr class="row">
					<td>Długość wymiennika</td>
					<td><input type="text" name="<%=ParametrySymulacji.DLUGOSC_WYMIENNIKA%>" value="0"/></td>
					<td class="unit">[m]</td>
				</tr>
				<tr class="row">
					<td>Ilość sekcji</td>
					<td><input type="text" name="<%=ParametrySymulacji.ILOSC_SEKCJI%>" value="0"/></td>
					<td class="unit"></td>
				</tr>
	
				<tr id="head6">
					<td colspan="6" class="head">
						<input id="showHelp" type="button" value="Pomoc" style="margin-right: 200px;"/>
						<input type="submit" value="Uruchom obliczenia"/>
					</td>
				</tr>
			</table>
		</form>

	</div>
	</body>
</html>