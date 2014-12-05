package lv.javaguru.java2.web.mvc.core;

/**
 * Created by SM on 11/10/2014.
 */
public interface  MVCProcessor {
    //ToDo: Separate GET and POST methods
    public  MVCModel processRequest(MVCRequestParameters req);
}
