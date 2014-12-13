<jsp:include page="/includes/header.jsp"/>
<%@ page import="lv.javaguru.java2.domain.TodoItem" %>
<%@ page import="lv.javaguru.java2.web.mvc.todoGroupServlet.TodoGroupModel" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.TodoGroup" %>
<%@ page import="java.util.ArrayList" %>
@Autowired
TodoGroupDAO todoItems;
<%
    TodoGroupModel model = (TodoGroupModel) request.getAttribute("model");
    List<TodoGroup> todoGroups = new ArrayList<TodoGroup>();
    int groupAmount = model.getTodoGroupAmount();
    for(int i = 1; i < groupAmount; i++){
        todoGroups.add(model.getTodoGroup(i));
    }
%>

<%=
    writeGroupsAndItems(model, todoGroups)
%>
<%!
    String writeGroupsAndItems(TodoGroupModel model, List<TodoGroup> todoGroups) {
        String groups = "";
        int todoGroupAmount = model.getTodoGroupAmount();
        for (int i = 0; i < todoGroupAmount; i++) {
            int number = i + 1;
            List<TodoItem> todoItemList = todoGroups.get(i).getItemsInGroup();
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


