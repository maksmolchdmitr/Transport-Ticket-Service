package maks.molch.dmitr.core.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.ParseNameCase;
import org.jooq.conf.RenderKeywordCase;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.jooq.tools.LoggerListener;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class JooqContextConfig {
    @Bean
    public DSLContext dslContext(DataSourceConfig config) throws SQLException {
        var connection = DriverManager.getConnection(config.url, config.username, config.password);
        var settings = new Settings()
                .withRenderKeywordCase(RenderKeywordCase.LOWER)
                .withParseNameCase(ParseNameCase.LOWER)
                .withRenderNameCase(RenderNameCase.LOWER);
        org.jooq.Configuration configuration = new DefaultConfiguration()
                .set(connection)
                .set(settings)
                .set(SQLDialect.POSTGRES)
                .set(new DefaultExecuteListenerProvider(
                        new LoggerListener()
                ));
        return DSL.using(configuration);
    }

    @ConfigurationProperties(prefix = "spring.datasource")
    public record DataSourceConfig(
            String url,
            String username,
            String password
    ) {
    }
}
