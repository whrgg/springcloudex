package com.hmall.gateway.filters;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class PrintGatewayFilterFactory extends AbstractGatewayFilterFactory<PrintGatewayFilterFactory.Config> {
    @Override
    public GatewayFilter apply(Config config) {
        return new  OrderedGatewayFilter(new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String a= config.getA();
                String b= config.getB();
                String c= config.getC();
                System.out.println(66666);
                System.out.println(a+" "+b+" "+c);
                return chain.filter(exchange);
            }
        },2){


        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("a","b","c");
    }

    @Override
    public Class<PrintGatewayFilterFactory.Config> getConfigClass() {
        return Config.class;
    }

    @Data
    public static class Config{
        private String a;
        private String b;
        private String c;
    }
}
