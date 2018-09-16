package net.dilwit.spring.httpClientsWithCloud.feign.external.google.client;

import org.springframework.stereotype.Component;

@Component
public class SearchClientFallBack implements SearchClient {

    @Override
    public String search() {
        return null;
    }
}
