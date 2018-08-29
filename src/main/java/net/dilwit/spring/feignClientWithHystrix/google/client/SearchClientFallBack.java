package net.dilwit.spring.feignClientWithHystrix.google.client;

public class SearchClientFallBack implements SearchClient {

    @Override
    public String search() {
        return null;
    }
}
