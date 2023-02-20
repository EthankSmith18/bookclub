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
    <title>Tacos</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/main.css" />
    <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script>
    <!-- change to match your file/naming structure -->
  </head>
  <body>
    <h1>Welcome <c:out value="${user.userName}"></c:out>!</h1><br>
    <p>This is your dashboard, nothing to see here.</p><br>
    <a href="/logout">Logout</a>
    <a href="/books/new">Create Book</a>
    <table>
      <thead>
        <tr>
          <td>Title</td>
          <td>Author</td>
          <td>Posted By</td>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="book" items="${books}">
          <tr>
          <td><a href="/books/${book.id}">${book.title}</a></td>
          <td>${book.author}</td>
          <td>${book.user.userName}</td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
