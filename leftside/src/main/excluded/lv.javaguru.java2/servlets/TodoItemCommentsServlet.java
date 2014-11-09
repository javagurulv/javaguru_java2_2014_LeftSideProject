package lv.javaguru.java2.servlets;

import lv.javaguru.java2.core.Authentication;
import lv.javaguru.java2.core.ConfigReader;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoItemCommentDAO;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.database.jdbc.TodoItemCommentDAOImpl;
import lv.javaguru.java2.database.jdbc.TodoItemDAOImpl;
import lv.javaguru.java2.domain.TodoItem;
import lv.javaguru.java2.domain.TodoItemComment;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Long;
import java.lang.String;
import java.util.*;

/**
 * Created by SM on 11/7/2014.
 */
public class TodoItemCommentsServlet extends HttpServlet {
    private static final TodoItemDAO todoItemDAO = new TodoItemDAOImpl();
    private static final TodoItemCommentDAO commentDAO = new TodoItemCommentDAOImpl();
    private static ConfigReader config = new ConfigReader();
    private static final String paramItemId = "item";
    private static final String paramTitle = "title";
    private static final String paramMsg = "msg";
    private static final String paramReplyTo = "replyTo";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long userId = Authentication.getUserId(session);
        PrintWriter out = resp.getWriter();

        Long itemId = null;
        Long inReplyTo = null;
        try {
            itemId = tryParseLong(req.getParameter(paramItemId));
            inReplyTo = tryParseLong(req.getParameter(paramReplyTo));
        } finally {
            if (null == itemId) {
                out.println("No value set or formatting issues for param \"" + paramItemId + "\"");
                return;
            }
        }

        TodoItem todoItem = null;
        List<TodoItemComment> commentList = null;
        try {
            todoItem = todoItemDAO.getById(itemId);
            commentList = commentDAO.getByItemId(itemId);
        } catch (DBException e) {
            throw new ServletException(e);
        }

        printTodoItemInfo(out, todoItem);
        printCommentList(out, commentList, session);
        out.println("<br><br>");
        printNewCommentForm(out, itemId, inReplyTo);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();

