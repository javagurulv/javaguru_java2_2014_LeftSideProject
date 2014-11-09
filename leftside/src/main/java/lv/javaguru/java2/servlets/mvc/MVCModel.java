package lv.javaguru.java2.servlets.mvc;

/**
 * Created by Emils on 2014.11.08..
 */
public class MVCModel {

    private String view;
    private Object data;

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

}
