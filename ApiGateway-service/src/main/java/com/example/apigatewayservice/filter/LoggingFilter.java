package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    public LoggingFilter(){
        super(Config.class);
    }

    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Custom Pre Filer
//        return ((exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response =  exchange.getResponse();
//
//            log.info("Global Filter baseMessae: {}", config.getBaseMessage());
//
//            if(config.isPreLogger()){
//                log.info("Global Filter Start: request id {}", request.getId());
//            }
//
//        //Custom Post Filter
//        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//            if(config.isPostLogger()){
//                log.info("Global Filter End: response code {}", response.getStatusCode());
//            }
//        }));
//        });

        //스프링의 WebFlux 사용
        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain)->{
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response =  exchange.getResponse();

            log.info("Logging Filter baseMessae: {}", config.getBaseMessage());

            if(config.isPreLogger()){
                log.info("Logging Pre Filter : request id {}", request.getId());
            }

        //Custom Post Filter
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            if(config.isPostLogger()){
                log.info("Logging Post Filter: response code {}", response.getStatusCode());
            }
        }));
        }, OrderedGatewayFilter.LOWEST_PRECEDENCE); // HIGHEST_PRECEDENCE: 필터 우선순위 제일 높게 적용 LOWEST_PRECEDENCE: 제일 낮게

        return filter;
    }
}
