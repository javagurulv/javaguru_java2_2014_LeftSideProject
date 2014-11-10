package lv.javaguru.java2.web.mvc.core;

public class RegisteredController {
    private String path;
    private String pageName;
    private boolean isVisible;
    private MVCProcessor processor;

    public RegisteredController(String path, String pageName, boolean isVisible, MVCProcessor processor) {
        this.path = path;
        this.pageName = pageName;
        this.isVisible = isVisible;
        this.processor = processor;
    }

    public String getPath() {
        return path;
    }

    public String getPageName() {
        return pageName;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public MVCProcessor getProcessor() {
        return processor;
    }
}