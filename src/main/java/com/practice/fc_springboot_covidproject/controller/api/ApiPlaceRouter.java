package com.practice.fc_springboot_covidproject.controller.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Deprecated
@Configuration
public class ApiPlaceRouter {



  @Bean
  public RouterFunction<ServerResponse> placeRouter(ApiPlaceHandler apiPlaceHandler) {
    return route().nest(path("/api/places"), builder -> builder
        .GET("", apiPlaceHandler::getPlaces)
        .POST("", req -> ServerResponse.ok().body(true))
        .GET("/{placeId}", req -> ServerResponse.ok().body("place" + req.pathVariable("placeId")))
        .PUT("/{placeId}", req -> ServerResponse.ok().body(true))
        .DELETE("/{placeId}", req -> ServerResponse.ok().body(true))
        ).build();

  }
}


/*
 * 기존 controller와 동일한 기능을 하나
 *  함수형으로 바꾼 코드
 *  위 코드는 ApiPlaceController에 있는 기능들인 CRUD와 동일하다.
 *
 *  기본적인 DI == private final ~~~~
 *
 *
 */