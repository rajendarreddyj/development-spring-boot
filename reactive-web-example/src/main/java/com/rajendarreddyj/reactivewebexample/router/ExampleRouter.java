package com.rajendarreddyj.reactivewebexample.router;


import com.rajendarreddyj.reactivewebexample.handler.ExampleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ExampleRouter {
    @Bean
    public RouterFunction<ServerResponse> route(ExampleHandler exampleHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/example")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), exampleHandler::hello)
                .andRoute(RequestPredicates.GET("/exampleFurther1")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), exampleHandler::helloFurther1)
                .andRoute(RequestPredicates.GET("/exampleFurther2")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), exampleHandler::helloFurther2)
                .andRoute(RequestPredicates.GET("/exampleFurther3")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), exampleHandler::helloFurther3);
    }
}
