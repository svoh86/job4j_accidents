package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.ArrayList;
import java.util.List;
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
    private final AccidentTypeService accidentTypeService;

    @GetMapping("/createAccident")
    public String createAccident(Model model) {
        model.addAttribute("user", "admin");
        model.addAttribute("types", accidentTypeService.getAll());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident, Model model,
                               @RequestParam(name = "type.id") int typeId) {
        Optional<AccidentType> accidentTypeOptional = accidentTypeService.findById(typeId);
        if (accidentTypeOptional.isEmpty()) {
            model.addAttribute("message", "Нет такого типа инцидента");
            return "errorPage";
        }
        accident.setType(accidentTypeOptional.get());
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
        model.addAttribute("types", accidentTypeService.getAll());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, Model model,
                                 @RequestParam("id") int accidentId,
                                 @RequestParam(name = "type.id") int typeId) {
        Optional<AccidentType> accidentTypeOptional = accidentTypeService.findById(typeId);
        if (accidentTypeOptional.isEmpty()) {
            model.addAttribute("message", "Нет такого типа инцидента");
            return "errorPage";
        }
        accident.setType(accidentTypeOptional.get());
        if (!accidentService.update(accidentId, accident)) {
            model.addAttribute("message", "Инцидент не создан!");
            return "errorPage";
        }
        return "redirect:/index";
    }
}