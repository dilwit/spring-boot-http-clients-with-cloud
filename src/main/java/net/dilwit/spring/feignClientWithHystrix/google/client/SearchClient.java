package net.dilwit.spring.feignClientWithHystrix.google.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "google-search-api", url = "{vendor.google.api.baseurl}", fallback = SearchClientFallBack.class)
public interface SearchClient {

    @RequestMapping(value = "{vendor.google.api.customsearch.url}", method = RequestMethod.GET)
    String search();
}