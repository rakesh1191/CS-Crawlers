<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Links</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<center><h2> <kbd>Search engine</kbd></h2></center>
<body background="img/triangle-background-17.png">

<div class="container">
<a href="home">Search Again</a>
<center>
<br/>
<div class="well">
<h5 style="text-align:left;">Total Matching Documents  ${sessionScope.size}</h5>
<table>

<c:if test="${empty sessionScope.links}">
<a>No Results Found</a>
</c:if>
<tr><th>ID</th><th>Tf-Idf Score</th><th>&nbsp;&nbsp;Link Analysis Score</th><th>total</th><th>&nbsp;&nbsp;Links</th></tr>
 <c:forEach items="${sessionScope.links}" var="temp" varStatus="status">
<tr><td>${temp.id+1} : </td>
<td><c:out value="${sessionScope.score[status.index]}"></c:out></td>
<td>&nbsp;&nbsp;<c:out value="${sessionScope.rScore[status.index]}"></c:out></td>
<td>&nbsp;&nbsp;<c:out value="${sessionScope.rScore[status.index]+sessionScope.rScore[status.index]}"></c:out></td>

<td>&nbsp;&nbsp;&nbsp;<a href="file:///${temp.url}">${temp.fileName}</a></td>
</tr>
 </c:forEach>
</table>
</div>
</center>
<a href="home">Search Again</a>
</div>




</body>
</html>