
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
	<br />
	<br />
	<table border="1" align="center" id="tables">
		<tr>
			<th>REQUEST ID</th>
			<th>EMPLOYEE NO</th>
			<th>EMPLOYEE NAME</th>
			<th>ASSET ID</th>
			<th>ASSET NAME</th>
			<th>REQUEST DATE</th>
			<th>REQUEST FOR DAYS</th>
			<th>STATUS</th>
			<th>APPROVE</th>
			<th>REJECT</th>
		</tr>

		<fo:forEach var="requestsPending" items="${requests}">

			<tr>
				<td>${requestsPending.requestId}</td>
				<td>${requestsPending.emp.empno}</td>
				<td>${requestsPending.emp.ename}</td>
				<td>${requestsPending.asset.assetId}</td>
				<td>${requestsPending.asset.assetName}</td>
				<td>${requestsPending.requestDate}</td>
				<td>${requestsPending.requestForDays}</td>
				<td>${requestsPending.status}</td>
				 <fo:choose>
					<fo:when test="${requestsPending.asset.quantity < 1}">
						<td>Asset Not available</td>
					</fo:when>

					<fo:otherwise>
						<td><a
							href="approveRequest.do?id=${requestsPending.requestId}">Approve</a></td>
					</fo:otherwise>
				</fo:choose>


           
				<td><a href="rejectRequest.do?id=${requestsPending.requestId}">Reject</a></td>
			</tr>

		</fo:forEach>
	</table>
	<br />
	<br />
	<center>
		<a href="adminHomePage.do"><button type="submit" id="nwi"><div id="styles">
				Go Back to Home Page!</div> </button></a>
	</center>
			<%@ include file="footer.html"%>
</body>
</html>

