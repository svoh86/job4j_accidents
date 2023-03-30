package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.AuthorityRepository;
import ru.job4j.accidents.repository.UserRepository;

/**
 * Страница регистрации пользователя
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Controller
@AllArgsConstructor
public class RegController {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "error", required = false) String error,
                          Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Пользователь с таким логином уже существует!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityRepository.findByAuthority("ROLE_USER"));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return "redirect:/reg?error=true";
        }
        return "redirect:/login";
    }
}
