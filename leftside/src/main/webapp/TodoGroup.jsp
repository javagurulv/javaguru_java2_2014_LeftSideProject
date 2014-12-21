<jsp:include page="/includes/header.jsp"/>
<%@ page import="lv.javaguru.java2.domain.TodoItem" %>
<%@ page import="lv.javaguru.java2.web.mvc.todoGroupServlet.TodoGroupModel" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.TodoGroup" %>

<%
    //TODO: fix logics.
    TodoGroupModel model = (TodoGroupModel) request.getAttribute("model");
    List<TodoGroup> todoGroups = model.getTodoGroups();
    String error = model.getMessage();
    //String isAuth = (String)request.getAttribute("auth");
    String isAuth = "true";

    String groupsAndItems = writeGroupsAndItems(todoGroups);
    String buttons = addButtons(isAuth);

%>

<%=groupsAndItems%>
<%=buttons%>
<% if (error != null) {%>
<br> Message: <%=error%>
<%model.setMessage(null);}%>

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
    String getGroupItems(List<TodoItem> todoItemList) {
        String itemNames = "";

        for (TodoItem todoItem : todoItemList) {
            itemNames += todoItem.getTitle() + " ; ";
        }

        return "{ " + itemNames + "}";
    }
%>

<%!
    String addButtons(String isAuth){
        if(isAuth == "true"){
        String codeForButtons = "";
        codeForButtons += "<form action=\"todoGroup\"method=\"POST\"\">\n" +
                "Add Button:<br>\n" +
                "<input type=\"text\" name=\"addedButtonName\">\n" +
                "<br><br>\n" +
                "<input type=\"submit\" value=\"Add\">\n" +
                "</form>" +
                "<br><br>";
        codeForButtons += "<form action=\"todoGroup\"method=\"POST\"\">\n" +
                "Delete Button:<br>\n" +
                "<input type=\"text\" name=\"deletedButtonName\">\n" +
                "<br><br>\n" +
                "<input type=\"submit\" value=\"Delete\">\n" +
                "</form>"+
                "<br><br>";


        return codeForButtons;
    }else {
        return "Login to post new TodoGroups";
    }
    }
%>
<jsp:include page="/includes/footer.jsp"/>