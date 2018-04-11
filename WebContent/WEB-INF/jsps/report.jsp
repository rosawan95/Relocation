<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	window.history.forward();
	function noBack(){
		window.history.forward();
	}
</script>
<body>
	<%@ include file="new.html"%>
	<div align="right">
		Welcome ${userName }&nbsp;&nbsp;&nbsp; <u> <%@ include
				file="logout.jsp"%><br>
		</u>
	</div>

	<center>
		<h2><div class="fnts">Allocated Asset Report:</div></h2>
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
		<h2><div class="fnts">Asset Status Report:</div></h2>
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

		<a href="generateReport.do"><button type="submit" id="nwi"><div id="styles">Generate
				Report</div></button></a>
		<br><a href="adminHomePage.do"><button type="submit" id="nwi"><div id="styles">Go Back To Home Page!</div></button></a>  <br>
	</center>
	<%@ include file="footer.html"%>
</body>
</html>