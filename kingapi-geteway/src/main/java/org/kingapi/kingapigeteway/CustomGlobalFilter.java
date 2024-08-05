package org.kingapi.kingapigeteway;

import ch.qos.logback.classic.spi.EventArgUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.kingapi.kingapiclientsdk.Utils.SignUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 全局过滤
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.请求日志
        ServerHttpRequest request = exchange.getRequest();
        log.info("请求唯一标识：" + request.getId());
        log.info("请求方法：" + request.getMethod());
        log.info("请求路径：" + request.getPath().value());
        log.info("请求参数：" + request.getQueryParams());
        String sourceAddress = request.getLocalAddress().getHostString();
        log.info("请求来源地址：" + request.getRemoteAddress());
        ServerHttpResponse response = exchange.getResponse();
        // 2.黑白名单
        if (IP_WHITE_LIST.contains(sourceAddress)) {
            return handelNoAuth(response);
        }
        // 3.用户鉴权（判断ak，sk是否合法）
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String body = headers.getFirst("body");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        if (!accessKey.equals("king")) {
            return handelNoAuth(response);
        }
        if (Long.parseLong(nonce) > 10000L) {
            return handelNoAuth(response);
        }
        Long currentTime = System.currentTimeMillis() / 1000;
        final Long FIVE_MINUTES = 60 * 5L;
        if ((currentTime - Long.parseLong(timestamp))>=FIVE_MINUTES){
            return handelNoAuth(response);
        }

        String serverSign = SignUtil.getSign(body, "kinglearn");
        if (!sign.equals(serverSign)){
            return handelNoAuth(response);
        }
        // 4.请求的接口是否存在
        // todo 从数据库中查询模拟接口是否存在，以及请求方法是否匹配（还可以校验请求参数>
        // 5.请求转发，调用模拟接口
        Mono<Void> filter = chain.filter(exchange);
        log.info("custom global filter");
        //7．响应目志

        // 8. todo 调用成功，接口调用次数＋1 invokeCount

        // 9．调用失败，返回一个规范的错误码
        if (response.getStatusCode() == HttpStatus.OK){

        }else {
            return handelInvokeError(response);
        }
        return filter;
    }

    @Override
    public int getOrder() {
        return -1;
    }

    public Mono<Void> handelNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }
    public Mono<Void> handelInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }
}