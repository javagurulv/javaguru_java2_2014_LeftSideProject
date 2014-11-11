<%@ page import="lv.javaguru.java2.database.TodoItemDAO" %>
<%@ page import="lv.javaguru.java2.database.jdbc.TodoItemDAOImpl" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="lv.javaguru.java2.domain.TodoItem" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.database.DBException" %>
<%--
  Created by IntelliJ IDEA.
  User: alekmiku
  Date: 2014.11.10.
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TodoItems Page</title>
</head>
<body>
<header>TodoItemPage Body</header>
<%
    TodoItemDAO item = new TodoItemDAOImpl();
    List<TodoItem> itemList = item.getAll();
%>
    <b>Item list size:</b>
<%
    out.println(itemList.size());
%>

</body>
</html>
