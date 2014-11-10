package lv.javaguru.java2.web.mvc.core;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Emils on 2014.11.08..
 */
public class MVCFilter implements Filter {
    ConfigReader config = new ConfigReader();

    private Map<String, RegisteredController> processorMap;
    private RegisteredController defaultController;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        MVCControllerRegistrator registrator = new MVCControllerRegistrator();
        processorMap = registrator.registerAll();
        defaultController = registrator.getDefaultController(processorMap.values());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String contextURI = req.getServletPath();

        String path = req.getRequestURI();
        System.out.println(path);

        RegisteredController controller;
        if (processorMap.containsKey(contextURI)) {
            controller = processorMap.get(contextURI);
        } else {
            controller = defaultController;
        }

        MVCRequestParameters requestParameters = new MVCRequestParameters(req);
        MVCModel model = controller.getProcessor().processRequest(requestParameters);

        req.setAttribute("model", model.getData());
        req.setAttribute("errorList", model.getErrorList());
        req.setAttribute("controllers", processorMap.values());
        req.setAttribute("title", controller.getPageName());
        req.setAttribute("debug", config.isDebugMode());

        ServletContext context = req.getServletContext();
        System.out.println("View: " + model.getView());
        RequestDispatcher requestDispatcher =
                context.getRequestDispatcher(model.getView());
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}
