package com.bridgelabz.bsa.apigateway.filter;

import com.bridgelabz.bsa.apigateway.exception.InvalidTokenException;
import com.bridgelabz.bsa.apigateway.exception.MissingAuthorizationHeaderException;
import com.bridgelabz.bsa.apigateway.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtFilter extends AbstractGatewayFilterFactory<Object> {

    private RouteValidator validator;
    private JwtUtil jwtUtil;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest serverHttpRequest = null;
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new MissingAuthorizationHeaderException("Missing authorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                } else {
                    throw new InvalidTokenException("Invalid token format in Authorization header");
                }
                try {
                    jwtUtil.validateJwtToken(authHeader);
                    serverHttpRequest = exchange.getRequest()
                            .mutate()
                            .header("username", jwtUtil.extractUsernameFromToken(authHeader))
                            .header("userId", jwtUtil.extractUserIdFromToken(authHeader).toString())
                            .build();
                } catch (Exception e) {
                    throw new InvalidTokenException("Unauthorized access to application");
                }
                return chain.filter(exchange.mutate().request(serverHttpRequest).build());
            }

            return chain.filter(exchange);
        };
    }

}
