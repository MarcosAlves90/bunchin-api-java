package br.com.fatecmaua.bunchin.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiRootRedirectController {
    @GetMapping("/api")
    public String redirectApiRoot() {
        return "redirect:/";
    }
}
