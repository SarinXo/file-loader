package ilya.coursework.fileloader.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file-storage")
public class HomeController {

    @GetMapping
    public String getHomePage(){
        return "main";
    }
}
