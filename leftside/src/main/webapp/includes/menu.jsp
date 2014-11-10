<%@ page import="java.util.Collection" %>
<%@ page import="lv.javaguru.java2.web.mvc.core.RegisteredController" %>
<%
    Collection<RegisteredController> controllers = (Collection<RegisteredController>) request.getAttribute("controllers");
    boolean debug = (Boolean) request.getAttribute("debug");
    for (RegisteredController controller : controllers) {
        if (controller.isVisible() || debug) {
            %>
            <a href="<%=controller.getPath()%>"><%=controller.getPageName()%></a><br>
            <%
        }
    }
%>