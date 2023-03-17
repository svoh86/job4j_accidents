package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Индексная страница
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Controller
public class IndexController {
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "admin");
        return "index";
    }
}
