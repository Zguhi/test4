package com.example.apfast.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String loginPage() {
    return "vehicle/login";
  }

  @GetMapping("/")
  public String home() {
    return "redirect:/catalogues";
  }
}