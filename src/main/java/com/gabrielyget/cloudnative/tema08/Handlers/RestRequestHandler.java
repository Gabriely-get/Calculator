package com.gabrielyget.cloudnative.tema08.Handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gabrielyget.cloudnative.tema08.Builders.JsonResponseBuilder;
import com.gabrielyget.cloudnative.tema08.Commands.OperationsType;
import com.gabrielyget.cloudnative.tema08.CustomExceptions.DivisionException;
import com.gabrielyget.cloudnative.tema08.CustomExceptions.EndpointException;
import com.gabrielyget.cloudnative.tema08.CustomExceptions.JsonBuilderException;
import com.gabrielyget.cloudnative.tema08.CustomExceptions.PowException;
import com.gabrielyget.cloudnative.tema08.Services.CalculatorService;
import com.netflix.karyon.transport.http.health.HealthCheckEndpoint;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import rx.Observable;

import java.util.Locale;

public class RestRequestHandler implements RequestHandler<ByteBuf, ByteBuf> {
    private double value1;
    private double value2;
    private double result;
    private String operation;

    private final String healthCheckUri;
    private final CalculatorService calculatorService;
    private final HealthCheckEndpoint healthCheckEndpoint;

    public RestRequestHandler(String healthCheckUri, HealthCheckEndpoint healthCheckEndpoint, CalculatorService calculatorService) {
        this.healthCheckUri = healthCheckUri;
        this.calculatorService = calculatorService;
        this.healthCheckEndpoint = healthCheckEndpoint;
    }

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {

        try {
            if (request.getUri().startsWith(healthCheckUri)) {
                return healthCheckEndpoint.handle(request, response);

            } else if (request.getUri().substring(10).indexOf("?") == 0) {
                try {

                    this.inputsValidation(new String[]{
                            request.getQueryParameters().get("value1").get(0),
                            request.getQueryParameters().get("value2").get(0),
                            request.getQueryParameters().get("operation").get(0).toLowerCase(Locale.ROOT)
                        }
                    );

                    double result;

                    switch (this.operation) {
                        case "sum":
                            this.result = this.calculatorService.calculate(this.value1, this.value2, OperationsType.SUM);

                            response.setStatus(HttpResponseStatus.OK);

                            response.writeStringAndFlush(new JsonResponseBuilder()
                                    .withMessage("Calculated successfully")
                                    .withOperation(OperationsType.SUM)
                                    .withResult(this.result).build()
                            );

                            break;
                        case "sub":
                            this.result = this.calculatorService.calculate(this.value1, this.value2, OperationsType.SUB);

                            response.setStatus(HttpResponseStatus.OK);

                            response.writeStringAndFlush(new JsonResponseBuilder()
                                    .withMessage("Calculated successfully")
                                    .withOperation(OperationsType.SUB)
                                    .withResult(this.result).build()
                            );

                            break;
                        case "div":
                            this.result = this.calculatorService.calculate(this.value1, this.value2, OperationsType.DIVISION);

                            response.setStatus(HttpResponseStatus.OK);

                            response.writeStringAndFlush(new JsonResponseBuilder()
                                    .withMessage("Calculated successfully")
                                    .withOperation(OperationsType.DIVISION)
                                    .withResult(this.result).build()
                            );

                            break;
                        case "multiply":
                            this.result = this.calculatorService.calculate(this.value1, this.value2, OperationsType.MULTIPLY);

                            response.setStatus(HttpResponseStatus.OK);

                            response.writeStringAndFlush(new JsonResponseBuilder()
                                    .withMessage("Calculated successfully")
                                    .withOperation(OperationsType.MULTIPLY)
                                    .withResult(this.result).build()
                            );

                            break;
                        case "pow":
                            this.result = this.calculatorService.calculate(this.value1, this.value2, OperationsType.POW);

                            response.setStatus(HttpResponseStatus.OK);

                            response.writeStringAndFlush(new JsonResponseBuilder()
                                    .withMessage("Calculated successfully")
                                    .withOperation(OperationsType.POW)
                                    .withResult(this.result).build()
                            );

                            break;
                        default:
                            throw new IllegalArgumentException("Operation type not found");
                    }

                } catch (PowException | DivisionException | IllegalArgumentException e) {
                    response.setStatus(HttpResponseStatus.BAD_REQUEST);

                    response.writeStringAndFlush(new JsonResponseBuilder()
                            .withError(this.operation,
                                    e.getMessage())
                            .build()
                    );

                    e.printStackTrace();
                }
            } else {
                response.setStatus(HttpResponseStatus.BAD_REQUEST);

                response.writeStringAndFlush(new JsonResponseBuilder()
                        .withError(null,
                                "This endpoint doesn't exists")
                        .build()
                );

                throw new EndpointException("This endpoint doesn't exists");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            response.setStatus(HttpResponseStatus.BAD_REQUEST);

            response.writeStringAndFlush("{\"Error\":\"An error occurred on build the response\"}");

            throw new JsonBuilderException("An error occurred on build the response");
        }

        throw new RuntimeException("An unexpected error occurred");
    }

    private void inputsValidation(String[] inputs) {
        for (String input: inputs) {
            if (input == null || input.isEmpty() || input.equals("null"))
                throw new IllegalArgumentException("Input can't be null");
        }

        this.operation = inputs[2];

        try {
            this.value1 = Double.parseDouble(inputs[0]);
            this.value2 = Double.parseDouble(inputs[1]);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value for calculate");
        }
    }
}