package lv.javaguru.java2.web.mvc.core;

import java.util.List;

/**
 * Created by Emils on 2014.11.08..
 */
public class MVCModel {

    private String view;
    private Object data;
    private List<String> errorList;


    public MVCModel(String view, Object data, List<String> errorList) {
        this(view, data);
        this.errorList = errorList;
    }

    public MVCModel(String view, Object data) {
        this.view = view;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public String getView() {
        return view;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}
