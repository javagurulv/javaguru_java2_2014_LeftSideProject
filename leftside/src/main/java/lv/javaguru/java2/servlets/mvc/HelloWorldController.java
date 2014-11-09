package lv.javaguru.java2.servlets.mvc;

/**
 * Created by Emils on 2014.11.08..
 */
public class HelloWorldController implements MVCController {

    @Override
    public MVCModel processRequest(MVCRequestParameters req) {
        return new MVCModel(getDefaultViewPath(), getPageName(),
                "Hello world from MVC!<br>" +
                "TestMessage from url param 'test': "+ req.getValue("test"));
    }

    @Override
    public String getPath() {
        return "/hello";
    }

    @Override
    public String getPageName() {
        return "HelloWorld";
    }

    @Override
    public String getDefaultViewPath() {
        return "/hello.jsp";
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
