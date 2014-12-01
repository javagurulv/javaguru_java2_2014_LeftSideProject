package lv.javaguru.java2.web.mvc.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

/**
 * Created by SM on 11/9/2014.
 */
public class MVCRequestParameters {
    private Long userId;
    private boolean userAuthenticated;
    private Map<String, String[]> parameters;

    public MVCRequestParameters(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        userId = Authentication.getUserId(session);
        userAuthenticated = Authentication.isLoggedIn(session);
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

    public Long getUserId() {
        return userId;
    }

    public boolean isUserAuthenticated() {
        return userAuthenticated;
    }
}
