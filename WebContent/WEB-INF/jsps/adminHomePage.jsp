<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	window.history.forward();
	function noBack(){
		window.history.forward();
	}
</script>
<title>Insert title here</title>
</head>
<body  onload="noBack();" 
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

			<h1 id="mgr">Admin Home Page</h1>
			<br>
			<br> <a href="showAssets.do"><button type="submit" id="nwo"><div id="styles">View
					Asset List</div></button></a> <br /> 
					<a href="showPendingRequests.do"><button
					type="submit" id="nwo"><div id="styles">Show Requests</div></button></a> <br /> <a
				href=showReport.do><button type="submit" id="nwo"><div id="styles">Generate
					Report</div></button></a>

		</div>
	</center>
			<%@ include file="footer.html"%>
</body>
</html>