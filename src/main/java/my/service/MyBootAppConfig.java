package my.service;

import my.service.component.MyDataBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBootAppConfig {

    @Bean
    MyDataBean myDataBean() {
        return new MyDataBean();
    }
}
