package com.gabrielyget.cloudnative.tema08;

import com.gabrielyget.cloudnative.tema08.Config.AppConfig;
import com.gabrielyget.cloudnative.tema08.Handlers.RestRequestHandler;
import com.gabrielyget.cloudnative.tema08.Services.CalculatorService;
import com.netflix.karyon.health.HealthCheckHandler;
import com.netflix.karyon.transport.http.health.HealthCheckEndpoint;
import io.reactivex.netty.RxNetty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CalculatorApplicationRunner {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        HealthCheckHandler healthCheckHandler = applicationContext.getBean("healthcheckHandler", HealthCheckHandler.class);

        RxNetty.createHttpServer(8888, new RestRequestHandler("/healthcheck",
                        new HealthCheckEndpoint(healthCheckHandler),
                        applicationContext.getBean("calculatorService", CalculatorService.class)))
                .startAndWait();
    }
}
