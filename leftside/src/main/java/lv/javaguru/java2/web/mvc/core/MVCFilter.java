package lv.javaguru.java2.web.mvc.core;

import lv.javaguru.java2.web.spring.SpringAppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    Logger logger = LogManager.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    private Map<String, RegisteredController> processorMap;
    private RegisteredController defaultController;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        applicationContext = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        MVCControllerRegistrator registrator = new MVCControllerRegistrator(applicationContext);
        processorMap = registrator.registerAll();
        defaultController = registrator.getDefaultController(processorMap.values());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String contextURI = req.getServletPath();

        String path = req.getRequestURI();
        logger.info(path);
        //FixMe: skip static resource requests

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
        logger.info("View: " + model.getView());
        RequestDispatcher requestDispatcher =
                context.getRequestDispatcher(model.getView());
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}
