<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Books</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/main.css" />
    <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script>
    <!-- change to match your file/naming structure -->
  </head>
  <body>
    <h1>Edit Book</h1><br />
    <a href="/books/">Back to Shelves</a><br />
    <form:form  action="/edit/${book.id}" method="post" modelAttribute="book">
      <input type="hidden" name="_method" value="put">
        <p>
            <form:label path="title">Book Title</form:label>
            <form:errors path="title"/>
            <form:input path="title"/>
        </p>
        <p>
            <form:label path="author">Author</form:label>
            <form:errors path="author"/>
            <form:textarea path="author"/>
        </p>
        <p>
            <form:label path="thoughts">Thoughts</form:label>
            <form:errors path="thoughts"/>
            <form:input type="textarea" path="thoughts"/>
        </p>  
        <input type="submit" value="Save Edits"/>
    </form:form> 
  </body>
</html>
