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
        String groups = "";
        int todoGroupAmount = model.getTodoGroupAmount();
        for(int i = 0; i < todoGroupAmount; i++) {
            int number = i + 1;

            List<TodoItem> todoItemList = todoItems.getByGroupId((long)i + 1);


            groups = groups + number + " " + model.getTodoGroup(i).getName() + " : " + getGroupItems(todoItemList) + "<br>";
        }
        return groups;
    }
%>

<%!
  String getGroupItems(List<TodoItem> todoItemList){
      String itemNames = "";

      for(int j = 0 ; j < todoItemList.size(); j++){
          itemNames = itemNames + todoItemList.get(j).getTitle() + " ; ";
      }

      return itemNames;
  }
%>
<jsp:include page="/includes/footer.jsp" />


