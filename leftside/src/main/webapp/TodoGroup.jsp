<jsp:include page="/includes/header.jsp"/>
<%@ page import="lv.javaguru.java2.domain.TodoItem" %>
<%@ page import="lv.javaguru.java2.web.mvc.todoGroupServlet.TodoGroupModel" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.database.TodoGroupDAO" %>
<%@ page import="lv.javaguru.java2.database.hibernate.TodoGroupDAOImpl" %>
<%@ page import="lv.javaguru.java2.domain.TodoGroup" %>
<%
    TodoGroupModel model = (TodoGroupModel) request.getAttribute("model");

    TodoGroupDAO todoItems = new TodoGroupDAOImpl();
%>
<%=
    writeGroupsAndItems(model, todoItems)
%>
<%!
    String writeGroupsAndItems(TodoGroupModel model, TodoGroupDAO todoItems) {
        String groups = "";
        int todoGroupAmount = model.getTodoGroupAmount();
        for (int i = 0; i < todoGroupAmount; i++) {
            int number = i + 1;

            List<TodoItem> todoItemList = todoItems.getByGroupId((long) i + 1);
            groups = groups + number + " " + model.getTodoGroup(i).getName() + " : " + getGroupItems(todoItemList) + "<br>";
        }
        return groups;
    }
%>

<%!
    String getGroupItems(List<TodoItem> todoItemList) {
        String itemNames = "";

        for (int j = 0; j < todoItemList.size(); j++) {
            itemNames = itemNames + todoItemList.get(j).getTitle() + " ; ";
        }

        return itemNames;
    }
%>
<jsp:include page="/includes/footer.jsp"/>


