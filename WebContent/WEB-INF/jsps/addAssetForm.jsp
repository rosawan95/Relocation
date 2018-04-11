<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="design.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
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
	<br />
	<center>
	
	<form:form modelAttribute="asset" action="submitAssets.do"
		method="post">
		<div class="container">
			<h1 id="mgr">Add New Asset</h1>
			
			<div>
			<label for="assetName"><div class="fnts">Asset Name:</div></label>
			<div>
				<form:input path="assetName" id="aname" required="true"
					placeholder="Asset Name"  />
					<form:errors path="assetName" cssStyle="color:red"/>
			</div>
		</div>
		
		<div>
			<label for="assetDesc"><div class="fnts">Asset Description:</div></label>
			<div>
				<form:input path="assetDesc" id="adesc" required="true"
					placeholder="Asset Description" />
					<form:errors path="assetDesc" cssStyle="color:red"/>
			</div>
		</div>
		
			<div>
			<label for="quantity"><div class="fnts">Asset Quantity:</div></label>
			<div>
				<form:input path="quantity" id="aqty" required="true"
					type="text" required="true" placeholder="Asset Quantity" />
					<form:errors path="quantity" cssStyle="color:red"/>
			</div>
		</div>
		<div>
						<!-- <td>Asset Status:</td> -->
						<label for="status"></label>
						<div><form:input path="status" name="status"
							value="Available" id="status" readonly="true" type="hidden"
							 /></div>
				</div>
				<!-- <input type="submit" value="ADD Asset" id="styles" /> <input
					type="reset" value="Reset" id="styles" /> -->
<button type="submit"><div id="final">Add Asset</div></button>
<button type="reset"><div id="final">Reset Details</div></button>


		</div>
		
		<br>
		
</form:form>

			<br> <a href="adminHomePage.do"><button type="submit"
					id="nwo"><div id="styles">Back to Home Page!</div></button></a>

	</center>
			<%@ include file="footer.html"%>
</body>
</html>