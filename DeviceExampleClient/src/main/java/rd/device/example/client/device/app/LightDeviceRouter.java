package rd.device.example.client.device.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import rd.device.example.client.device.app.controller.LightDeviceHandler;
import rd.device.framework.api.controller.handler.ErrorHandler;
import rd.device.framework.api.exceptions.PathNotFoundException;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class LightDeviceRouter {

    @Autowired
    LightDeviceHandler handler;

    @Autowired
    ErrorHandler errorHandler;

    @Bean
    RouterFunction<?> routerFunction() {
        return route(GET("/device/status"),
                req -> handler.handleStatus()
        ).and(route(OPTIONS("/device/status"),
                        req -> handler.handleStatus()))
                .and(route(POST("/device/{id}/{action}"),
                        req -> handler.handleCommand(req)))
                .andOther(route(RequestPredicates.all(),
                        request -> this.errorHandler.handleError(new PathNotFoundException("not found"))));
    }
}
