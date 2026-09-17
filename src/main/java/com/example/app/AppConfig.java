package com.example.app;

import com.example.app.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
//@ConditionalOnClass(DataSource.class)
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    @Value("users.admin")
    private String admin;
    private String name;

    @Bean
    public AuthorizationService authorizationService() {
        return new AuthorizationService();
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder().build();
//    }

//    @Bean
//    @ConditionalOnProperty(name = "app.jdbc", havingValue = "true")
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

}
