<%@ page import="lv.javaguru.java2.web.mvc.core.Authentication" %>
<%@ page import="lv.javaguru.java2.web.mvc.todoItemServlet.TodoItemModel" %>
<%@ page import="java.text.SimpleDateFormat" %>

<jsp:include page="/includes/header.jsp"/>

<%
    boolean isAuthenticated = Authentication.isLoggedIn(session);
    TodoItemModel model = (TodoItemModel) request.getAttribute("model");
    int itemSize = model.getTodoItemSize();
%>

<%
    String htmlTable;
    htmlTable = "<table border='1'>" +
            "<th>ID</th>" +
            "<th>Title</th>" +
            "<th>Description</th>" +
            "<th>DueDate</th>";

    for (int i = 0; i < itemSize; i++) {

        htmlTable += "<tr><td>" + model.getTodoItem(i).getItemId()
                + "</td><td>" + model.getTodoItem(i).getTitle()
                + "</td><td>" + model.getTodoItem(i).getDescription()
                + "</td><td>" + model.getTodoItem(i).getDueDate()
                + "</td></tr>";
    }

    htmlTable += "</table>";
%>

<% String htmlAddItemForm;
    htmlAddItemForm = "";
    htmlAddItemForm += "<p><form action='/TodoItem.jsp' method='post' >" +
            "<fieldset><legend>Add new ToDo item</legend>" +
            "Title:<br> <input type='text' name='Title'><br>" +
            "Description:<br> <input type='text' name='Description'><br>" +
            "<input type='submit' value='Submit'>" +
            "</fieldset></form></p>";

%>


<h4>Today's date:
    <%= new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>
</h4>


<h3>ToDo Items
</h3>

<%
    //ToDo: AM: reverse when login will be set
    if (!isAuthenticated) {%> <%=htmlTable%> <%
} else %> <h1>You are authorized</h1>



<%= htmlAddItemForm %>


<jsp:include page="/includes/footer.jsp"/>