        if (!Authentication.isLoggedIn(session)) {
            out.println("Please <a href='/login'>authorise</a> first.");
        } else {
            Long itemId = null;
            String title = null;
            String msg = null;
            Long replyTo = null;
            try {
                itemId = tryParseLong(req.getParameter(paramItemId));
                title = req.getParameter(paramTitle);
                msg = req.getParameter(paramMsg);
                replyTo = tryParseLong(req.getParameter(paramReplyTo));
            } finally {
                if (null == itemId) {
                    out.println("No value set or formatting issues for param \"" + paramItemId + "\"");
                    return;
                }
                if (null == title) {
                    out.println("No value set or insufficient length for param \"" + paramTitle + "\"");
                    return;
                }
                if (null == msg) {
                    out.println("No value set or  insufficient length for param \"" + paramMsg + "\"");
                    return;
                }
            }

            Long userId = Authentication.getUserId(session);
            dbCreateComment(userId, itemId, replyTo, title, msg);

            resp.sendRedirect("todoComments?" + paramItemId + "=" + itemId);
        }
    }

    private void dbCreateComment(Long userId, Long itemId, Long replyTo, String title, String msg) {
        TodoItemComment comment = new TodoItemComment();
        comment.setUserId(userId);
        comment.setItemId(itemId);
        comment.setReplyToID(replyTo);
        comment.setDate(DateTime.now());
        comment.setTitle(title);
        comment.setMessage(msg);

        commentDAO.create(comment);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    private void printTodoItemInfo(PrintWriter out, TodoItem item) {
        out.println("<br><b>Item</b> " + item.getItemId()
                + " <br><b>Title:</b> " + item.getTitle()
                + " <br><b>Description:</b> " + item.getDescription()
                + " <br><b>Due Date::</b>" + item.getDueDate()
                + " <br><hr><br>");
    }

    private void printCommentList(PrintWriter out, List<TodoItemComment> commentList, HttpSession srssion) {
        CommentTree commRoot = new CommentTree(null);
        Map<Long, CommentTree> hashCommentTrees = new HashMap<Long, CommentTree>();
        Map<Long, CommentTree> noRootTrees = new HashMap<Long, CommentTree>();
        hashCommentTrees.put(null, commRoot);

        for (TodoItemComment comment : commentList) {
            CommentTree tree = hashCommentTrees.get(comment.getReplyToID());
            if (null == tree) {
                tree = noRootTrees.get(comment.getReplyToID());
            }
            if (null == tree) {
                noRootTrees.put(comment.getCommentId(), new CommentTree(comment));
            } else {
                CommentTree t = new CommentTree(comment);
                tree.childs.add(t);
                hashCommentTrees.put(comment.getCommentId(), t);
            }
        }

        List<Long> commentsOutOfSynch = new ArrayList<Long>();
        for (CommentTree t : noRootTrees.values()) {
            CommentTree hashedTree = hashCommentTrees.get(t.comment.getReplyToID());
            if (null == hashedTree) {
                hashedTree = noRootTrees.get(t.comment.getReplyToID());
            }
            if (null == hashedTree) {
                commentsOutOfSynch.add(t.comment.getCommentId());
            } else {
                hashedTree.childs.add(t);
            }
        }

        if (0 < commentsOutOfSynch.size() && config.isDebugMode()) {
            out.println("<font color='red'>Please check data consistency for TodoItemComments : ");

            Iterator<Long> iter = commentsOutOfSynch.iterator();
            StringBuilder builder = new StringBuilder(iter.next().toString());
            while (iter.hasNext()) {
                builder.append(", ").append(iter.next().toString());
            }

            out.println(builder.toString() + "</font><br><br>");
        }

        if (0 < commRoot.childs.size()) {
            for (CommentTree tree : commRoot.childs) {
                out.println(tree.toHtml(srssion));
            }
        } else {
            out.println("No comments found, would you like to write first one?");
        }
    }

    private void printNewCommentForm(PrintWriter out, Long itemId, Long inReplyTo) {
        out.println("<h3 id='comment' name=;comment'>New comment:</h3><br>" +
                "<form method='POST' action='todoComments'>");
        if (null != inReplyTo) {
            out.println("<input type='hidden' name='" + paramReplyTo + "' value='" + inReplyTo + "'/><br>");
        }
        out.println("<input type='hidden' name='" + paramItemId + "' value='" + itemId + "'/><br>" +
                "Title: <input type='text' name='" + paramTitle + "'/><br>" +
                "Message: <textarea rows='5' name='" + paramMsg + "'></textarea><br>" +
                "<input type='submit'/>" +
                "</form>");
    }

    class CommentTree {
        TodoItemComment comment;
        public List<CommentTree> childs;

        CommentTree(TodoItemComment comment) {
            this.comment = comment;
            childs = new ArrayList<CommentTree>();
        }

        public String toHtml(HttpSession session) {
            String html = "";
            if (null != this.comment) {
                html += "Left by UserId: " + comment.getUserId() + "<br>" +
                        "Title: " + comment.getTitle() + "<br>" +
                        "Message: " + comment.getMessage() + "<br>" +
                        "Actions: ";
                if (Authentication.isLoggedIn(session)) {
                    html += getReplyAction(comment.getItemId(), comment.getCommentId());
                }
                html += "<br><br>";
            }
            if (0 < childs.size()) {
                html += "<div style='padding-left:25px;border-left: black dashed thin;'>";
                for (CommentTree tree : childs) {
                    html += tree.toHtml(session);
                }
                html += "</div>";
            }
            return html;
        }

        String getReplyAction(long itemId, long commentId) {
            return "<a href='todoComments?" + paramItemId + "=" + itemId + "&" + paramReplyTo + "=" + commentId + "#comment'>" +
                    "Reply</a>";
        }
    }

    public static Long tryParseLong(String txt) {
        try {
            return Long.parseLong(txt);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
