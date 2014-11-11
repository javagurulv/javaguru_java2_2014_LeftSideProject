package lv.javaguru.java2.web.mvc.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Emils on 2014.11.08..
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MVCController {
    String path();

    String pageName() default "MVC Page";

    boolean isVisible() default false;
}
