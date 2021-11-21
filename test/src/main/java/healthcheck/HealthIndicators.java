package healthcheck;

import java.net.Socket;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class HealthIndicators   implements HealthIndicator {

    private static final String URL
            = "http://127.0.0.1";
    //Now its static ,but in the future if we will have more endpoints  we will create new class that has all url.
    @Override
    public Health health() {
        // check if url shortener service url is reachable
        try (Socket socket =
                     new Socket(new java.net.URL(URL).getHost(),12345)) {

        } catch (Exception e) {
            log.warn("Failed to connect to: {}",URL);
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
        return Health.up().build();
    }

}
