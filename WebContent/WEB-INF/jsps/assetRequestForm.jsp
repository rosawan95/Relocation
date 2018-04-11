<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fo"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="design.css" rel="stylesheet" type="text/css">
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
		Welcome ${userName }&nbsp;&nbsp;&nbsp; <u> <%@ include file="logout.jsp"%></u><br>

	</div>
	<!-- <div class="fnts"> -->
	<br>
	<br>
<center>
	<div class="container">
	<fo:form method="post" modelAttribute="request" action="insertRequestData.do">
	
		<div>
			<label for="emp.empno"><div class="fnts">Employee Name</div></label>
			
			<div>
				<fo:select path="emp.empno">
				
                <fo:options items="${emplMap}" />
            </fo:select>
				<br>
			</div>
		</div>
		<div>
			<label for="asset.assetId"><div class="fnts">Required Asset:</div> </label>
			
			<div>
				<fo:select path="asset.assetId" >
				
                <fo:options items="${assetMap}" />
           		</fo:select>
			</div>
		</div>
		<div>
		<label for="requestDate"><div class="fnts">Required Date: </div></label>
			<div>
				<fo:input type="date" id="styl" path="requestDate" class="date"  name = "requestDate" required="true" />
				<div id="errmsg">
				${errorMsg}
				</div>
			</div>
		</div>
		
		<div>
		<label for="requestForDays"><div class="fnts">No of days: </div></label>
			<div>
				<fo:input type="number" id="styl" min="1" max="600" path="requestForDays" name = "requestForDays" /><br/>
				 <form:errors path="requestForDays" cssStyle="color: #ff0000;" />
				
				<fo:input type="hidden" path="status"  value="Pending" />
			</div>
		</div>
		<div>
			<div>
				<button type="submit"><div id="styles">Add</div></button>
			</div>
		</div>
	</fo:form>
	</div>
	</center>
	<center>
			<a href="getManagerHome.do"><button type="button" id="nwi"><div id="styles">Back to Home Page!</div></button></a>
			</center>
</body>
		<%@ include file="footer.html"%>
</html>