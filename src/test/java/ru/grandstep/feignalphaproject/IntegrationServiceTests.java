package ru.grandstep.feignalphaproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.grandstep.feignalphaproject.client.CurrencyClient;
import ru.grandstep.feignalphaproject.client.GifClient;
import ru.grandstep.feignalphaproject.service.IntegrationService;

@SpringBootTest
class IntegrationServiceTests {
    @Autowired
    private IntegrationService integrationService;
    @MockBean
    private CurrencyClient currencyClient;
    @MockBean
    private GifClient gifClient;

    @Test
    void getCorrectCurrentCurrencyAnswer() throws JsonProcessingException {
        Mockito.when(currencyClient.getLatestCurrency()).thenReturn(JsonAnswers.CORRECT_CURRENCY_ANSWER_70);

        double currentCurrency = integrationService.getCurrentCurrency();

        Assertions.assertEquals(70, currentCurrency);
    }

    @Test
    void getIncorrectCurrentCurrencyAnswer(){
        Mockito.when(currencyClient.getLatestCurrency()).thenReturn(JsonAnswers.INCORRECT_JSON_ANSWER);

        Assertions.assertThrows(JsonProcessingException.class, integrationService::getCurrentCurrency);
    }

    @Test
    void getCorrectYesterdayCurrencyAnswer() throws JsonProcessingException {
        Mockito.when(currencyClient.getHistoricalCurrency(Mockito.notNull())).thenReturn(JsonAnswers.CORRECT_CURRENCY_ANSWER_70);

        double currentCurrency = integrationService.getYesterdayCurrency();

        Assertions.assertEquals(70, currentCurrency);
    }

    @Test
    void getIncorrectYesterdayCurrencyAnswer(){
        Mockito.when(currencyClient.getHistoricalCurrency(Mockito.notNull())).thenReturn(JsonAnswers.INCORRECT_JSON_ANSWER);

        Assertions.assertThrows(JsonProcessingException.class, integrationService::getYesterdayCurrency);
    }

    @Test
    void getCorrectBrokeGif() throws JsonProcessingException {
        Mockito.when(gifClient.getBroke()).thenReturn(JsonAnswers.CORRECT_GIF_JSON);

        String imageOriginalUrl = integrationService.getBrokeGif();

        Assertions.assertEquals("IMAGE_ORIGINAL_URL", imageOriginalUrl);
    }

    @Test
    void getIncorrectBrokeGifAnswer(){
        Mockito.when(gifClient.getBroke()).thenReturn(JsonAnswers.INCORRECT_JSON_ANSWER);

        Assertions.assertThrows(JsonProcessingException.class, integrationService::getBrokeGif);
    }

    @Test
    void getCorrectRichGif() throws JsonProcessingException {
        Mockito.when(gifClient.getRich()).thenReturn(JsonAnswers.CORRECT_GIF_JSON);

        String imageOriginalUrl = integrationService.getRichGif();

        Assertions.assertEquals("IMAGE_ORIGINAL_URL", imageOriginalUrl);
    }

    @Test
    void getIncorrectRichGifAnswer(){
        Mockito.when(gifClient.getRich()).thenReturn(JsonAnswers.INCORRECT_JSON_ANSWER);

        Assertions.assertThrows(JsonProcessingException.class, integrationService::getRichGif);
    }

}
