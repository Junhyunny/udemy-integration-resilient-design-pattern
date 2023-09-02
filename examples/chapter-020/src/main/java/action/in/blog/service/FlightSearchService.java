package action.in.blog.service;

import action.in.blog.client.DeltaClient;
import action.in.blog.client.FrontierClient;
import action.in.blog.client.JetBlueClient;
import action.in.blog.domain.FlightResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class FlightSearchService {

    private final DeltaClient deltaClient;
    private final FrontierClient frontierClient;
    private final JetBlueClient jetBlueClient;

    public FlightSearchService(
            DeltaClient deltaClient,
            FrontierClient frontierClient,
            JetBlueClient jetBlueClient
    ) {
        this.deltaClient = deltaClient;
        this.frontierClient = frontierClient;
        this.jetBlueClient = jetBlueClient;
    }

    public Flux<FlightResult> getFlights(String from, String to) {
        return Flux.merge(
                        deltaClient.getFlights(from, to),
                        frontierClient.getFlights(from, to),
                        jetBlueClient.getFlights(from, to)
                )
                .take(Duration.ofSeconds(3));
    }
}
