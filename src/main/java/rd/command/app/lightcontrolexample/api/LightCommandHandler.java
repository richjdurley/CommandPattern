package rd.command.app.lightcontrolexample.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import rd.command.app.lightcontrolexample.adaptor.LightAdaptor;
import rd.command.app.lightcontrolexample.mock.MockLightDeviceProvider;
import rd.command.app.lightcontrolexample.model.LightDeviceResult;
import rd.command.components.api.framework.exceptions.BadRequestException;
import rd.command.components.api.framework.exceptions.InfrastructureException;
import rd.command.framework.CommandHandler;
import rd.command.framework.domain.Command;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class LightCommandHandler implements CommandHandler<Object, LightDeviceResult> {

    @Autowired
    MockLightDeviceProvider mockLightDeviceProvider;

    @Autowired
    LightAdaptor lightAdaptor;
    private java.util.logging.Logger log =
            Logger.getLogger(LightCommandHandler.class.getSimpleName());

    public LightCommandHandler(LightAdaptor lightAdaptor) {
        this.lightAdaptor = lightAdaptor;
    }

    public Mono<LightDeviceResult> handle(Command<Object> command) {
        return WebClient.create().post()
                .uri(builder -> builder.scheme("http")
                        .host("localhost").port(8080).path("/mock/provider/" + command.getTargetDeviceName() + "/" + command.getCommandActionName())
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new InfrastructureException("Internal Server Error")))
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new BadRequestException("Bad Request"))).bodyToMono(LightDeviceResult.class);

    }



  /*  public WebClient client() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080/mock/provider")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                //.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();
    }*/

}
