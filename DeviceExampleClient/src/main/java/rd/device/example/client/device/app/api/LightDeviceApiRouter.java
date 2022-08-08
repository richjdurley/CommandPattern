package rd.device.example.client.device.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import rd.device.example.client.device.app.model.LightDeviceResult;
import rd.device.framework.api.handler.BaseDeviceHandler;
import rd.device.framework.api.handler.ErrorHandler;
import rd.device.framework.api.exceptions.PathNotFoundException;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class LightDeviceApiRouter {

    @Autowired
    BaseDeviceHandler<LightDeviceResult> handler;

    @Autowired
    ErrorHandler errorHandler;

    @Bean
    RouterFunction<?> routerFunction() {
        return route(GET("/status"),
                req -> handler.handleStatus(req)
        ).and(route(OPTIONS("/status"),
                        req -> handler.handleStatus(req)))
                .and(route(POST("/devices"),
                        req -> handler.handleCommand(req)))
                .andOther(route(RequestPredicates.all(),
                        request -> this.errorHandler.handleError(new PathNotFoundException("not found"))));
    }
}
