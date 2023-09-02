package action.in.blog.client;

import action.in.blog.domain.FlightResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JetBlueClient {

    private static final String JET_BLUE = "JET BLUE";
    private final WebClient webClient;

    public JetBlueClient(@Value("${airline.jet-blue}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<FlightResult> getFlights(String from, String to) {
        return webClient
                .get()
                .uri("/{from}/{to}", from, to)
                .retrieve()
                .bodyToFlux(FlightResult.class)
                .doOnNext(flight -> normalizeResponse(flight, from, to))
                .onErrorResume(ex -> Mono.empty());
    }

    private void normalizeResponse(FlightResult result, String from, String to) {
        result.setFrom(from);
        result.setTo(to);
        result.setAirline(JET_BLUE);
    }
}
