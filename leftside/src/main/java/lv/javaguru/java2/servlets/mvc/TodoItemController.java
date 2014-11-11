package lv.javaguru.java2.servlets.mvc;

/**
 * Created by alekmiku on 2014.11.10..
 */
public class TodoItemController implements MVCController {

    @Override
    public MVCModel processRequest(MVCRequestParameters req) {
        return new MVCModel(getDefaultViewPath(), getPageName(),
                "Hello world from MVC!<br>" +
                "TestMessage from url param 'test': " + req.getValue("test"));
    }

    @Override
    public String getPath() {
        return "/TodoItem";
    }

    @Override
    public String getPageName() {
        return "TodoItems";
    }

    @Override
    public String getDefaultViewPath() {
        return "/TodoItem.jsp";
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
