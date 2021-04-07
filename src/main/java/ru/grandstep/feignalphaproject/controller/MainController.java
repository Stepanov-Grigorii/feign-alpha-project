package ru.grandstep.feignalphaproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.feignalphaproject.service.ManagerService;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ManagerService  managerService;

    @GetMapping
    public RedirectView getGif() throws JsonProcessingException {
        return new RedirectView(managerService.gifDistributor());
    }
}
