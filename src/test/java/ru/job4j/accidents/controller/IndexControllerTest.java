package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class IndexControllerTest {
    /**
     * Создает объект-заглушку. Мы можем отправлять в него запросы.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Аннотация @WithMockUser - Подставляет авторизованного пользователя в запрос.
     */
    @Test
    @WithMockUser
    public void whenIndexView() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(handler().handlerType(IndexController.class))
                .andExpect(model().attribute("user", "user"));
    }
}