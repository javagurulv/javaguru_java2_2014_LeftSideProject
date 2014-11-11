package lv.javaguru.java2.web.mvc.todoItemCommentServlet;

import lv.javaguru.java2.database.TodoItemCommentDAO;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.database.jdbc.TodoItemCommentDAOImpl;
import lv.javaguru.java2.database.jdbc.TodoItemDAOImpl;
import lv.javaguru.java2.domain.TodoItem;
import lv.javaguru.java2.domain.TodoItemComment;
import lv.javaguru.java2.web.mvc.core.MVCController;
import lv.javaguru.java2.web.mvc.core.MVCModel;
import lv.javaguru.java2.web.mvc.core.MVCProcessor;
import lv.javaguru.java2.web.mvc.core.MVCRequestParameters;
import org.joda.time.DateTime;

import java.util.*;

import static lv.javaguru.java2.web.mvc.todoItemCommentServlet.TodoItemCommentsController.Action.VIEW;

/**
 * Created by SM on 11/9/2014.
 */
@MVCController(path = "/todoComments",
        pageName = "ToDo Comments",
        isVisible = true)
public class TodoItemCommentsController implements MVCProcessor {
    private static final String DEFAULT_VIEW = "/TodoItemComments.jsp";
    private static final TodoItemDAO todoItemDAO = new TodoItemDAOImpl();
    private static final TodoItemCommentDAO commentDAO = new TodoItemCommentDAOImpl();

    public static Long tryParseLong(String txt) {
        try {
            return Long.parseLong(txt);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static Action tryParseAction(String txt) {
        try {
            return Action.valueOf(txt.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return VIEW;
        } catch (NullPointerException ex) {
            return VIEW;
        }
    }

    @Override
    public MVCModel processRequest(MVCRequestParameters parameters) {
        Action action = tryParseAction(parameters.getValue("act"));
        Long itemId = tryParseLong(parameters.getValue("item"));
        Long inReplyTo = tryParseLong(parameters.getValue("replyTo"));
        List<String> errList = new ArrayList<String>();

        if (!parametersValidation(itemId)) {
            errList.add("No value set or formatting issues for param itemId");
            return new MVCModel(DEFAULT_VIEW,
                    new TodoItemCommentsModel(null, null, null), errList);
        }

        TodoItem todoItem = todoItemDAO.getById(itemId);

        switch (action) {
            case CREATE:
                createComment(itemId, inReplyTo, parameters, errList);
                break;
            case DELETE: //ToDo: implement comment deletion with safety check
                break;
        }

        List<TodoItemComment> commentList = commentDAO.getByItemId(itemId);

        TodoItemCommentsTree commRoot = new TodoItemCommentsTree(null);
        List<Long> commentsOutOfSync = constructTree(commentList, commRoot);
        if (0 < commentsOutOfSync.size()) {
            errList.add("Please check data consistency for TodoItemComments : "
                    + implodeList(commentsOutOfSync));
        }

        TodoItemCommentsModel model = new TodoItemCommentsModel(todoItem, commRoot, inReplyTo);
        return new MVCModel(DEFAULT_VIEW, model, errList);
    }

    private void createComment(Long itemId, Long replyTo, MVCRequestParameters parameters, List<String> errList) {
        if (!parameters.isUserAuthenticated()) {
            errList.add("User is not authenticated.");
            return;
        }

        String title = parameters.getValue("title");
        String msg = parameters.getValue("msg");
        Long userId = parameters.getUserId();

        if (validateNewComment(errList, title, msg)) {
            dbCreateComment(userId, itemId, replyTo, title, msg);
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

    private boolean validateNewComment(List<String> errList, String title, String msg) {
        boolean res = true;
        if (null == title) {
            errList.add("No value set or insufficient length for param title");
            res = false;
        }
        if (null == msg) {
            errList.add("No value set or  insufficient length for param msg");
            res = false;
        }
        return res;
    }

    public String implodeList(List<Long> longList) {
        Iterator<Long> iter = longList.iterator();
        StringBuilder stringBuilder = new StringBuilder(iter.next().toString());
        while (iter.hasNext()) {
            stringBuilder.append(", ").append(iter.next().toString());
        }
        return stringBuilder.toString();
    }

    private List<Long> constructTree(List<TodoItemComment> commentList, TodoItemCommentsTree commRoot) {
        Map<Long, TodoItemCommentsTree> hashCommentTrees = new HashMap<Long, TodoItemCommentsTree>();
        Map<Long, TodoItemCommentsTree> noRootTrees = new HashMap<Long, TodoItemCommentsTree>();
        hashCommentTrees.put(null, commRoot);

        for (TodoItemComment comment : commentList) {
            TodoItemCommentsTree tree = hashCommentTrees.get(comment.getReplyToID());
            if (null == tree) {
                tree = noRootTrees.get(comment.getReplyToID());
            }
            if (null == tree) {
                noRootTrees.put(comment.getCommentId(), new TodoItemCommentsTree(comment));
            } else {
                TodoItemCommentsTree t = new TodoItemCommentsTree(comment);
                tree.childs.add(t);
                hashCommentTrees.put(comment.getCommentId(), t);
            }
        }

        List<Long> commentsOutOfSynch = new ArrayList<Long>();
        for (TodoItemCommentsTree t : noRootTrees.values()) {
            TodoItemCommentsTree hashedTree = hashCommentTrees.get(t.comment.getReplyToID());
            if (null == hashedTree) {
                hashedTree = noRootTrees.get(t.comment.getReplyToID());
            }
            if (null == hashedTree) {
                commentsOutOfSynch.add(t.comment.getCommentId());
            } else {
                hashedTree.childs.add(t);
            }
        }

        return commentsOutOfSynch;
    }

    private boolean parametersValidation(Long itemId) {
        return null != itemId;
    }

    enum Action {
        VIEW("view"), CREATE("create"), DELETE("delete");
        String action;

        Action(String action) {
            this.action = action;
        }
    }
}
