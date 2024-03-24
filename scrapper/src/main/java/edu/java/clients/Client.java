package edu.java.clients;

import edu.java.dto.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

public abstract class Client {
    protected abstract WebClient getWebClient();

    public <T> Mono<T> processGetQuery(String uri, Class<T> klass) {
        return getWebClient().get().uri(uri).retrieve().onStatus(
            HttpStatusCode::is4xxClientError,
            error -> Mono.error(new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Link is not valid"
            ))
        ).onStatus(
            HttpStatusCode::is5xxServerError,
            error -> Mono.error(new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"
            ))
        ).bodyToMono(klass);
    }
}
