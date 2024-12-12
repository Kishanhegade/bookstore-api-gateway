package com.bridgelabz.bsa.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/fallback/user")
    public Mono<String> userServiceFallback() {
        return Mono.just("User Service is temporarily unavailable. Please try again later.");
    }

    @GetMapping("/fallback/book")
    public Mono<String> bookServiceFallback() {
        return Mono.just("Book Service is temporarily unavailable. Please try again later.");
    }

    @GetMapping("/fallback/cart")
    public Mono<String> cartServiceFallback() {
        return Mono.just("Cart Service is temporarily unavailable. Please try again later.");
    }

    @GetMapping("/fallback/order")
    public Mono<String> orderServiceFallback() {
        return Mono.just("Order Service is temporarily unavailable. Please try again later.");
    }

    @GetMapping("/fallback/wishlist")
    public Mono<String> wishlistServiceFallback() {
        return Mono.just("Wishlist Service is temporarily unavailable. Please try again later.");
    }

    @GetMapping("/fallback/feedback")
    public Mono<String> feedbackServiceFallback() {
        return Mono.just("Feedback Service is temporarily unavailable. Please try again later.");
    }
}
