package backend.ecommerce.ecommerceapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api/home")
public class HomeController    {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Home Page";
    }
}
