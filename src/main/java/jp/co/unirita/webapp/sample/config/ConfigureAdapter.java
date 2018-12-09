package jp.co.unirita.webapp.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConfigureAdapter extends WebMvcConfigurerAdapter {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Bean
    public InputData inputData() {
        return ddlAuto.equals("create") ? new InputData() : null;
    }
}
