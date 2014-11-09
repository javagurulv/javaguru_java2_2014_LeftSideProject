package lv.javaguru.java2.servlets.mvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * Created by SM on 11/9/2014.
 */
public class MVCRequestParameters {
    private Map<String, String[]> parameters;

    private MVCRequestParameters() {
        // Way to restrict default constructor usage
    }

    public MVCRequestParameters(HttpServletRequest httpServletRequest) {
        parameters = httpServletRequest.getParameterMap();
    }

    public Set<String> getParameterNames() {
        return parameters.keySet();
    }

    public boolean containsKey(String key) {
        return parameters.containsKey(key);
    }

    public String[] getValueArray(String key) {
        return parameters.get(key);
    }

    public String getValue(String key) {
        String[] arr = parameters.get(key);
        if (null != arr && 0 < arr.length) {
            return arr[0];
        } else {
            return null;
        }
    }

}
