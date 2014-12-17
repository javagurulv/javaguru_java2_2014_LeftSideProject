<jsp:include page="/includes/header.jsp"/>
<%@ page import="lv.javaguru.java2.domain.TodoItem" %>
<%@ page import="lv.javaguru.java2.web.mvc.todoGroupServlet.TodoGroupModel" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.TodoGroup" %>
<%@ page import="lv.javaguru.java2.database.TodoGroupDAO" %>

<%
    TodoGroupModel model = (TodoGroupModel) request.getAttribute("model");
    List<TodoGroup> todoGroups = model.getAllTodoGroups();
    TodoGroupDAO databaseAccess = (TodoGroupDAO) request.getAttribute("database");
    String buttonFunc = addButtonFunc(request, model, databaseAccess);
    String groupsAndItems = writeGroupsAndItems(todoGroups);
    String buttons = addButtons();

%>

<%=groupsAndItems%>
<%=buttons%>
<%=buttonFunc%>

<%!
    String writeGroupsAndItems(List<TodoGroup> todoGroups) {
        String groups = "";
        int number = 1;
        for (TodoGroup todoGroup : todoGroups) {
            List<TodoItem> todoItemList = todoGroup.getTodoItems();
            if (todoItemList != null) {
                groups += number + ") " + todoGroup.getName() + " : " + getGroupItems(todoItemList) + "<br>";
            }else{
                groups += number + ") " + todoGroup.getName() + " : { }" + "<br>";
            }
            number++;
        }
        return groups;
    }
%>

<%!
    String addButtonFunc(HttpServletRequest request, TodoGroupModel model, TodoGroupDAO databaseAccess) {
        String buttons = "";
        String add = request.getParameter("addedButtonName");
        String delete = request.getParameter("deletedButtonName");
        String isGroupAdded = isGroupAdded(add);
        String isGroupDeleted = isGroupDeleted(delete, model);
        if(isGroupAdded == "Incorrect name" || isGroupDeleted == "Incorrect name"){
            buttons = isGroupAdded;
        }else if(isGroupAdded == "true"){
            addGroupToDatabase(add, databaseAccess, model);
            buttons += "Group Added";
        }else if(isGroupDeleted == "true"){
            removeGroupFromDatabase(model, delete, databaseAccess);
            buttons += "Group Deleted";
        }
        if(buttons == null){
            return " ";
        }else{
            return buttons;
        }
    }


%>

<%!
    void removeGroupFromDatabase(TodoGroupModel model, String delete, TodoGroupDAO databaseAccess){
        //TODO If 2 groups have the same name ask which one to remove.
        TodoGroup groupToDelete = model.getTodoGroupByName(delete);
        String groupName = groupToDelete.getName();
        databaseAccess.deleteGroupFromDatabase(groupName);
        model.removeTodoGroup(groupToDelete);
    }

%>

<%!
    void addGroupToDatabase(String add, TodoGroupDAO databaseAccess, TodoGroupModel model){
        TodoGroup todoGroup = new TodoGroup();
        todoGroup.setName(add);
        databaseAccess.addGroupToDatabase(todoGroup);
        model.addTodoGroup(todoGroup);
    }
%>

<%!
    String isGroupAdded(String addedGroupName){
        String isGroupAdded = "";
        if(addedGroupName == null) {
            isGroupAdded = "false";
        }else if(addedGroupName == ""){
            isGroupAdded = "Incorrect name";
        }else if(addedGroupName != null){
            isGroupAdded = "true";
        }
        return isGroupAdded;
    }
%>

<%!
    String isGroupDeleted(String deletedGroupName, TodoGroupModel model){
        TodoGroup group = model.getTodoGroupByName(deletedGroupName);
        if(deletedGroupName == null) {
            return "false";
        }else if(group == null){
            return "Incorrect name";
        }else{
            return "true";
        }
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

<%!
    String addButtons(){
        String codeForButtons = "";
        codeForButtons += "<form action=\"TodoGroup\"method=\"POST\"\">\n" +
                "Add Button:<br>\n" +
                "<input type=\"text\" name=\"addedButtonName\">\n" +
                "<br><br>\n" +
                "<input type=\"submit\" value=\"Add\">\n" +
                "</form>" +
                "<br><br>";
        codeForButtons += "<form action=\"TodoGroup\"method=\"POST\"\">\n" +
                "Delete Button:<br>\n" +
                "<input type=\"text\" name=\"deletedButtonName\">\n" +
                "<br><br>\n" +
                "<input type=\"submit\" value=\"Delete\">\n" +
                "</form>"+
                "<br><br>";


        return codeForButtons;
    }
%>
<jsp:include page="/includes/footer.jsp"/>