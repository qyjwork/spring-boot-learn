package cn.imacoder.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthFilterFactory extends AbstractGatewayFilterFactory {

    /**
     * 创造出的过滤器的顺序
     */
    private int order = -5;

    /**
     * constructor
     */
    public AuthFilterFactory() {
    }

    @Override
    public GatewayFilter apply(Object config) {
        return new AuthFilter();
    }

    /**
     * 创建一个内部类，来实现2个接口，指定顺序
     */
    private class AuthFilter implements GatewayFilter, Ordered {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            String token = request.getHeaders().getFirst("Authorization");
            if (token == null || token.isEmpty()) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                String returnStr = "token为空";
                DataBuffer buffer = response.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
                return response.writeWith(Flux.just(buffer));
            }
            return chain.filter(exchange);
        }

        @Override
        public int getOrder() {
            return order;
        }
    }

}
