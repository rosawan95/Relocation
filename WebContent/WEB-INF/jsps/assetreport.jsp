<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>



	<%
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "inline; filename="
				+ "report.xls");
	%>


	<center>
		<h2>Allocated Asset Report:</h2>
	</center>
	<table border="1" align="center" id="tables">
		<tr>
			<th>ASSET ID</th>
			<th>EMPLOYEE NAME</th>
			<th>ALLOCATION ID</th>
			<th>ALLOCATION DATE</th>
			<th>RELEASE DATE</th>
			<th>ASSET NAME</th>
			
		</tr>
		<fo:forEach var="assets" items="${allocatedAssets}">

			<tr>
				<td>${assets.assetId.assetId}</td>
				<td>${assets.empNo.ename}</td>
				<td>${assets.allocationId}</td>
				<td>${assets.allocationDate}</td>
				<td>${assets.releaseDate}</td>
				<td>${assets.assetId.assetName}</td>
			</tr>

		</fo:forEach>
	</table>



	<center>
		<h2>Asset Status Report:</h2>
	</center>
	<table border="1" align="center" id="tables">

		<tr>
			<th>ASSET ID</th>
			<th>ASSET NAME</th>
			<th>DESCRIPTION</th>
			<th>QUANTITY</th>
			<th>STATUS</th>
		</tr>

		<fo:forEach var="report" items="${availAssetsDetail}">

			<tr>
				<td>${report.assetId}</td>
				<td>${report.assetName}</td>
				<td>${report.assetDesc}</td>
				<td>${report.quantity}</td>
				<td>${report.status}</td>


			</tr>


		</fo:forEach>
	</table>
	<br>
	<center>
	
</body>
</html>