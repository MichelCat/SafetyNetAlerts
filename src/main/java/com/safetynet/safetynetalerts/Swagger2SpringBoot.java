package com.safetynet.safetynetalerts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.oas.annotations.EnableOpenApi;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.safetynet.safetynetalerts.configuration.LocalDateConverter;
import com.safetynet.safetynetalerts.configuration.LocalDateTimeConverter;


@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = {"com.safetynet.safetynetalerts", "com.safetynet.safetynetalerts.api", "com.safetynet.safetynetalerts.configuration"})
public class Swagger2SpringBoot implements CommandLineRunner {

  public static void main(String[] args) {
    new SpringApplication(Swagger2SpringBoot.class).run(args);
  }

  @Configuration
  static class CustomDateConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addFormatters(FormatterRegistry registry) {
      registry.addConverter(new LocalDateConverter("yyyy-MM-dd"));
      registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSS"));
    }
  }

  @Override
  public void run(String... args) throws Exception {
  }
}
