<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

 <script src="js/jquery.easy-autocomplete.min.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    var availableTags = ${sessionScope.term};
    $( "#tags" ).autocomplete({
    	
      
      source: function(request, response) {
          var results = $.ui.autocomplete.filter(availableTags, request.term);

          response(results.slice(0, 10));
      }
    });
   
  });
  </script>
</head>
<body background="img/triangle-background-17.png">
<form action="home" method="POST">
<center>
<div class="container">
<br/>
<br/>
<br/>
<br/>
 <h1><label for="comment">Search:</label></h1> 
<div >

<input type="text" id="tags" name="getQue" class="form-control"  style="width:500px;height: 35px">

<br/><center><input type="submit" name="Search" value="search"  id="search" class="btn btn-primary btn-lg" style="font-size: 40"></center>
</div>
</div>
</center>
</form>
<div class="navbar navbar-fixed-bottom"><center><a href="search" style="color: #190707">Go To Home Page</a></center></div>


</body>
</html>