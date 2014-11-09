package lv.javaguru.java2.servlets.mvc;

/**
 * Created by Emils on 2014.11.08..
 */
public class HelloWorldController implements MVCController {

    @Override
    public MVCModel processRequest(MVCRequestParameters req) {
        return new MVCModel("/hello.jsp", "Hello world from MVC!<br>" +
                "TestMessage from url param 'test': "+ req.getValue("test"));
    }
}
