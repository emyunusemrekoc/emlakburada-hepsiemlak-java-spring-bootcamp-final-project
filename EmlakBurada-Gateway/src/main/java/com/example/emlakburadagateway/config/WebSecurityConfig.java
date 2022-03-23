package com.example.emlakburadagateway.config;

import com.example.emlakburadagateway.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class WebSecurityConfig {


    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //// @formatter:off
        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable();

        return http.build();
        // @formatter:on

    }

    @Autowired
    private JwtFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()


                //login işlemi yapılmadan; kullanıcı oluşturulabilir, login işlemi yapılabilir. filtreden geçmez.

                .route("emlakburada-user",
                        r -> r.method(HttpMethod.POST)
                                .and()
                                .path(("/individual-users/**"))
                                .uri("lb://emlakburada-user"))
                .route("emlakburada-auth",
                        r -> r.method(HttpMethod.POST)
                                .and()
                                .path("/auth/**")
                                .uri("lb://emlakburada-auth"))
                .route("emlakburada-user",
                        r -> r.method(HttpMethod.GET)
                                .and()
                                .path(("/individual-users/**"))
                                .filters(f -> f.filter(filter)).uri("lb://emlakburada-user"))
                .route("emlakburada-advert",
                        r -> r.method(HttpMethod.GET,HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE)
                                .and()
                                .path("/adverts/**")
                                .filters(f -> f.filter(filter)).uri("lb://emlakburada-advert"))
                .route("emlakburada-purchase",
                        r -> r.method(HttpMethod.GET,HttpMethod.POST)
                                .and()
                                .path("/purchases/**")
                                .filters(f -> f.filter(filter)).uri("lb://emlakburada-purchase"))
                .route("emlakburada-payment",
                        r -> r.path("/payments/**")
                                .filters(f -> f.filter(filter)).uri("lb://emlakburada-payment"))
                .build();

    }

}
