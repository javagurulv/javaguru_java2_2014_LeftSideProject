<%--
  Created by IntelliJ IDEA.
  User: alekmiku
  Date: 2014.12.15.
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title> :: Todo Items List :: </title>
</head>
<body>

<h3>Todo Item List</h3>
<c:if test="${!empty listItems}">
  <table class="tg" border="1">
    <tr>
      <th width="80">Item ID</th>
      <th width="120">State</th>
      <th width="120">Title</th>
      <th width="120">Description</th>
      <th width="120">DueDate</th>
      <th width="60">Edit</th>
      <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listItems}" var="item">
      <tr>
        <td>${item.getItemId()}</td>
        <td>${item.getStateId().getState()}</td>
        <td>${item.getTitle()}</td>
        <td>${item.getDescription()}</td>
        <td>${item.getDueDate()}</td>
        <td><a href="<c:url value='${contextPath}/item/edit/${item.getItemId()}' />" >Edit</a></td>
        <td><a href="<c:url value='${contextPath}/item/remove/${item.getItemId()}' />" >Delete</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<h3>
  Add or Edit todo item
</h3>


<c:url var="addAction" value="${contextPath}/item/add" ></c:url>

<form:form action="${addAction}" commandName="item">
  <table>
    <c:if test="${!empty item.getItemId()}">
      <tr>
        <td>
          <form:label path="itemId">
            <spring:message text="ID"/>
          </form:label>
        </td>
        <td>
          <form:input path="itemId" readonly="true" size="2"  disabled="true" />
          <form:hidden path="itemId" />
        </td>
      </tr>
    </c:if>
    <tr>
      <td>
        <form:label path="Title">
          <spring:message text="Title"/>
        </form:label>
      </td>
      <td>
        <form:input path="title" />
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="description">
          <spring:message text="Description"/>
        </form:label>
      </td>
      <td>
        <form:input path="description" />
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="dueDate">
          <spring:message text="DueDate"/>
        </form:label>
      </td>
      <td>
        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${item.getDueDate()}" var="duedateFormatted"/>
        <form:input path="dueDate" value="${duedateFormatted}" />
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="stateId">
          <spring:message text="State"/>
        </form:label>
      </td>
      <td>
        <form:input path="stateId.state" />
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <c:if test="${!empty item.getItemId()}">
          <input type="submit"
                 value="<spring:message text="Edit Item"/>" />
        </c:if>
        <c:if test="${empty item.getItemId()}">
          <input type="submit"
                 value="<spring:message text="Add Item"/>" />
        </c:if>
      </td>
    </tr>
  </table>
</form:form>
<br>

</body>
</html>
