package maks.molch.dmitr.analytic;

import maks.molch.dmitr.analytic.config.JooqContextConfig;
import maks.molch.dmitr.analytic.config.KafkaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties({KafkaConfig.KafkaProps.class, JooqContextConfig.DataSourceConfig.class})
public class AnalyticApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticApplication.class, args);
    }

}
