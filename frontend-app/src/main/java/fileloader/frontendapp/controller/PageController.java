package fileloader.frontendapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file-storage")
public class PageController {

    @GetMapping
    public String getHomePage(){
        return "main";
    }
}
