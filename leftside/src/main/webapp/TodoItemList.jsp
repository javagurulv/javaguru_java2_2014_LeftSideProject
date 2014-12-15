<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title> :: Todo Items List :: </title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>ID</th>
        <th>StateID</th>
        <th>Title</th>
        <th>Description</th>
        <th>DueDate</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${model}" var="i">


        <tr>

            <td><c:out value="${i.getItemId()}"/></td>
            <td><c:out value="${i.getStateId()}"/></td>
            <td><c:out value="${i.getTitle()}"/></td>
            <td><c:out value="${i.getDescription()}"/></td>
            <td><c:out value="${i.getDueDate()}"/></td>

            <td><a href="/todoItem?action=edit&itemId=<c:out value="${i.getItemId()}"/>">Update</a></td>
            <td><a href="/todoItem?action=delete&itemId=<c:out value="${i.getItemId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="todoItem?action=insert">Add TodoItem</a></p>
</body>
</body>
</html>