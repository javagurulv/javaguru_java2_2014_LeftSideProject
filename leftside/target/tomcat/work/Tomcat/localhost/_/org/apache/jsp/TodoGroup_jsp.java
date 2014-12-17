/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2014-12-17 18:59:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import lv.javaguru.java2.domain.TodoItem;
import lv.javaguru.java2.web.mvc.todoGroupServlet.TodoGroupModel;
import java.util.List;
import lv.javaguru.java2.domain.TodoGroup;

public final class TodoGroup_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    String writeGroupsAndItems(List<TodoGroup> todoGroups) {
        String groups = "";
        int number = 1;
        for (TodoGroup todoGroup : todoGroups) {
            List<TodoItem> todoItemList = todoGroup.getTodoItems();
            if (todoItemList != null) {
                groups += number + ") " + todoGroup.getName() + " : " + getGroupItems(todoItemList) + "<br>";
            }else{
                groups += number + ") " + todoGroup.getName() + " : { }" + "<br>";
            }
            number++;
        }
        return groups;
    }


    String addButtonFunc(HttpServletRequest request, TodoGroupModel model) {
        String buttons = "";
        String add = request.getParameter("addedButtonName");
        String delete = request.getParameter("deletedButtonName");
        String isGroupAdded = isGroupAdded(add);
        String isGroupDeleted = isGroupDeleted(delete, model);
        if(isGroupAdded == "Incorrect name" || isGroupDeleted == "Incorrect name"){
            buttons = isGroupAdded;
        }else if(isGroupAdded == "true"){
            //TODO: POST/GET request
        }else if(isGroupDeleted == "true"){
            //TODO: POST/GET request
        }
        if(buttons == null){
            return " ";
        }else{
            return buttons;
        }
    }




    String isGroupAdded(String addedGroupName){
        String isGroupAdded = "";
        if(addedGroupName == null) {
            isGroupAdded = "false";
        }else if(addedGroupName == ""){
            isGroupAdded = "Incorrect name";
        }else if(addedGroupName != null){
            isGroupAdded = "true";
        }
        return isGroupAdded;
    }


    String isGroupDeleted(String deletedGroupName, TodoGroupModel model){
        TodoGroup group = model.getTodoGroupByName(deletedGroupName);
        if(deletedGroupName == null) {
            return "false";
        }else if(group == null){
            return "Incorrect name";
        }else{
            return "true";
        }
    }


    String getGroupItems(List<TodoItem> todoItemList) {
        String itemNames = "";

        for (TodoItem todoItem : todoItemList) {
            itemNames += todoItem.getTitle() + " ; ";
        }

        return "{ " + itemNames + "}";
    }


    String addButtons(){
        String codeForButtons = "";
        codeForButtons += "<form action=\"TodoGroup\"method=\"POST\"\">\n" +
                "Add Button:<br>\n" +
                "<input type=\"text\" name=\"addedButtonName\">\n" +
                "<br><br>\n" +
                "<input type=\"submit\" value=\"Add\">\n" +
                "</form>" +
                "<br><br>";
        codeForButtons += "<form action=\"TodoGroup\"method=\"POST\"\">\n" +
                "Delete Button:<br>\n" +
                "<input type=\"text\" name=\"deletedButtonName\">\n" +
                "<br><br>\n" +
                "<input type=\"submit\" value=\"Delete\">\n" +
                "</form>"+
                "<br><br>";


        return codeForButtons;
    }

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/header.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    //TODO: fix logics.
    TodoGroupModel model = (TodoGroupModel) request.getAttribute("model");
    List<TodoGroup> todoGroups = model.getAllTodoGroups();
    String buttonFunc = addButtonFunc(request, model);
    String groupsAndItems = writeGroupsAndItems(todoGroups);
    String buttons = addButtons();


      out.write("\r\n");
      out.write("\r\n");
      out.print(groupsAndItems);
      out.write('\r');
      out.write('\n');
      out.print(buttons);
      out.write('\r');
      out.write('\n');
      out.print(buttonFunc);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/footer.jsp", out, false);
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
