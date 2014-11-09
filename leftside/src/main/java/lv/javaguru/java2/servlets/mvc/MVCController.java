package lv.javaguru.java2.servlets.mvc;

/**
 * Created by Emils on 2014.11.08..
 */
public interface MVCController {

    MVCModel processRequest(MVCRequestParameters requestParameters);

}
