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
	<div align="center"><b> ${errorMsg} </b></div>
	<br />
	

	<table border="1" align="center" id="tables">
		<tr>
			<th>ASSET ID</th>
			<th>ASSET NAME</th>
			<th>ASSET DESCRIPTION</th>
			<th>QUANTITY</th>
			<th>STATUS</th>
			<th>ADD / MODIFY</th>
			<th>REMOVE</th>
		</tr>
		<fo:forEach var="asset" items="${asset}">

			<tr>
				<td>${asset.assetId}</td>
				<td>${asset.assetName}</td>
				<td>${asset.assetDesc}</td>
				<td>${asset.quantity}</td>
				<td>${asset.status}</td>
				<td><a href="adminModify.do?id=${asset.assetId }">ADD/MODIFY</a></td>
				<td><a href="delete.do?id=${asset.assetId }">REMOVE</a></td>
			</tr>	
			

		</fo:forEach>
	</table>

	<br />
	<br />
	<center>
		<a href="addNewAsset.do"><button type="submit" id="nwi"><div id="styles">Add
				New Asset</div></button></a> <br>
		<br> <a href="adminHomePage.do"><button type="submit"
				id="nwi"><div id="styles">Go Back to Home Page!</div></button></a>
	</center>
		<%@ include file="footer.html"%>
</body>
</html>

