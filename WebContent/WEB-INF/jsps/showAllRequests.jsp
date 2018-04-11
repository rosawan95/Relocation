<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	window.history.forward();
	function noBack(){
		window.history.forward();
	}
</script>
</head>
<body>
	<%@ include file="new.html"%>
	<div align="right">
		Welcome ${userName }&nbsp;&nbsp;&nbsp; <u> <%@ include
				file="logout.jsp"%><br>
		</u>
	</div>	
	<br />
	<br />
	<br />
	<table border="1" id="tables" align="center">
		<tr>
			<th>Requestid</th>
			<th>Empno</th>
			<th>Employee Name</th>
			<th>Assetid</th>
			<th>Asset Name</th>
			<th>Request Date</th>
			<th>Request for Days</th>
			<th>Status</th>
		</tr>


		<fo:forEach var="request" items="${data}">
			<tr>
				<td>${request.requestId}</td>
				<td>${request.emp.empno}</td>
				<td>${request.emp.ename}</td>
				<td>${request.asset.assetId}</td>
				<td>${request.asset.assetName}</td>
				<td>${request.requestDate}</td>
				<td>${request.requestForDays}</td>
				<td>${request.status}</td>
			</tr>
		</fo:forEach>

	</table>
	<br />
	<br />
	<center>
		<a href="createAssetRequestData.do"><button type="submit" id="nwi"><div id="styles">Request
				Asset</div></button></a>
		<!-- id="nwi" -->
		<br>
		<br><a href="getManagerHome.do"><button type="submit" id="nwi"><div id="styles">Go Back to Home Page!</div></button></a>  <br>
		<br>
	</center>
		<%@ include file="footer.html"%>
</body>
</html>