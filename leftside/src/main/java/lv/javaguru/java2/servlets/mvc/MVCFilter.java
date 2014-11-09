package lv.javaguru.java2.servlets.mvc;

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

    private Map<String, MVCController> controllerMap;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        controllerMap = new HashMap<String, MVCController>();
        controllerMap.put("/hello", new HelloWorldController());

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String contextURI = req.getServletPath();

        String path = req.getRequestURI();
        System.out.println(path);

        MVCController controller = controllerMap.get(contextURI);

        MVCRequestParameters requestParameters = new MVCRequestParameters(req);
        MVCModel model = controller.processRequest(requestParameters);

        req.setAttribute("model", model.getData());
        ServletContext context =  req.getServletContext();
        System.out.println("View: " + model.getView());
        RequestDispatcher requestDispatcher =
                context.getRequestDispatcher(model.getView());
        requestDispatcher.forward(req,resp);
    }

    @Override
    public void destroy() {

    }
}
