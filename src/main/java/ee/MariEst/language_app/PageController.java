package ee.MariEst.language_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/sections")
    public String sections() {
        return "sections";
    }

    @GetMapping("/letters")
    public String letters() {
        return "letters";
    }

}