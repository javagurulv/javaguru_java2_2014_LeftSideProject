<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title> :: File List :: </title>
</head>
<body>

<h3>File List</h3>
<c:if test="${!empty listFiles}">
    <table class="tg" border="1">
        <tr>
            <th width="80">File ID</th>
            <th width="120">Path</th>
            <th width="120">File name</th>
            <th width="120">Upload date</th>

        </tr>
        <c:forEach items="${listFiles}" var="item">
            <tr>
                <td>${item.getFileId()}</td>
                <td>${item.getPath()}</td>
                <td>${item.getFileName()}</td>
                <td><time>${item.getUploadDate()}</time></td>

            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>