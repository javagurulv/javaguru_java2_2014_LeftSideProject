package lv.javaguru.java2.web.mvc.core;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.util.*;

/**
 * Created by SM on 11/11/2014.
 */
public class MVCControllerRegistrator {

    private static final String PREFIX = "lv.javaguru.java2.web.mvc";

    public Map<String, RegisteredController> registerAll() {
        MVCController annotation = null;
        try {
            final Reflections reflections = new Reflections(PREFIX, new TypeAnnotationsScanner());
            Set<Class<?>> allControllers = reflections.getTypesAnnotatedWith(MVCController.class);

            HashMap mappedControllers = new HashMap<String, RegisteredController>();

            for (Class<?> controller : allControllers) {
                annotation = controller.getAnnotation(MVCController.class);
                MVCProcessor processor = (MVCProcessor) controller.newInstance();
                RegisteredController reg = new RegisteredController(annotation.path(),
                        annotation.pageName(),
                        annotation.isVisible(),
                        processor);

                if (mappedControllers.containsKey(reg.getPath())) {
                    throw new RuntimeException("Controller mapping: path clash occurred, search by " + reg.getPath());
                }
                mappedControllers.put(reg.getPath(), reg);
            }

            return mappedControllers;
        } catch (InstantiationException e) {
            if (null != annotation) {
                throw new RuntimeException("Cannot instantiate controller for path " + annotation.path(), e);
            } else {
                throw new RuntimeException(e);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public RegisteredController getDefaultController(Collection<RegisteredController> controllers) {
        final Reflections reflections = new Reflections(PREFIX, new TypeAnnotationsScanner());
        Set<Class<?>> allControllers = reflections.getTypesAnnotatedWith(MVCDefaultController.class);

        if (1 < allControllers.size()) {
            throw new RuntimeException("More than one controller is mapped as default: " + implodeSet(allControllers));
        }
        Class defaultClass = (Class) allControllers.toArray()[0];
        RegisteredController defaultController = null;
        for (RegisteredController reg : controllers) {
            if (reg.getProcessor().getClass() == defaultClass) {
                defaultController = reg;
                break;
            }
        }
        if (0 == allControllers.size()) {
            System.out.println("INFO: No controller mapped as default.");
        } else if (null == defaultController) {
            throw new RuntimeException("Default class is not mapped: " + defaultClass.getName());
        }

        return defaultController;
    }

    private String implodeSet(Set<Class<?>> cList) {
        Iterator<Class<?>> iter = cList.iterator();
        StringBuilder stringBuilder = new StringBuilder(iter.next().getClass().getName());
        while (iter.hasNext()) {
            stringBuilder.append(", ").append(iter.next().getClass().getName());
        }
        return stringBuilder.toString();
    }
}
