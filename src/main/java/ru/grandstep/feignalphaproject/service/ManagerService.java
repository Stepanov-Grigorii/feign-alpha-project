package ru.grandstep.feignalphaproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerService {
    private final IntegrationService integrationService;

    public String gifDistributor() throws JsonProcessingException {
        return integrationService.getCurrentCurrency() >= integrationService.getYesterdayCurrency() ?
                integrationService.getRichGif() : integrationService.getBrokeGif();
    }
}
