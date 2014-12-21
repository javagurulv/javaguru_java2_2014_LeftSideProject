<jsp:include page="/includes/header.jsp"/>
<%@ page import="lv.javaguru.java2.domain.TodoItem" %>
<%@ page import="lv.javaguru.java2.web.mvc.todoGroupServlet.TodoGroupModel" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.TodoGroup" %>
<%@ page import="java.util.ArrayList" %>

<%
    TodoGroupModel model = (TodoGroupModel) request.getAttribute("model");
    List<TodoGroup> todoGroups = model.getTodoGroups();
    String error = model.getMessage();
    Boolean isAuth = (Boolean) request.getAttribute("auth");
    List<TodoGroup> deleteableTodoGroups = (List<TodoGroup>)request.getAttribute("error");

    String groupsAndItems = writeGroupsAndItems(todoGroups);
    String buttons = addButtons(isAuth, todoGroups, deleteableTodoGroups);

%>

<%=groupsAndItems%>
<%=buttons%>
<% if (error != null) {%>
<br> Message: <%=error%>
<%
        model.setMessage(null);
    }
%>

<%!
    String writeGroupsAndItems(List<TodoGroup> todoGroups) {
        String groups = "<table border=\"1\" style=\"width:100%\">";
        int number = 1;
        for (TodoGroup todoGroup : todoGroups) {
            List<TodoItem> todoItemList = todoGroup.getTodoItems();
            if (todoItemList != null) {
                groups += "<tr>" + "<td>" + number + ")" + "</td>" +
                        "<td>" + todoGroup.getName() + "</td>";
                for (int i = 0; i < todoItemList.size(); i++){
                    groups += "<td>" + todoItemList.get(i).getTitle() + "</td>";
                }
                groups += "</tr>";
            } else {
                groups +=  "<tr>" + "<td>" + number + ")" + "</td>" +
                        "<td>" + todoGroup.getName() + "</td>" + "</tr>";
            }
            number++;
        }
        groups += "</table>";
        return groups;
    }
%>

<%!
    String addButtons(Boolean isAuth, List<TodoGroup> todoGroups, List<TodoGroup> deletableTodoGroups) {
        if (isAuth && deletableTodoGroups == null) {
            String codeForButtons = "";
            codeForButtons += "<form action=\"todoGroup\"method=\"POST\"\">" +
                    "Add Group:<br>" +
                    "<input type=\"text\" name=\"addedButtonName\">" +
                    "<br><br>" +
                    "<input type=\"submit\" value=\"Add\">" +
                    "</form>" +
                    "<br><br>";
            codeForButtons += "<form action=\"todoGroup\"method=\"POST\"\">" +
                    "Delete Group:<br>" +
                    "<input type=\"text\" name=\"deletedButtonName\">" +
                    "<br><br>" +
                    "<input type=\"submit\" value=\"Delete\">" +
                    "</form>" +
                    "<br><br>";
            codeForButtons += "<form action=\"todoGroup\"method=\"POST\"\">" +
                    "Add Item:<br>" +
                    "<input type=\"text\" name=\"addedItemName\">" +
                    "<br>" +
                    "<br>";
            codeForButtons += "Which group? <br>";
            for(int i = 0 ; i < todoGroups.size(); i++){
                String todoGroupName = todoGroups.get(i).getName();
                codeForButtons += "<input type=\"radio\" name=\"groupId\" value="+ todoGroups.get(i).getGroupId() +" checked> " + todoGroupName + "<br>";
            }
            codeForButtons += "<INPUT TYPE=\"submit\" value=\"Add\">" +
                    "</form>";


            return codeForButtons;
        }else if(deletableTodoGroups != null){
            return multipleOptionButtons(deletableTodoGroups, todoGroups);
        } else {
            return "<br> Login to change TodoGroups!";
        }
    }
%>

<%!
    String multipleOptionButtons(List<TodoGroup> todoGroups, List<TodoGroup> allTodoGroups){
        String code = "";

        code += "Which Group ID? <br>" +
                "<form action=\"todoGroup\" method=\"POST\">";

        List<Integer> IDs = getIdForGroupInTable(allTodoGroups, todoGroups.get(0));
        for(int i = 0 ; i < IDs.size(); i++){
            int todoGroupId = IDs.get(i);
            code += "<input type=\"radio\" name=\"extraGroupId\" value="+ todoGroups.get(i).getGroupId() +"> " + todoGroupId + "<br>";
        }
        code += "<INPUT TYPE=\"submit\">";
        code += "</form>";

        return code;
    }
%>

<%!
    List<Integer> getIdForGroupInTable(List<TodoGroup> todoGroups, TodoGroup todoGroup){
        List<Integer> IDs = new ArrayList<Integer>();
        for(int i = 0; i < todoGroups.size(); i++){
            if(todoGroups.get(i).getName().equals(todoGroup.getName())){
                IDs.add(new Integer(i + 1));
            }
        }

        return IDs;
    }
%>

<jsp:include page="/includes/footer.jsp"/>