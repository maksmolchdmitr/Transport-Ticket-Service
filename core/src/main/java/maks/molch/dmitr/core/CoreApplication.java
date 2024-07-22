package maks.molch.dmitr.core;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import maks.molch.dmitr.core.config.JooqContextConfig;
import maks.molch.dmitr.core.config.KafkaConfig;
import maks.molch.dmitr.core.config.security.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@OpenAPIDefinition(info = @Info(title = "Transport Ticket Service"))
@SpringBootApplication
@EnableConfigurationProperties({JooqContextConfig.DataSourceConfig.class, JwtConfig.JwtConfigProperties.class, KafkaConfig.KafkaProperties.class})
@EnableCaching
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

}
