package rd.command.framework.api.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Order(3)
public class ResponseHeadersFilter implements WebFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseHeadersFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain) {
        return webFilterChain.filter(SharedHTTPHeaders.addHeaders(serverWebExchange));
    }


}


