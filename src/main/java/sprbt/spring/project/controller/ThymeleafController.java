package sprbt.spring.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
//@RequestMapping("/thymeleaf/")
public class ThymeleafController {

    @GetMapping("/")
    public String ex4(){

        return "index";
    }
}
