<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.List" %>
</div>
        </div>
<%
    boolean debug = (Boolean) request.getAttribute("debug");
    List<String> errorList = (List<String>) request.getAttribute("errorList");
    if (debug && null != errorList && 0 < errorList.size()) {
        %>
        <div style="padding-top: 50px;">
            Errors occurred: <%
            for (String err : errorList) {
                %>
                    * <%=err%><br>
                <%
            }
        %>
        </div>
        <%
    }

    if (debug) {
        %><br><br><hr><h1>Debug section</h1>
        <h2>General information</h2>
        The request URI: <%=request.getRequestURI()%><br>
        Method: <%= request.getMethod()%><br>

        <h2>Request parameters:</h2><%
        Enumeration<String> parameterNames = request.getParameterNames();
        if (parameterNames.hasMoreElements()) {
            %><table><thead><tr><td>Name</td><td>Value</td></tr></thead><tbody><%
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);
                %><tr><td><%=name%></td><td><%=value%></td></tr><%
            }
            %></tbody></table><br><%
        } else {
            %>---None---<br><%
        }

        %><h2>Session information</h2><%
        if (null == session) {
            %>---No session---<br><%
        } else {
            Enumeration<String> attributeNames = session.getAttributeNames();
            if (attributeNames.hasMoreElements()) {
                %><table><thead><tr><td>Name</td><td>Value</td></tr></thead><tbody><%
                while (attributeNames.hasMoreElements()) {
                    String name = attributeNames.nextElement();
                    Object value = session.getAttribute(name);
                    %><tr><td><%=name%></td><td><%=value.toString()%></td></tr><%
                }
                %></tbody></table><br><%
            } else {
                %>--- No attributes set ---<br><%
            }
        }
    }
%>
    </body>
</html>
