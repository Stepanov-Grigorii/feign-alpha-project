package ru.grandstep.feignalphaproject.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "currencyClient", url = "${currency.api.url}")
public interface CurrencyClient {
    @GetMapping("latest.json?app_id=${currency.api.token}")
    String getLatestCurrency();
    @GetMapping("historical/{date}.json?app_id=${currency.api.token}")
    String getHistoricalCurrency(@PathVariable("date") String date);
}
