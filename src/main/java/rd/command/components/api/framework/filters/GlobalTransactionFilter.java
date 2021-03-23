package rd.command.components.api.framework.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import rd.command.components.api.framework.exceptions.ErrorResponseTransformer;
import rd.command.components.utils.json.JSONUtil;
import reactor.core.publisher.Mono;

import java.util.logging.Level;

import static rd.command.components.api.framework.filters.SharedHTTPHeaders.addHeaders;

@Component
@Order(Integer.MIN_VALUE)
public class GlobalTransactionFilter implements WebFilter {

    Logger logger = LoggerFactory.getLogger(GlobalTransactionFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain) {
        logger.info(
                "start api request : {}",
                serverWebExchange.getRequest().getURI());
        return webFilterChain.filter(serverWebExchange).log(GlobalTransactionFilter.class.getName(), Level.FINE).doOnSuccess(aVoid -> logger.info("end api req : {} response : {}",
                serverWebExchange.getRequest().getURI(), serverWebExchange.getResponse().getStatusCode())).onErrorResume(e ->
                doManualErrorProcessing(serverWebExchange, webFilterChain, e));
    }

    private Mono<Void> doManualErrorProcessing(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain, Throwable e) {
        logger.warn("A processing exception occurred and was caught by the global transaction handler", e);
        addHeaders(serverWebExchange, MediaType.APPLICATION_JSON).getResponse().setStatusCode(HttpStatus.valueOf(ErrorResponseTransformer.transformToErrorResponse(e).getStatusCode()));
        return serverWebExchange.getResponse().writeWith(Mono.just(serverWebExchange.getResponse().bufferFactory().wrap(JSONUtil.toJSONString(ErrorResponseTransformer.transformToErrorResponse(e)).getBytes())));
    }


}
