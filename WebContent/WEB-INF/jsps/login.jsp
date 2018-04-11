<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="design.css" rel="stylesheet" type="text/css">
<title>Login</title>
<script type="text/javascript">
	window.history.forward();
	function noBack(){
		window.history.forward();
	}
</script>
</head>
<body>


	<%@ include file="new.html"%>
		




	<div align="center" style="color: red">

		${errorMsg }

		<%-- <%
			String msg=(String)request.getAttribute("errorMsg");
			out.write(msg);
			%>	 --%>
	</div>
	<center>
	
		<div class="container">
	<form action="authenticate.do" method="post" align="center">
	
		<!--   Username : <input type="text" name="userName"><br> -->
		<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
		
		
				<b><div class="fnts">Username</div></b><input type="text" id="usn"
					placeholder="Enter Username" name="userName" required> <br />
				<!--  Password : <input type="password" name="password"><br> -->
				<b><div class="fnts">Password</div></b> <input type="password" id="pwd"
					placeholder="Enter Password" name="password" required>
				<!--    <input type="submit" value="Login">&nbsp;&nbsp;&nbsp;<input type="reset" value="Clear"> -->
				<br />
				<button type="submit">Login</button>

				<br />


		


	</form>
			</div>
	<%@ include file="footer.html"%>
	</center>
	



</body>
</html>