package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import javax.servlet.http.HttpServletRequest;
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
    private final RuleService ruleService;

    @GetMapping("/createAccident")
    public String createAccident(Model model, Authentication auth) {
        model.addAttribute("user", auth.getName());
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident, Model model,
                               @RequestParam(name = "type.id") int typeId,
                               HttpServletRequest req) {
        Optional<AccidentType> accidentTypeOptional = accidentTypeService.findById(typeId);
        if (accidentTypeOptional.isEmpty()) {
            model.addAttribute("message", "Нет такого типа инцидента");
            return "errorPage";
        }
        accident.setType(accidentTypeOptional.get());
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(ruleService.findById(ids));
        if (!accidentService.create(accident)) {
            model.addAttribute("message", "Инцидент не создан!");
            return "errorPage";
        }
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String editAccident(Model model, @RequestParam("id") int accidentId, Authentication auth) {
        model.addAttribute("user", auth.getName());
        Optional<Accident> accidentOptional = accidentService.findById(accidentId);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидента не существует!");
            return "errorPage";
        }
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, Model model,
                                 @RequestParam(name = "type.id") int typeId,
                                 HttpServletRequest req) {
        Optional<AccidentType> accidentTypeOptional = accidentTypeService.findById(typeId);
        if (accidentTypeOptional.isEmpty()) {
            model.addAttribute("message", "Нет такого типа инцидента");
            return "errorPage";
        }
        accident.setType(accidentTypeOptional.get());
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(ruleService.findById(ids));
        if (!accidentService.update(accident)) {
            model.addAttribute("message", "Инцидент не создан!");
            return "errorPage";
        }
        return "redirect:/index";
    }
}
