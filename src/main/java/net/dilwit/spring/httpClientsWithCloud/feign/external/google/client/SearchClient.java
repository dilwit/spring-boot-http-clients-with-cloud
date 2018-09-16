package net.dilwit.spring.httpClientsWithCloud.feign.external.google.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "google-search-api", url = "{vendor.google.api.baseurl}", fallback = SearchClientFallBack.class)
public interface SearchClient {

    @RequestMapping(value = "{vendor.google.api.customsearch.url}", method = RequestMethod.GET)
    String search();
}