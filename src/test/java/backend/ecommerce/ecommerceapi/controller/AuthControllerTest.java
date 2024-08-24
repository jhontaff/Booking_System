package backend.ecommerce.ecommerceapi.controller;

import backend.ecommerce.ecommerceapi.controller.authentication.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    void signup() throws Exception {

    }

    @Test
    void signin() {
    }
}