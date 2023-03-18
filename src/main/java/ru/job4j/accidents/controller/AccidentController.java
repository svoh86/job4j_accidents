package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/editAccident/{accidentId}")
    public String editAccident(Model model, @PathVariable("accidentId") Integer accidentId, HttpSession session) {
        model.addAttribute("user", "admin");
        Optional<Accident> accidentOptional = accidentService.findById(accidentId);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидента не существует!");
            return "errorPage";
        }
        session.setAttribute("accidentId", accidentOptional.get().getId());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, Model model, HttpSession session) {
        Integer accidentId = (Integer) session.getAttribute("accidentId");
        if (!accidentService.update(accidentId, accident)) {
            model.addAttribute("message", "Инцидент не создан!");
            return "errorPage";
        }
        return "redirect:/index";
    }
}
