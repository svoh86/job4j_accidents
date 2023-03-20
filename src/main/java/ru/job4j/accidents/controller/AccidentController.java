package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Контроллер инцидентов
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping("/createAccident")
    public String createAccident(Model model) {
        model.addAttribute("user", "admin");
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident, Model model) {
       if (!accidentService.create(accident)) {
           model.addAttribute("message", "Инцидент не создан!");
           return "errorPage";
       }
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String editAccident(Model model, @RequestParam("id") int accidentId) {
        model.addAttribute("user", "admin");
        Optional<Accident> accidentOptional = accidentService.findById(accidentId);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидента не существует!");
            return "errorPage";
        }
        model.addAttribute("accident", accidentOptional.get());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, Model model,
                                 @RequestParam("id") int accidentId) {
        if (!accidentService.update(accidentId, accident)) {
            model.addAttribute("message", "Инцидент не создан!");
            return "errorPage";
        }
        return "redirect:/index";
    }
}
