package maks.molch.dmitr.core;

import maks.molch.dmitr.core.config.JooqContextConfig;
import maks.molch.dmitr.core.config.security.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JooqContextConfig.DataSourceConfig.class, JwtConfig.class})
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

}
