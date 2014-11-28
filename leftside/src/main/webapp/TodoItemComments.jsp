<%@ page import="lv.javaguru.java2.domain.TodoItem" %>
<%@ page import="lv.javaguru.java2.domain.TodoItemComment" %>
<%@ page import="lv.javaguru.java2.web.mvc.core.Authentication" %>
<%@ page import="lv.javaguru.java2.web.mvc.todoItemCommentServlet.TodoItemCommentsModel" %>
<%@ page import="lv.javaguru.java2.web.mvc.todoItemCommentServlet.TodoItemCommentsTree" %>
<jsp:include page="/includes/header.jsp" />
<%
    boolean isAuthenticated = Authentication.isLoggedIn(session);
    TodoItemCommentsModel model = (TodoItemCommentsModel)request.getAttribute("model");
    TodoItem item = model.getTodoItem();
    TodoItemCommentsTree commentsTree = model.getCommentTree();
    Long responseTo = model.getReplyCommentId();

    String commentForm;
            if (null != item && isAuthenticated) {
                commentForm = "<h3 id='comment' name=;comment'>New comment:</h3>" +
                        "<form method='POST' action='todoComments'>" +
                        "<input type='hidden' name='act' value='create'/>";
                if (null != responseTo) {
                    commentForm += "<input type='hidden' name='replyTo' value='" + responseTo + "'/>";
                }
                commentForm += "<input type='hidden' name='item' value='" + item.getItemId() + "'/>" +
                        "Title: <input type='text' name='title'/><br>" +
                        "Message: <textarea rows='5' name='msg'></textarea><br>" +
                        "<input type='submit'/>" +
                        "</form>";
            } else {
                commentForm = "You cannot leave comment.";
            }

%>

<%!
    String printCommTree(TodoItemCommentsTree commentsTree, boolean isAuthenticated, Long responseTo, String commForm) {
        if (null == commentsTree) return "No data to display.";
        String html = "";
        TodoItemComment comment = commentsTree.comment;
        if (null != comment) {
            html += comment.getCommentId()+" Left by UserId: " + comment.getUserId() + "<br>" +
                    "Title: " + comment.getTitle() + "<br>" +
                    "Message: " + comment.getMessage() + "<br>" +
                    "Actions: ";
            if (isAuthenticated) {
                html += "<a href='todoComments?item=" + comment.getItemId() + "&replyTo=" + comment.getCommentId() + "'>" +
                        "Reply</a>";
            }
            if (null != responseTo && responseTo == comment.getCommentId()) {
                html += "<br>"+commForm;
            }
            html += "<br><br>";
        }
        if (0 < commentsTree.childs.size()) {
            html += "<div style='padding-left:25px;border-left: black dashed thin;'>";
            for (TodoItemCommentsTree tree : commentsTree.childs) {
                html += printCommTree(tree, isAuthenticated, responseTo, commForm);
            }
            html += "</div>";
        }
        return html;
    }
%>
<h2>Item</h2>
<% if (null != item) { %>
    <b>Item id:</b> <%=item.getItemId()%>
    <br><b>Title:</b> <%=item.getTitle()%>
    <br><b>Description:</b> <%=item.getDescription()%>
    <br><b>Due Date:</b> <%=item.getDueDate()%>
    <br>
<%} else {%>
    No data to display.
<%}%>
<hr><br>
<h2>Comments</h2>
<%=printCommTree(commentsTree, isAuthenticated, responseTo, commentForm)%>

<%
    if (null == responseTo) {
        %>
        <br><%=commentForm%>
        <%
    }
%>

<jsp:include page="/includes/footer.jsp" />