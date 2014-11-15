package lv.javaguru.java2.web.mvc.helloServlet;

import lv.javaguru.java2.web.mvc.core.*;
import org.springframework.stereotype.Component;

/**
 * Created by Emils on 2014.11.08..
 */
@Component
@MVCController(path = "/hello",
        pageName = "HelloWorld",
        isVisible = true)
@MVCDefaultController
public class HelloWorldController implements MVCProcessor {

    @Override
    public MVCModel processRequest(MVCRequestParameters req) {
        return new MVCModel("/hello.jsp",
                "Hello world from MVC!<br>" +
                        "TestMessage from url param 'test': " + req.getValue("test"));
    }
}
