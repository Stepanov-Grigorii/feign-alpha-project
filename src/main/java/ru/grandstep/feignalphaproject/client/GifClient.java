package ru.grandstep.feignalphaproject.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "gifClient", url = "${gif.api.url}")
public interface GifClient {
    @GetMapping("random?api_key=${gif.api.token}&tag=rich&random_id=${gif.api.random-id}")
    String getRich();
    @GetMapping("random?api_key=${gif.api.token}&tag=broke&random_id=${gif.api.random-id}")
    String getBroke();
}
