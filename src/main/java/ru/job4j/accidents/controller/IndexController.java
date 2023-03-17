package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.AccidentService;

/**
 * Индексная страница
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Controller
public class IndexController {
    private final AccidentService accidentService;

    public IndexController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "admin");
        model.addAttribute("accidents", accidentService.getAll());
        return "index";
    }
}
