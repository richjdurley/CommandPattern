package rd.device.example.remote.device.lifx.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import rd.device.framework.api.handler.ErrorHandler;
import rd.device.framework.api.exceptions.PathNotFoundException;
import rd.device.example.remote.device.lifx.provider.handler.LIFX_LightsController;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class LightDeviceApiRouter {

    @Autowired
    LIFX_LightsController handler;

    @Autowired
    ErrorHandler errorHandler;

    @Bean
    RouterFunction<?> routerFunction() {
        return route(GET("/status"),
                req -> handler.handleStatus(req)
        ).and(route(OPTIONS("/status"),
                        req -> handler.handleStatus(req)))
                .and(route(POST("/devices/{device}"),
                        req -> handler.handleCommand(req)))
                .andOther(route(RequestPredicates.all(),
                        request -> this.errorHandler.handleError(new PathNotFoundException("not found"))));
    }
}
