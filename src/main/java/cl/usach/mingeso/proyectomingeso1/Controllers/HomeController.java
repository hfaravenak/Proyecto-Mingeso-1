package cl.usach.mingeso.proyectomingeso1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String myPage() { return "main"; }

}
