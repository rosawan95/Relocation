<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		Welcome ${userName }&nbsp;&nbsp;&nbsp; <a href="logout.do">logout</a>
	</div>
	<br>
	<center>
		<div class="container">
		<div id="reqinfo">
			<center>Raised request for employee ${empName} with Employee Id:
				${empId} and the Request Id is: ${reqId}</center>
				</div>
			<br>
			<br>
			<br> <a href="createAssetRequestData.do"><button type="submit"
					id="nwi">Request Asset</button></a> <br>
			<br> <a href="showAllRequests.do"><button type="submit"
					id="nwi">Check Request status</button></a>
			<br/>
			<a href="getManagerHome.do"><button type="button" id="nwi">Home</button></a>
	</center>
	</div>
			<%@ include file="footer.html"%>
</body>
</html>