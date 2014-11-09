package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.core.ConfigReader;

import java.util.List;

/**
 * Created by Emils on 2014.11.08..
 */
public class MVCModel {

    private String view;
    private String title;
    private Object data;
    private List<String> errorList;


    public MVCModel(String view, String title, Object data, List<String> errorList) {
        this(view, title, data);
        this.errorList = errorList;
    }

    public MVCModel(String view, String title, Object data) {
        this(view, data);
        this.title = title;
    }

    public MVCModel(String view, Object data) {
        this.view = view;
        this.data = data;
        this.title = "MVC page";
    }

    public Object getData() {
        return data;
    }

    public String getView() {
        return view;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}
