package com.bridgelabz.bsa.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "users/register/.*",
            "users/login/.*"
    );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> {
                String path = serverHttpRequest.getURI().getPath();


                return openApiEndpoints.stream()
                        .noneMatch(uri -> path.matches(uri));
            };
}
