<%@ page import="lv.javaguru.java2.web.mvc.fileServlet.FileModel" %>
<%@ page import="java.util.List" %>
<jsp:include page="/includes/header.jsp" />


<%
    FileModel model = (FileModel) request.getAttribute("model");
%>

<%=
    writeFiles(model)
%>

<%!
    String writeFiles(FileModel model) {

        String files ="";
        List<Long> itemList = model.getItemList();
        int itemListAmount = model.getItemListAmount();
        int fileAmount = model.getFileAmount();

        for(int i =0; i < itemListAmount; i++){
            long itemNumber = itemList.get(i);
            files = files + (i+1) + ". Item: " + "ID= " + itemNumber + ", Title= " + model.getTodoItem((int) itemNumber).getTitle() + "<br>";

            int fileCounter = 1;
            for(int j =0; j < fileAmount; j++){

                if(model.getFile(j).getTodoItemID() == itemNumber){
                    files = files + "_ _ _ " + fileCounter + ". File" + "Name= " + model.getFile(j).getFileName() + ", Path= " + model.getFile(j).getPath() + ", UploadTime= " + model.getFile(j).getUploadDate() + "<br>";
                    fileCounter++;
                }
            }
        }
        return files;
    }
%>

<jsp:include page="/includes/footer.jsp" />