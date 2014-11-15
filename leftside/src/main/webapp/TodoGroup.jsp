<jsp:include page="/includes/header.jsp" />
<%@ page import="lv.javaguru.java2.web.mvc.todoGroupServlet.TodoGroupModel" %>
<%@ page import="lv.javaguru.java2.domain.TodoItem" %>
<%@ page import="lv.javaguru.java2.database.TodoItemDAO" %>
<%@ page import="lv.javaguru.java2.database.jdbc.TodoItemDAOImpl" %>
<%@ page import="java.util.List" %>
<%
    TodoGroupModel model = (TodoGroupModel) request.getAttribute("model");
    TodoItemDAO todoItems = new TodoItemDAOImpl();
%>
<%=
    writeGroupsAndItems(model, todoItems)
%>
<%!
    String writeGroupsAndItems(TodoGroupModel model, TodoItemDAO todoItems) {
        String groups;
        StringBuilder stringBuilder = new StringBuilder();
        int todoGroupAmount = model.getTodoGroupAmount();
        for(int i = 0; i < todoGroupAmount; i++) {
            int number = i + 1;

            List<TodoItem> todoItemList = todoItems.getByGroupId((long)i + 1);


            stringBuilder.append(number + " " + model.getTodoGroup(i).getName() + " : " + getGroupItems(todoItemList) + "<br>");
        }
        groups = stringBuilder.toString();
        return groups;
    }
%>

<%!
  String getGroupItems(List<TodoItem> todoItemList){
      String itemNames;
      StringBuilder stringBuilder = new StringBuilder();

      for(int j = 0 ; j < todoItemList.size(); j++){
          stringBuilder.append(todoItemList.get(j).getTitle() + " ; ");
      }

      itemNames = stringBuilder.toString();

      return itemNames;
  }
%>
<jsp:include page="/includes/footer.jsp" />


