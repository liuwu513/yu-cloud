package org.yuhan.ziyu.gateway;

import org.yuhan.ziyu.gateway.filter.BasicAuthFilter;
import org.yuhan.ziyu.gateway.filter.CharacterEncodingPostFilter;
import org.yuhan.ziyu.gateway.filter.ResponseErrorFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
@EnableCircuitBreaker
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public CharacterEncodingPostFilter characterEncodingPostFilter() {
        return new CharacterEncodingPostFilter();
    }

    @Bean
    public BasicAuthFilter basicAuthFilter() {
        return new BasicAuthFilter();
    }

    @Bean
    public ResponseErrorFilter responseErrorFilter() {
        return new ResponseErrorFilter();
    }
}
