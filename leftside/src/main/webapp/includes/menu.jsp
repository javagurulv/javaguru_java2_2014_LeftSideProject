<%@ page import="lv.javaguru.java2.servlets.mvc.MVCController" %>
<%@ page import="java.util.Collection" %>
<%
    Collection<MVCController> controllers = (Collection<MVCController>) request.getAttribute("controllers");
    boolean debug = (Boolean) request.getAttribute("debug");
    for (MVCController controller : controllers) {
        if (controller.isVisible() || debug) {
            %>
            <a href="<%=controller.getPath()%>"><%=controller.getPageName()%></a><br>
            <%
        }
    }
%>