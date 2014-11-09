package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.core.ConfigReader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emils on 2014.11.08..
 */
public class MVCFilter implements Filter {
    ConfigReader config = new ConfigReader();

    private Map<String, MVCController> controllerMap;
    private MVCController defaultController;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultController = new HelloWorldController();

        controllerMap = new HashMap<String, MVCController>();
        registerController(defaultController);
        registerController(new TodoItemCommentsController());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String contextURI = req.getServletPath();

        String path = req.getRequestURI();
        System.out.println(path);

        MVCController controller;
        if (controllerMap.containsKey(contextURI)) {
            controller = controllerMap.get(contextURI);
        } else {
            controller = defaultController;
        }

        MVCRequestParameters requestParameters = new MVCRequestParameters(req);
        MVCModel model = controller.processRequest(requestParameters);

        req.setAttribute("title", model.getTitle());
        req.setAttribute("model", model.getData());
        req.setAttribute("errorList", model.getErrorList());
        req.setAttribute("controllers", controllerMap.values());
        req.setAttribute("debug", config.isDebugMode());

        ServletContext context =  req.getServletContext();
        System.out.println("View: " + model.getView());
        RequestDispatcher requestDispatcher =
                context.getRequestDispatcher(model.getView());
        requestDispatcher.forward(req,resp);
    }

    @Override
    public void destroy() {
    }

    private void registerController(MVCController controller) {
        if (controllerMap.containsKey(controller.getPath())) {
            throw new RuntimeException("Controller mapping: path clash occurred");
        }
        controllerMap.put(controller.getPath(), controller);
    }
}
