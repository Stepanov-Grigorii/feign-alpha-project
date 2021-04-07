package ru.grandstep.feignalphaproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.grandstep.feignalphaproject.service.IntegrationService;
import ru.grandstep.feignalphaproject.service.ManagerService;

@SpringBootTest
public class ManagerServiceTests {
    @Autowired
    private ManagerService managerService;
    @MockBean
    private IntegrationService integrationService;

    @Test
    void getBrokeDistributorAnswer() throws JsonProcessingException {
        Mockito.when(integrationService.getCurrentCurrency()).thenReturn(70.);
        Mockito.when(integrationService.getYesterdayCurrency()).thenReturn(71.);
        Mockito.when(integrationService.getBrokeGif()).thenReturn("BROKE");

        String brokeGif = managerService.gifDistributor();

        Assertions.assertEquals("BROKE", brokeGif);
    }

    @Test
    void getRichDistributorAnswer() throws JsonProcessingException {
        Mockito.when(integrationService.getCurrentCurrency()).thenReturn(72.);
        Mockito.when(integrationService.getYesterdayCurrency()).thenReturn(71.);
        Mockito.when(integrationService.getRichGif()).thenReturn("RICH");

        String richGif = managerService.gifDistributor();

        Assertions.assertEquals("RICH", richGif);
    }

}
