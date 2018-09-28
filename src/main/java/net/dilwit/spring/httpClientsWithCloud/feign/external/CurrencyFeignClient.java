package net.dilwit.spring.httpClientsWithCloud.feign.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "currency-client", url = "https://free.currencyconverterapi.com")
public interface CurrencyFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = "/api/v6/currencies")
    String getCurrencies();

}
