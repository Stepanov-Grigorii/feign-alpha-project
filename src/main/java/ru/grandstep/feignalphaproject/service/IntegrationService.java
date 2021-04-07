package ru.grandstep.feignalphaproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.grandstep.feignalphaproject.client.CurrencyClient;
import ru.grandstep.feignalphaproject.client.GifClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntegrationService {
    private final CurrencyClient currencyClient;
    private final GifClient gifClient;

    public double getCurrentCurrency() throws JsonProcessingException {
        log.info("request current currency");

        double currentCurrency = getCurrencyFromJson(currencyClient.getLatestCurrency());
        log.info("request success, current currency = {}", currentCurrency);
        return currentCurrency;
    }

    public double getYesterdayCurrency() throws JsonProcessingException {
        log.info("request yesterday currency");
        LocalDateTime time = LocalDateTime.now().minusDays(1);
        String date = time.format(DateTimeFormatter.ISO_DATE);

        double yesterdayCurrency = getCurrencyFromJson(currencyClient.getHistoricalCurrency(date));
        log.info("request success, yesterday currency = {}", yesterdayCurrency);
        return yesterdayCurrency;
    }

    public String getRichGif() throws JsonProcessingException {
        log.info("request rich gif");

        String gifUrlFromJson = getGifUrlFromJson(gifClient.getRich());
        log.info("request success, rich gif = {}", gifUrlFromJson);
        return gifUrlFromJson;
    }

    public String getBrokeGif() throws JsonProcessingException {
        log.info("request broke gif");

        String gifUrlFromJson = getGifUrlFromJson(gifClient.getBroke());
        log.info("request success, broke gif = {}", gifUrlFromJson);
        return gifUrlFromJson;
    }

    private double getCurrencyFromJson(String currencyJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info(currencyJson);
        return objectMapper.readValue(currencyJson, ObjectNode.class).get("rates").get("RUB").asDouble();
    }

    private String getGifUrlFromJson(String gifJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(gifJson, ObjectNode.class).get("data").get("image_original_url").asText();
    }
}
