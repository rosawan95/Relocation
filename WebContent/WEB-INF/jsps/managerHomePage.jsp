<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="design.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	window.history.forward();
	function noBack(){
		window.history.forward();
	}
</script>

<title>Insert title here</title>
</head>
<body onload="noBack();" 
	onpageshow="if(event.persisted) noBack();" onunload="">
	
	<%@ include file="new.html"%>


	<div align="right">
	Welcome ${userName }&nbsp;&nbsp;&nbsp; <u> <a href="logout.do">logout</a>
	</u>
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<center>
		<div class="container">
			<h1 id="mgr">Manager Home Page</h1>
			<br>
			<br>
			<a href="createAssetRequestData.do"><button type="submit" id="nwo"><div id="styles">Request
					Asset</div></button></a> <br>
			<br> <a href="showAllRequests.do"><button type="submit"
					id="nwo"><div id="styles">Check Request status</div></button></a>

		</div>
	</center>
		<%@ include file="footer.html"%>
</body>
</html>


