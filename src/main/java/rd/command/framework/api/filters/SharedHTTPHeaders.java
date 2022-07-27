package rd.command.framework.api.filters;

import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;

public class SharedHTTPHeaders {
    public static final ServerWebExchange addHeaders(ServerWebExchange serverWebExchange) {
        if (!serverWebExchange.getResponse().getHeaders().containsKey("Access-Control-Allow-Origin"))
            serverWebExchange.getResponse()
                    .getHeaders().add("Access-Control-Allow-Origin", "*");

        if (!serverWebExchange.getResponse().getHeaders().containsKey("Access-Control-Request-Method"))
            serverWebExchange.getResponse()
                    .getHeaders().add("Access-Control-Request-Method", "GET, POST, OPTIONS");

        if (!serverWebExchange.getResponse().getHeaders().containsKey("Access-Control-Allow-Headers"))
            serverWebExchange.getResponse()
                    .getHeaders().add("Access-Control-Allow-Headers", "Content-Type");


        return serverWebExchange;
    }

    public static final ServerWebExchange addHeaders(ServerWebExchange serverWebExchange, MediaType mediaType) {
        addHeaders(serverWebExchange);
        if (!serverWebExchange.getResponse().getHeaders().containsKey("Content-Type"))
            serverWebExchange.getResponse()
                    .getHeaders().add("Content-Type", mediaType.toString());
        return serverWebExchange;
    }
}
