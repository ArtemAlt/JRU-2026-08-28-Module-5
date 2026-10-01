package com.example.app.service;

import com.example.app.account.BankTransferService;
import com.example.app.controller.DemoController;
import com.example.app.model.CreateUserDto;
import com.example.app.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DemoController.class)
class ControllerSliseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void createUserTest() throws Exception {
        CreateUserDto dto = new CreateUserDto("John", "john@example.com", 20);
        when(userService.createUser(dto)).thenReturn(new User(1L, dto.getName(), dto.getEmail(), dto.getAge()));
        String body = """
                {
                "name": "John",
                "email": "john@example.com",
                "age": 20
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith("User created with ID: ")));
//                .andExpect(jsonPath("$.name").value("John"));
    }
}