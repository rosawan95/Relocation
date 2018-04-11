<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
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

	<center>
		<div class="container">
			<h1 id="mgr">Modify Asset Form</h1>
			<f:form modelAttribute="asset" action="adminUpdateConfirm.do?id=${asset.assetId}">
			
				<table>
					<tr>
						<td><div class="fnts">Asset ID:</div></td>
						<td><f:input  name="asstid" path="assetId"
							value="${asset.assetId}" id="asstid" readonly="true" /></td>
					</tr>
					<tr>
						<td><div class="fnts">Asset Name:</div></td>
						<td><f:input path="assetName" name="asstnm"
							value="${asset.assetName}" id="assetName" readonly="true"
							/></td>
					</tr>
					<tr>
						<td><div class="fnts">Asset Description:</div></td>
						<td><f:input path="assetDesc" name="asstdes"
							value="${asset.assetDesc}" id="assetDesc" readonly="true"
							 /></td>
					</tr>
					<tr>
						<td><div class="fnts">Quantity:</div></td>
						<td><f:input path="quantity"
							value="${asset.quantity}"  readonly="readonly" /></td>
					</tr>
					<tr>
						<!-- <td>Asset Status:</td> -->
						<td><f:input path="status" name="status"
							value="${asset.status}" id="status" readonly="true" type="hidden"
							 /></td>
					</tr>
					<tr>
						<td><div class="fnts">Add Quantity:</div></td>
						<td><input type="number" id="plusquantity" min="1" max="100"
							name="plusquantity" /></td>
					</tr>
				</table>

				<!-- <input type="submit" value="Confirm Update" id="isd" /> -->



			<!-- 	<td><input type="reset" value="Reset Details" id="isd" /></td> -->
<button type="submit" id="isd"><div id="styles">Confirm Update</div></button></a>
<button type="reset" id="isd"><div id="styles">Reset</div></button></a>


			</f:form>
			<br> <a href="adminHomePage.do">
					<button type="submit" id="isd"><div id="styles">Go Back to Home Page!</div></button></a>
		</div>
	</center>
		<%@ include file="footer.html"%>
</body>
</html>
