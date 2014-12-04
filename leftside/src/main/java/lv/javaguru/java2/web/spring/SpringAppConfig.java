package lv.javaguru.java2.web.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by MuromcevS on 15/11/2014.
 */
@Configuration
@ComponentScan(basePackages = {"lv.javaguru.java2"})
@Import({HibernateConfig.class})
public class SpringAppConfig {

    
}
