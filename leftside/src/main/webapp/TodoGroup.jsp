<jsp:include page="/includes/header.jsp"/>
<%@ page import="lv.javaguru.java2.domain.TodoItem" %>
<%@ page import="lv.javaguru.java2.web.mvc.todoGroupServlet.TodoGroupModel" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.TodoGroup" %>
<%@ page import="java.util.ArrayList" %>
<%
    TodoGroupModel model = (TodoGroupModel) request.getAttribute("model");
    List<TodoGroup> todoGroups = model.getAllTodoGroups();
%>

<%=
    writeGroupsAndItems(todoGroups)
%>
<%!
    String writeGroupsAndItems(List<TodoGroup> todoGroups) {
        String groups = "";
        int number = 1;
        for (TodoGroup todoGroup : todoGroups) {
            List<TodoItem> todoItemList = todoGroup.getTodoItems();
            groups += number + ") " + todoGroup.getName() + " : " + getGroupItems(todoItemList) + "<br>";
            number++;
        }
        return groups;
    }
%>

<%!
    String getGroupItems(List<TodoItem> todoItemList) {
        String itemNames = "";

        for (TodoItem todoItem : todoItemList) {
            itemNames += todoItem.getTitle() + " ; ";
        }

        return "{ " + itemNames + "}";
    }
%>
<jsp:include page="/includes/footer.jsp"/>


