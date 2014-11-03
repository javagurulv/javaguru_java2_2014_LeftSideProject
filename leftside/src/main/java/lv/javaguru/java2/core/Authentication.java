package lv.javaguru.java2.core;

import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by SM on 11/2/2014.
 */
public final class Authentication {
    private static final String sessionUserId = "user_id";

    public static final void authenticate(HttpSession session, User user) {
        authenticate(session, user.getUserId());
    }

    public static final void authenticate(HttpSession session, long userId) {
        session.setAttribute(sessionUserId, userId);
    }

    public static final boolean isLoggedIn(HttpSession session) {
        Long userId = getUserId(session);
        return null != userId;
    }
    public static final void invalidate(HttpSession session) {
        session.removeAttribute(sessionUserId);
    }

    public static final Long getUserId(HttpSession session) {
        return (Long) session.getAttribute(sessionUserId);
    }
}
