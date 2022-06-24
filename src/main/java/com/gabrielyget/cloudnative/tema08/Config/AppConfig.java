package com.gabrielyget.cloudnative.tema08.Config;

import com.gabrielyget.cloudnative.tema08.Commands.*;
import com.gabrielyget.cloudnative.tema08.Handlers.HealthcheckResource;
import com.gabrielyget.cloudnative.tema08.Services.CalculatorService;
import com.netflix.karyon.health.HealthCheckHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.gabrielyget.cloudnative.tema08")
public class AppConfig {

    @Bean(name = "SUM")
    public Sum sum() {
        return new Sum();
    }

    @Bean(name = "SUB")
    public Sub sub() {
        return new Sub();
    }

    @Bean(name = "MULTIPLY")
    public Multiply multiply() {
        return new Multiply();
    }

    @Bean(name = "DIVISION")
    public Division division() {
        return new Division();
    }

    @Bean(name = "POW")
    public Pow pow() {
        return new Pow();
    }

    @Bean(name = "healthcheckHandler")
    public HealthCheckHandler healthcheckHandler() {
        return new HealthcheckResource();
    }

    @Bean(name = "calculatorService")
    public CalculatorService calculatorService(ApplicationContext applicationContext) {
        return new CalculatorService(applicationContext);
    }
}
