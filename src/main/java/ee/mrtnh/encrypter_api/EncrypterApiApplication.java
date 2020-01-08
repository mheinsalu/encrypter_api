package ee.mrtnh.encrypter_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.time.LocalDateTime;

@SpringBootApplication
public class EncrypterApiApplication extends SpringBootServletInitializer {
// extends and override are needed to start as a .war without embedded Tomcat
    // spring initializer puts this in a separate class named ServletInitializer

    private static final Logger logger = LoggerFactory.getLogger(EncrypterApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EncrypterApiApplication.class, args);
        logger.info("Started EncrypterDemoApplication at " + LocalDateTime.now());
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EncrypterApiApplication.class);
    }

}
