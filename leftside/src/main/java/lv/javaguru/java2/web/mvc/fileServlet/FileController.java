package lv.javaguru.java2.web.mvc.fileServlet;

import lv.javaguru.java2.web.mvc.core.MVCController;
import lv.javaguru.java2.web.mvc.core.MVCModel;
import lv.javaguru.java2.web.mvc.core.MVCProcessor;
import lv.javaguru.java2.web.mvc.core.MVCRequestParameters;

/**
 * Created by Sergey on 13.11.14.
 */

    @MVCController(path = "/file",
            pageName = "Files",
            isVisible = true)


    public class FileController implements MVCProcessor {
        private static final String DEFAULT_VIEW = "/file.jsp";


        @Override
        public MVCModel processRequest(MVCRequestParameters req) {

            FileModel fileModel = new FileModel();

            return new MVCModel(DEFAULT_VIEW, fileModel);
        }

    }






