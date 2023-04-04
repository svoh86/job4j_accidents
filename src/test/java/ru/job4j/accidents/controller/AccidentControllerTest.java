package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccidentService accidentService;

    @Test
    @WithMockUser
    public void whenCreateAccident() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"))
                .andExpect(model().attribute("user", "user"));
    }

    @Test
    @WithMockUser
    public void whenEditAccident() throws Exception {
        this.mockMvc.perform(get("/editAccident/?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("user", "user"));
    }

    @Test
    @WithMockUser
    public void whenSaveAccidentReturnErrorPage() throws Exception {
        this.mockMvc.perform(post("/saveAccident")
                        .param("name", "Name")
                        .param("text", "Text")
                        .param("address", "Address")
                        .param("type.id", "1")
                        .param("rIds", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("message", "Инцидент не создан!"));
        ArgumentCaptor<Accident> argumentCaptor = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).create(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getName()).isEqualTo("Name");
    }

    @Test
    @WithMockUser
    public void whenUpdateAccidentReturnErrorPage() throws Exception {
        this.mockMvc.perform(post("/updateAccident")
                        .param("id", "4")
                        .param("name", "new Name")
                        .param("text", "Text")
                        .param("address", "Address")
                        .param("type.id", "1")
                        .param("rIds", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("message", "Инцидент не обновлен!"));
        ArgumentCaptor<Accident> argumentCaptor = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).update(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getName()).isEqualTo("new Name");
    }
}